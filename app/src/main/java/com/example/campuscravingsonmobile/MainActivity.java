package com.example.campuscravingsonmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
//import com.example.campuscravingsonmobile.ui.Menu.MenuFragment;
import com.example.campuscravingsonmobile.ui.Menu.MenuFragment;
import com.example.campuscravingsonmobile.ui.cart.CartFragment;
import com.example.campuscravingsonmobile.ui.home.HomeFragment;
import com.example.campuscravingsonmobile.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String fullName;
    private String email;
    private String studentNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve data from intent
        Intent intent = getIntent();
        fullName = intent.getStringExtra("FULL_NAME");
        email = intent.getStringExtra("EMAIL");
        studentNumber = intent.getStringExtra("STUDENT_NUMBER");

        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (savedInstanceState == null) {  // To prevent reloading on orientation changes
            loadFragment(new HomeFragment());
        }

        // Set up listener for BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Handle navigation item clicks using if-else
                if (item.getItemId() == R.id.nav_home1) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.nav_cart1) {
                    selectedFragment = new CartFragment();
                } else if (item.getItemId() == R.id.nav_profile1) {

                    // Create the ProfileFragment and pass the data
                    ProfileFragment profileFragment = new ProfileFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("FULL_NAME", fullName);
                    bundle.putString("EMAIL", email);
                    bundle.putString("STUDENT_NUMBER", studentNumber);
                    profileFragment.setArguments(bundle);
                    selectedFragment = profileFragment; // Set the selected fragment to profileFragment
                } else if (item.getItemId() == R.id.nav_menu1) {
                    selectedFragment = new MenuFragment();
                }

                // Load the selected fragment
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }
                return true; // Return true to display the selected menu item as active
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment) // R.id.frameLayout is the container for fragments
                .commit();
    }
}