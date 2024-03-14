package com.example.firstwatch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.firstwatch.childalarm;
import com.example.firstwatch.firstalarm;

public class AlarmPagerAdapter extends FragmentPagerAdapter {

    public AlarmPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new firstalarm();
        } else {
            return new childalarm();
        }
    }

    @Override
    public int getCount() {
        return 2; // You have two fragments
    }
}
