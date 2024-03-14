package com.example.firstwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Hide the title and flag
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout btnContainer = (LinearLayout) findViewById(R.id.btn_container);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout recentContainer = (LinearLayout) findViewById(R.id.recent_container);


        for(int i = 0; i < recentContainer.getChildCount(); i++) {
            View view = btnContainer.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(0, 40, 0, 40);

//            view.setPadding(60,60,60,60);
            view.setLayoutParams(layoutParams);
        }

        for(int i = 0; i < btnContainer.getChildCount(); i++) {
            View view = btnContainer.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            if(i == 0) {
                layoutParams.setMargins(60, 0, 10, 0);
            }else if(i == btnContainer.getChildCount() - 1) {
                layoutParams.setMargins(10, 0, 60, 0);
            }else {
                layoutParams.setMargins(10, 0, 10, 0);

            }

            view.setPadding(60,60,60,60);
            view.setLayoutParams(layoutParams);
        }

        Button dashboardViewMore = (Button) findViewById(R.id.dashboard_view);
        dashboardViewMore.setPaintFlags(dashboardViewMore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView recentViewMore = (TextView) findViewById(R.id.recentAlarm_view);
        recentViewMore.setPaintFlags(dashboardViewMore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout notiBtn = (LinearLayout) findViewById(R.id.notifications_btn);
//
//        notiBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HomeActivity.this, "Test" , Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}