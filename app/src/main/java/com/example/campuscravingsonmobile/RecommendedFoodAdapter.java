package com.example.campuscravingsonmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import java.util.List;


public class RecommendedFoodAdapter extends RecyclerView.Adapter<RecommendedFoodAdapter.FoodViewHolder> {

    private final List<RecommendedFood> foodList;

    public RecommendedFoodAdapter(List<RecommendedFood> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommended_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        RecommendedFood food = foodList.get(position);
        holder.foodImage.setImageResource(food.getImageResId());
        holder.foodName.setText(food.getName());
        holder.foodDescription.setText(food.getDescription());
        holder.foodPrice.setText(String.format("Rs%.2f", food.getPrice()));

        holder.addToCartButton.setOnClickListener(v -> {
            // Implement add to cart functionality here
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodDescription;
        TextView foodPrice;
        MaterialButton addToCartButton;

        FoodViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.ivFoodImage);
            foodName = itemView.findViewById(R.id.tvFoodName);
            foodDescription = itemView.findViewById(R.id.tvFoodDescription);
            foodPrice = itemView.findViewById(R.id.tvFoodPrice);
            addToCartButton = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}