package com.example.firstwatch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.appsearch.AppSearchBatchResult;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    AlarmsFragment alarmsFragment;
    SettingsFragment settingsFragment;
    private AppState state = AppState.getInstance();

    public HomeFragment(){
        // require a empty public constructor
        alarmsFragment = new AlarmsFragment();
        settingsFragment = new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflated = inflater.inflate(R.layout.fragment_home, container, false);

        Button viewMoreButton = (Button)inflated.findViewById(R.id.dashboard_view);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView name = inflated.findViewById(R.id.username);

        ImageView settingsBtn = inflated.findViewById(R.id.settings);
        TextView recentView = inflated.findViewById(R.id.recentAlarm_view);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, settingsFragment)
                        .addToBackStack(null)  // Optional: Add transaction to back stack for back navigation
                        .commit();

                BottomNavigationView navbar = getActivity().findViewById(R.id.bottomNavigationView);
                navbar.setSelectedItemId(-1);
            }
        });

        recentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, alarmsFragment)
                        .addToBackStack(null)  // Optional: Add transaction to back stack for back navigation
                        .commit();

                BottomNavigationView navbar = getActivity().findViewById(R.id.bottomNavigationView);
                navbar.setSelectedItemId(R.id.alarms);
            }
        });


        viewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNavigationView navbar = getActivity().findViewById(R.id.bottomNavigationView);
                navbar.setSelectedItemId(R.id.alarms);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, alarmsFragment)
                        .addToBackStack(null)  // Optional: Add transaction to back stack for back navigation
                        .commit();
            }
        });

        if(state != null) {
            name.setText(state.getCurrentUser().getUsername() + "!");
//            welcomeMsg.setText("Welcome " + state.getCurrentUser().getUsername() + "!");
        }

        return inflated;
    }
}
