package com.example.firstwatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.bloco.faker.Faker;

public class AlarmsFragment extends Fragment {


    private Faker[] alarms;
    SelectedAlarmFragment selectedAlarmFragment;

    public AlarmsFragment(){
        // require a empty public constructor
        Faker alarm1 = new Faker();
        selectedAlarmFragment = new SelectedAlarmFragment();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflated = inflater.inflate(R.layout.fragment_alarms,container,false);

        // Create a list of Date objects (you can replace these with your actual dates)
        List<Date> dateList = new ArrayList<>();
        dateList.add(new Date()); // Current date
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1))); // Yesterday
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2))); // 2 days ago
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3)));
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(4)));

        // Create and set the custom adapter for your ListView
        CustomAdapter adapter = new CustomAdapter(getActivity(), dateList);
        ListView listView = inflated.findViewById(R.id.listView);
        listView.setAdapter(adapter);


        adapter.setOnRectangleClickListener(new CustomAdapter.OnRectangleClickListener() {
            @Override
            public void onRectangleClick(int position) {
                // Handle the click event for the rectangle at the specified position
                // You can perform actions like opening a detail view, displaying a toast, etc.
                BottomNavigationView navbar = getActivity().findViewById(R.id.bottomNavigationView);
                navbar.setSelectedItemId(R.id.alarms);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, selectedAlarmFragment)
                        .addToBackStack(null)  // Optional: Add transaction to back stack for back navigation
                        .commit();
//                Toast.makeText(getActivity(), "Rectangle at position " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });



        return inflated;
    }
}
