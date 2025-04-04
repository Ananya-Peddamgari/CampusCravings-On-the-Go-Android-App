package com.example.campuscravingsonmobile.ui.Menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campuscravingsonmobile.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<MenuItem> menuItems; // Current list displayed in the RecyclerView
    private final List<MenuItem> allItems; // Original full list of items
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onAddToCartClick(MenuItem item);
    }

    public MenuAdapter(List<MenuItem> items, OnItemClickListener listener) {
        this.menuItems = new ArrayList<>(items);
        this.allItems = new ArrayList<>(items); // Preserve original list for filtering
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.tvItemName.setText(item.getName());
        holder.tvItemPrice.setText("Rs " + item.getPrice());

        // Set up the "Add to Cart" button click listener
        holder.btnAddToCart.setOnClickListener(v -> listener.onAddToCartClick(item));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    /**
     * Filter items to show only vegetarian options if showVegOnly is true.
     * Otherwise, it displays all items.
     */
    public void filterVegOnly(boolean showVegOnly) {
        menuItems.clear();
        if (showVegOnly) {
            for (MenuItem item : allItems) {
                if (item.isVeg()) {
                    menuItems.add(item);
                }
            }
        } else {
            menuItems.addAll(allItems);
        }
        notifyDataSetChanged(); // Notify RecyclerView to refresh
    }

    // ViewHolder class to hold views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName, tvItemPrice;
        Button btnAddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
