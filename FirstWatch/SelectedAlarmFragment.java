package com.example.firstwatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectedAlarmFragment extends Fragment {


    TextView eventCommentsbtn, alarmCommentsbtn, alarmAddCommentsbtn;

    public SelectedAlarmFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflated = inflater.inflate(R.layout.fragment_selected_alarm, container, false);

        eventCommentsbtn = inflated.findViewById(R.id.event);
        alarmCommentsbtn = inflated.findViewById(R.id.comments);
        alarmAddCommentsbtn = inflated.findViewById(R.id.addComments);

        eventCommentsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new eventcomments());
            }
        });

        alarmCommentsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new alarmcomments());
            }
        });

        alarmAddCommentsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new alarmaddcomments());
            }
        });


        return inflated;
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout,fragment);
        fragmentTransaction.commit();

    }
}
