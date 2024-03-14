package com.example.firstwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavBarActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView navbar;
    AlarmsFragment alarmsFragment;
    ReportsFragment reportsFragment;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        alarmsFragment = new AlarmsFragment();
        reportsFragment = new ReportsFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();

        navbar = findViewById(R.id.bottomNavigationView);
        navbar.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        navbar.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int selectedItemId = item.getItemId();

        if (selectedItemId == R.id.alarms) {
            getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, alarmsFragment)
                        .commit();
            return true;
        } else if(selectedItemId == R.id.reports) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, reportsFragment)
                    .commit();
            return true;
        } else if(selectedItemId == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        } else if(selectedItemId == R.id.profile) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFragment)
                    .commit();
            return true;
        }
        return false;
    }
}