package com.example.travelexpenses;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        Fragment homeFragment = new HomeFragment();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.settings, homeFragment);
        transaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e("talentive", "nav item has been clicked");

                int id = item.getItemId();

                if (id == R.id.my_home) {
                    Log.e("tale", "home");
                    item.setChecked(true);
                    // Create new fragment and transaction
                    Fragment homeFragment = new HomeFragment();
                    final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.settings, homeFragment);
                    transaction.commit();
                } else if (id == R.id.settings_button){
                    Log.e("tale", "settings");

                    // Create new fragment and transaction
                    Fragment homeFragment = new SettingsFragment();
                    final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.settings, homeFragment);

                    transaction.commit();

                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null) {
                        actionBar.setDisplayHomeAsUpEnabled(true);
                    }
                }
                return true;
            }
        });


    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}