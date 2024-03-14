package com.example.firstwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Find the TextView inside the layout
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView logoutTextView = findViewById(R.id.logoutBtn);

        // Set an OnClickListener for the logoutTextView
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the MainActivity
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                intent.putExtra("logoutMessage", "You have been logged out!");
                startActivity(intent);
            }
        });
    }
}
