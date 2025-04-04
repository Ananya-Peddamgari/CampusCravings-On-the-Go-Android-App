    package com.example.campuscravingsonmobile.ui.Menu;

    import android.os.Bundle;
    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Toast;

    import com.example.campuscravingsonmobile.R;
    import com.example.campuscravingsonmobile.ui.cart.CartManager;
    import com.google.android.material.switchmaterial.SwitchMaterial;

    import java.util.ArrayList;
    import java.util.List;

    public class MenuFragment extends Fragment implements MenuAdapter.OnItemClickListener {

        private RecyclerView menuRecyclerView;
        private MenuAdapter menuAdapter;
        private List<MenuItem> menuItems;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_menu, container, false);

            // Initialize RecyclerView
            menuRecyclerView = view.findViewById(R.id.menuRecyclerView);
            menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Load and Set Menu Data
            menuItems = getMenuItems();
            menuAdapter = new MenuAdapter(menuItems, this);
            menuRecyclerView.setAdapter(menuAdapter);

            // Filter Switch
            SwitchMaterial vegSwitch = view.findViewById(R.id.vegSwitch);
            vegSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> menuAdapter.filterVegOnly(isChecked));

            return view;
        }

        private List<MenuItem> getMenuItems() {
            List<MenuItem> items = new ArrayList<>();
            items.add(new MenuItem("Burger", 89, false));
            items.add(new MenuItem("Dosa", 120, true));
            items.add(new MenuItem("Fries", 99, true));
            items.add(new MenuItem("Veg Biryani", 150, true));
            items.add(new MenuItem("Chicken Tikka", 200, false));
            items.add(new MenuItem("Paneer Roll", 100, true));
            items.add(new MenuItem("Cheese Roll", 110, true));
            items.add(new MenuItem("Chicken Biryani", 250, false));
            items.add(new MenuItem("Pastries", 60, true));
            items.add(new MenuItem("Brownies", 60, true));
            return items;
        }

        @Override
        public void onAddToCartClick(MenuItem item) {
            CartManager.getInstance().addItemToCart(item);
            Toast.makeText(getContext(), item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        }
    }
