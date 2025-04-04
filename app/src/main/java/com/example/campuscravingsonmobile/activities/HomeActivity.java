package com.example.campuscravingsonmobile.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campuscravingsonmobile.ui.cart.CartFragment;
import com.example.campuscravingsonmobile.ui.home.HomeFragment;
//import com.example.campuscravingsonmobile.ui.Menu.MenuFragment;

import com.example.campuscravingsonmobile.ui.profile.ProfileFragment;
import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.RecommendedFood;
import com.example.campuscravingsonmobile.RecommendedFoodAdapter;
import com.example.campuscravingsonmobile.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText etSearchProduct;
    private RecyclerView rvRecommendedFood;
    private RecommendedFoodAdapter recommendedFoodAdapter;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView3);

        // Set the default fragment when the app starts (optional)
//        loadFragment(new HomeFragment());

        // Set up listener for BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Handle navigation item clicks using if-else
                if (item.getItemId() == R.id.nav_home1) {
                    selectedFragment = new HomeFragment();
                }
                  else if (item.getItemId() == R.id.nav_profile1) {
                    selectedFragment = new ProfileFragment();

                } else if (item.getItemId() == R.id.nav_cart1) {
                    selectedFragment = new CartFragment();
                }

                // Load the selected fragment
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }

                return true; // Return true to display the selected menu item as active
            }
        });
    }


    // Method to load fragments into the container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment) // R.id.frameLayout is the container for fragments
                .commit();

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//
//        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {
//
//            int itemId = item.getItemId();
//
//            if (itemId == R.id.nav_home1) {
//                replaceFragment(new HomeFragment());
//            } else if (itemId == R.id.nav_menu1) {
//                replaceFragment(new MenuFragment());
//            } else if (itemId == R.id.nav_order1) {
//                replaceFragment(new OrderFragment());
//            }
//            else if(itemId == R.id.nav_profile1) {
//                replaceFragment(new ProfileFragment());
//            }
//            return true;
//        });
//        }
//
//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_Layout, fragment);
//        fragmentTransaction.commit();

//        binding.bottomNavigationView2.setOnItemSelectedListener(item ->{
//            // Initialize the fragment
//            Fragment selectedFragment = null;
//
//            // Use if-else condition instead of switch-case
//            if (item.getItemId() == R.id.nav_home1) {
//                selectedFragment = new Fragment(); // Navigate to HomeFragment
//            } else if (item.getItemId() == R.id.nav_menu1) {
//                selectedFragment = new MenuFragment(); // Navigate to MenuFragment
//            } else if (item.getItemId() == R.id.nav_order1) {
//                selectedFragment = new OrderFragment(); // Navigate to OrderFragment
//            } else if (item.getItemId() == R.id.nav_profile1) {
//                selectedFragment = new ProfileFragment(); // Navigate to ProfileFragment
//            }
//
//            // Check if a valid fragment was selected
//            if (selectedFragment != null) {
//                // Replace the fragment in the FrameLayout
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame_Layout, selectedFragment)
//                        .commit();
//            }
//
//            return true;
//        });
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
////            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int itemId = item.getItemId();
//
//                if (itemId == R.id.nav_home1) {
//                    Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
//                    startActivity(homeIntent);
//                    return true;
//                } else if (itemId == R.id.nav_menu1) {
//                    Intent dashboardIntent = new Intent(HomeActivity.this, MenuActivity.class);
//                    startActivity(dashboardIntent);
//                    return true;
//                } else if (itemId == R.id.nav_order1) {
//                    Intent notificationsIntent = new Intent(HomeActivity.this, OrderActivity.class);
//                    startActivity(notificationsIntent);
//                    return true;
//                } else if (itemId == R.id.nav_profile1) {
//                    Intent settingsIntent = new Intent(HomeActivity.this, ProfileActivity.class);
//                    startActivity(settingsIntent);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });

        // Initialize UI elements
            etSearchProduct = findViewById(R.id.etSearchProduct);
            ImageView ivSearch = findViewById(R.id.ivSearch);
            CardView promotionBanner = findViewById(R.id.promotionBanner);
            TextView tvWelcome = findViewById(R.id.textView);

            // Retrieve and display the welcome message
            String fullName = getIntent().getStringExtra("FULL_NAME");
            tvWelcome.setText(fullName != null ? "Welcome  " + fullName + "!" : "Welcome!");

            // Set up search functionality
            ivSearch.setOnClickListener(v -> {
                String query = etSearchProduct.getText().toString();
                if (!query.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
                }
            });

            // Set up promotion banner click listener
            promotionBanner.setOnClickListener(v ->
                    Toast.makeText(HomeActivity.this, "Order Now clicked!", Toast.LENGTH_SHORT).show()
            );

            rvRecommendedFood = findViewById(R.id.rvRecommendedFood);
            setupRecommendedFoodRecyclerView();
        }

        private void setupRecommendedFoodRecyclerView () {
            List<RecommendedFood> recommendedFoodList = new ArrayList<>();
            recommendedFoodList.add(new RecommendedFood("Paneer roll", "Healthy and tasty", 120, R.drawable.biryani));
            recommendedFoodList.add(new RecommendedFood("Veggie Pizza", "A delicious pizza topped with fresh vegetables and melted cheese.", 10.99, R.drawable.ramen));
            recommendedFoodList.add(new RecommendedFood("Chocolate Brownie", "Rich, fudgy brownie with a perfectly crisp top layer.", 5.99, R.drawable.cake));
            recommendedFoodList.add(new RecommendedFood("Caesar Salad", "Crisp romaine lettuce, croutons, and parmesan cheese tossed in Caesar dressing.", 8.99, R.drawable.cake));
            recommendedFoodList.add(new RecommendedFood("Cheeseburger", "Juicy beef patty topped with melted cheese, lettuce, tomato, and special sauce.", 9.99, R.drawable.chocolate));
            recommendedFoodList.add(new RecommendedFood("Iced Latte", "Smooth espresso mixed with cold milk and served over ice.", 4.99, R.drawable.chocolate));
//
//            RecyclerView recyclerView = findViewById(R.id.rvRecommendedFood);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//            recyclerView.setLayoutManager(layoutManager);

            recommendedFoodAdapter = new RecommendedFoodAdapter(recommendedFoodList);
            rvRecommendedFood.setLayoutManager(new LinearLayoutManager(this));
            rvRecommendedFood.setAdapter(recommendedFoodAdapter);

        }
    }

