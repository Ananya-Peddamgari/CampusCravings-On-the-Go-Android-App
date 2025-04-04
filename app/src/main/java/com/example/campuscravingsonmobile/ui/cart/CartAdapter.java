package com.example.campuscravingsonmobile.ui.cart;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.ui.Menu.MenuItem;

import java.util.List;
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<MenuItem> cartItems;
    private final OnCartItemRemoveListener listener;

    public interface OnCartItemRemoveListener {
        void onItemRemoved(MenuItem item);
    }

    public CartAdapter(List<MenuItem> cartItems, OnCartItemRemoveListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = cartItems.get(position);
        holder.tvItemName.setText(item.getName());
        holder.tvItemPrice.setText("Rs " + item.getPrice());

        holder.btnRemove.setOnClickListener(v -> {
            listener.onItemRemoved(item); // Notify fragment to update the total cost
            cartItems.remove(position);   // Remove the item from the adapter’s list
            notifyItemRemoved(position);  // Notify adapter of the item removal
            notifyItemRangeChanged(position, cartItems.size()); // Update range for smooth animation
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName, tvItemPrice;
        Button btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
