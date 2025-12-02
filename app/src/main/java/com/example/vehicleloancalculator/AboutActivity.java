package com.example.vehicleloancalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AboutActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about); // References the updated activity_about.xml

        // New: Bind BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set the default selected item to 'About' as this is the AboutActivity
        // This relies on the menu ID 'menu_about' being defined in res/menu/bottom_nav_menu.xml
        bottomNavigationView.setSelectedItemId(R.id.menu_about);

        // New: Handle Bottom Navigation Item Selection
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_about) {
                    // Stay on the About screen
                    return true;
                } else if (itemId == R.id.menu_home) {
                    // Navigate back to MainActivity (the calculator screen).
                    // finish() is used because MainActivity is already in the back stack.
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    // Ensures that when the user presses the device's back button,
    // the 'About' tab remains visually selected if they stay in this activity.
    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_about);
        }
    }
}