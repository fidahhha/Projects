package com.example.firstwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedAlarmActivity extends AppCompatActivity {

    TextView eventCommentsbtn, alarmCommentsbtn, alarmAddCommentsbtn;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_alarm);

        // Initialize the ViewPager and PagerAdapter
        ViewPager viewPager = findViewById(R.id.viewPager);
        AlarmPagerAdapter pagerAdapter = new AlarmPagerAdapter(getSupportFragmentManager());

        // Set the adapter to the ViewPager
        viewPager.setAdapter(pagerAdapter);

        // Set the default fragment
        viewPager.setCurrentItem(0);

        // Swipe between fragments
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Handle when a new fragment is selected
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


// Display the "Swipe for more" toast with a margin at the top
        int marginInPixels = 200; // Adjust this value as needed
        Toast toast = Toast.makeText(this, "Swipe for more", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, marginInPixels);
        toast.show();


        eventCommentsbtn = findViewById(R.id.event);
        alarmCommentsbtn = findViewById(R.id.comments);
        alarmAddCommentsbtn = findViewById(R.id.addComments);

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
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout,fragment);
        fragmentTransaction.commit();

    }
}