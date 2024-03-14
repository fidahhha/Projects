package com.example.firstwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificationsAlarmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_alarms);


        // Create a list of Date objects (you can replace these with your actual dates)
        List<Date> dateList = new ArrayList<>();
        dateList.add(new Date()); // Current date
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1))); // Yesterday
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2))); // 2 days ago
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3)));
        dateList.add(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(4)));

        // Create and set the custom adapter for your ListView
        CustomAdapter adapter = new CustomAdapter(this, dateList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        adapter.setOnRectangleClickListener(new CustomAdapter.OnRectangleClickListener() {
            @Override
            public void onRectangleClick(int position) {
                // Handle the click event for the rectangle at the specified position
                // You can perform actions like opening a detail view, displaying a toast, etc.
                Toast.makeText(NotificationsAlarmsActivity.this, "Rectangle at position " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }



}