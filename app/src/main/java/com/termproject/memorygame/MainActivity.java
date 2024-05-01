package com.termproject.memorygame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Listener for the navigation items, defining actions based on item selected
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Fragment selectedFragment = null;

                // Getting the ID of the clicked menu item
                int id = menuItem.getItemId();
                // Conditional to check which fragment to display based on the item clicked
                if (id == R.id.nav_game) {
                    selectedFragment = new GameFragment();  // If game item is selected
                } else if (id == R.id.nav_help) {
                    selectedFragment = new HelpFragment();  // If help item is selected
                } else if (id == R.id.nav_settings) {
                    selectedFragment = new SettingsFragment(); // If settings item is selected
                }

                // Replacing the current fragment with the selected fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                // Returning true indicates the item selection was handled
                return true;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lock screen orientation to portrait on phones
        /*
        if (isPhone()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }*/

        // Setting the content view to the layout defined in activity_main.xml
        setContentView(R.layout.activity_main);

        // Finding the BottomNavigationView defined in the XML and initializing it
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        // Setting up the listener for item selection on the navigation bar
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // FragmentManager is used to handle fragments in the activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Starting a fragment transaction to add, remove, or replace fragments
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Adding the GameFragment as the default fragment when the activity starts
        fragmentTransaction.add(R.id.fragment_container, new GameFragment());
        // Committing the transaction to make the changes effective
        fragmentTransaction.commit();
    }

    /*
    private boolean isPhone() {
        // Get screen density and size in dp
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float widthDp = displayMetrics.widthPixels / displayMetrics.density;
        float heightDp = displayMetrics.heightPixels / displayMetrics.density;
        float smallestWidthDp = getResources().getConfiguration().smallestScreenWidthDp;

        // Common threshold for distinguishing phones from tablets
        boolean isScreenSizePhone = smallestWidthDp < 600;

        // Consider aspect ratio for additional check (common phone aspect ratio is around 16:9)
        float aspectRatio = Math.max(widthDp, heightDp) / Math.min(widthDp, heightDp);
        boolean isAspectRatioPhone = aspectRatio > 1.5;  // Adjust this value based on typical phone aspect ratios

        // Return true if both conditions suggest the device is a phone
        return isScreenSizePhone && isAspectRatioPhone;
    }*/

}
