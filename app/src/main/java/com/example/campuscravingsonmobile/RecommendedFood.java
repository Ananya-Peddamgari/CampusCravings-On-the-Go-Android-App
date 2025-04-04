package com.example.campuscravingsonmobile;

public class RecommendedFood {
    private String name;
    private String description;
    private double price;
    private int imageResId;
    private String category; // New field for category

    // Updated constructor to include the category parameter
    public RecommendedFood(String name, String description, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getCategory() {
        return category;
    }
}
