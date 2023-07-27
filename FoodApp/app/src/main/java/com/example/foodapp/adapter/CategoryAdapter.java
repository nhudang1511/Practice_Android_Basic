package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.ListProductOfCategory;
import com.example.foodapp.R;
import com.example.foodapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;
    private Context mContext;

    public CategoryAdapter(List<Category> categoryList, Context mContext) {
        this.categoryList = categoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, parent, false);
        return new CategoryViewHolder(view);
    }


    //Phương thức onBindViewHolder() trong Adapter được gọi mỗi khi một ViewHolder
    //cần được hiển thị cho một vị trí cụ thể trong RecyclerView
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null){
            return;
        }
        holder.txt_category.setText(category.getName());
        holder.Id_category = Integer.parseInt(category.getId());
        Glide.with(mContext).load(category.getImages()).into(holder.img_category);
    }

    @Override
    public int getItemCount() {
        if (categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_category;
        private TextView txt_category;

        private int Id_category;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = itemView.findViewById(R.id.img_category);
            txt_category = itemView.findViewById(R.id.tv_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListProductOfCategory.class);
                    intent.putExtra("idCategory", Id_category);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
