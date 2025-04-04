package com.example.campuscravingsonmobile.ui.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.RecommendedFood;
import com.example.campuscravingsonmobile.RecommendedFoodAdapter;
import com.example.campuscravingsonmobile.ui.cart.CartFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvRecommendedFood;
    private ImageView ivCart; // Add the cart icon reference

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI elements
        EditText etSearchProduct = view.findViewById(R.id.etSearchProduct);
        ImageView ivSearch = view.findViewById(R.id.ivSearch);
        ivCart = view.findViewById(R.id.ivCart); // Initialize ivCart here
        CardView promotionBanner = view.findViewById(R.id.promotionBanner);
        TextView tvWelcome = view.findViewById(R.id.textView);
        rvRecommendedFood = view.findViewById(R.id.rvRecommendedFood);

        // Retrieve and display the welcome message
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("FULL_NAME")) {
            String fullName = arguments.getString("FULL_NAME");
            tvWelcome.setText("Welcome, " + fullName + "!");
        } else {
            tvWelcome.setText("Welcome!");
        }

        // Set up search functionality
        ivSearch.setOnClickListener(v -> {
            String query = etSearchProduct.getText().toString();
            if (!query.isEmpty()) {
                Toast.makeText(getActivity(), "Searching for: " + query, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up promotion banner click listener
        promotionBanner.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Order Now clicked!", Toast.LENGTH_SHORT).show();
        });

        // Set up Cart button click listener to navigate to CartFragment
        ivCart.setOnClickListener(v -> {
            // Begin fragment transaction to navigate to CartFragment
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new CartFragment()); // Replace with CartFragment
            transaction.addToBackStack(null); // Add to back stack for back navigation
            transaction.commit(); // Commit the transaction to perform the navigation
        });

        setupRecommendedFoodRecyclerView();

        return view;
    }

    private void setupRecommendedFoodRecyclerView() {
        // Set up the recommended food items
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood("Paneer Roll", "Healthy and tasty", 120, R.drawable.biryani));
        recommendedFoodList.add(new RecommendedFood("Veggie Pizza", "A delicious pizza topped with fresh vegetables and melted cheese.", 10.99, R.drawable.ramen));
        recommendedFoodList.add(new RecommendedFood("Chocolate Brownie", "Rich, fudgy brownie with a perfectly crisp top layer.", 5.99, R.drawable.cake));
        recommendedFoodList.add(new RecommendedFood("Caesar Salad", "Crisp romaine lettuce, croutons, and parmesan cheese tossed in Caesar dressing.", 8.99, R.drawable.cake));
        recommendedFoodList.add(new RecommendedFood("Cheeseburger", "Juicy beef patty topped with melted cheese, lettuce, tomato, and special sauce.", 9.99, R.drawable.chocolate));
        recommendedFoodList.add(new RecommendedFood("Iced Latte", "Smooth espresso mixed with cold milk and served over ice.", 4.99, R.drawable.chocolate));

        RecommendedFoodAdapter recommendedFoodAdapter = new RecommendedFoodAdapter(recommendedFoodList);
        rvRecommendedFood.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRecommendedFood.setAdapter(recommendedFoodAdapter);
    }
}
