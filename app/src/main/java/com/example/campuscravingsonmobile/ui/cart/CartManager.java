package com.example.campuscravingsonmobile.ui.cart;
import com.example.campuscravingsonmobile.ui.Menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<MenuItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItemToCart(MenuItem item) {
        cartItems.add(item);
    }

    public void removeItemFromCart(MenuItem item) {
        cartItems.remove(item);
    }

    public List<MenuItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public double getTotalCost() {
        double total = 0;
        for (MenuItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
