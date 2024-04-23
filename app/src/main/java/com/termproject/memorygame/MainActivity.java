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
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // I'm using fragment manager and transaction to do the fragment switch
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // I'm adding the game fragment as the default fragment when the activity starts
        fragmentTransaction.add(R.id.fragment_container, new GameFragment());
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            menuItem -> {
                Fragment selectedFragment = null;

                int id = menuItem.getItemId();
                if (id == R.id.nav_game) {
                    selectedFragment = new GameFragment();
                } else if (id == R.id.nav_help) {
                    selectedFragment = new HelpFragment();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            };

}
