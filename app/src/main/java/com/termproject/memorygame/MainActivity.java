package com.termproject.memorygame;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

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

        // Setting up the listener for item selection on the navigation bar using the new method
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_game) {
                selectedFragment = new GameFragment(); //If game fragment
            } else if (id == R.id.nav_help) {
                selectedFragment = new HelpFragment(); //If help fragment
            } else if (id == R.id.nav_settings) {
                selectedFragment = new SettingsFragment(); //If settings fragment
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }

            return true; // Returning true indicates the item selection was handled
        });

        // FragmentManager is used to handle fragments in the activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new GameFragment()).commit();
        }
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
