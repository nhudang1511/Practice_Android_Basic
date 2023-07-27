package com.example.exercise1.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exercise1.ProductsOfCategory;
import com.example.exercise1.R;
import com.example.exercise1.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private List<Category> categoryList;
    private Context mContext;

    public CategoryAdapter(List<Category> categoryList, Context mContext) {
        this.categoryList = categoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if(category == null){
            return;
        }
        holder.tv_category.setText(String.valueOf(category.getName()));
        //Picasso.get().load(category.getImages()).into(holder.img_category);
        Glide.with(mContext)
                .load(category.getImages())
                .into(holder.img_category);


        holder.idCategory = Integer.parseInt(category.getId());
    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_category;
        private TextView tv_category;
        private int idCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = itemView.findViewById(R.id.img_category);
            tv_category = itemView.findViewById(R.id.tv_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ProductsOfCategory.class);
                    intent.putExtra("idCategory", idCategory);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
