// com/example/campuscravingsonmobile/ui/menu/MenuItem.java
package com.example.campuscravingsonmobile.ui.Menu;

public class MenuItem {
    private String name;
    private double price;
    private boolean isVeg;

    public MenuItem(String name, double price, boolean isVeg) {
        this.name = name;
        this.price = price;
        this.isVeg = isVeg;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVeg() {
        return isVeg;
    }
}
