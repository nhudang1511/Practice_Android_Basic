package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    private List<CategoryModel> mCategoryModel;
    private Context mContext;

    public CategoryAdapter(List<CategoryModel> mCategoryModel, Context mContext) {
        this.mCategoryModel = mCategoryModel;
        this.mContext = mContext;
    }

    // Tạo 1 ViewHolder mới
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mCategoryModel != null) {
            return mCategoryModel.size();
        }
        return 0;
    }

    // Liên kết ViewHolder với dữ liệu
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel category = mCategoryModel.get(position);
        if (category == null) {
            return;
        }
        // Xét dữ liệu cho thành phần
        else {
            holder.tvCategoryName.setText(category.getName());

            // Load ảnh với GLide
            Glide.with(mContext)
                    .load(category.getImages())
                    .into(holder.imgCategoryImages);
        }
    }

    // B1: tạo RecyclerView.ViewHolder
    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        // Khai báo thành phần có trong item View
        private ImageView imgCategoryImages;
        private TextView tvCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ View Item
            imgCategoryImages = (ImageView) itemView.findViewById(R.id.imgCategoryImages);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
