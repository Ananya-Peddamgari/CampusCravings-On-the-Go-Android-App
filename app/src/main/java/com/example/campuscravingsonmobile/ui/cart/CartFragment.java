package com.example.campuscravingsonmobile.ui.cart;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.campuscravingsonmobile.R;
import com.example.campuscravingsonmobile.ui.Menu.MenuItem;

public class CartFragment extends Fragment implements CartAdapter.OnCartItemRemoveListener {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalCostTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        totalCostTextView = view.findViewById(R.id.totalCostTextView);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter
        cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems(), this);
        cartRecyclerView.setAdapter(cartAdapter);

        // Initial total cost display
        updateTotalCost();

        return view;
    }

    private void updateTotalCost() {
        double totalCost = CartManager.getInstance().getTotalCost();
        totalCostTextView.setText("Total: Rs " + totalCost);
    }

    @Override
    public void onItemRemoved(MenuItem item) {
        CartManager.getInstance().removeItemFromCart(item);
        updateTotalCost();
        cartAdapter.notifyDataSetChanged();
    }
}
