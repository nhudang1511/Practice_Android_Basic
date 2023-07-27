package com.example.retrofitloginsignup.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitloginsignup.HomeActivity;
import com.example.retrofitloginsignup.Model.Category;
import com.example.retrofitloginsignup.ProductCategoryActivity;
import com.example.retrofitloginsignup.ProfileActivity;
import com.example.retrofitloginsignup.R;

import java.net.URI;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<Category> array;

    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //đổ dữ liệu vào View, view này nằm ở layout xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_categories, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public TextView tenSp;

        int id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //ánh xạ từ layout sang biến với tên biến là images, tenSp
            images = (ImageView) itemView.findViewById(R.id.imgCate);
            tenSp = (TextView) itemView.findViewById(R.id.tvCate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //Tạo một Intent để mở một Activity mới là ProductCategoryActivity.
                    Intent intent = new Intent(context, ProductCategoryActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
        });
    }

}

    @Override
    //Phương thức onBindViewHolder() trong Adapter được gọi mỗi khi một ViewHolder
    //cần được hiển thị cho một vị trí cụ thể trong RecyclerView
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = array.get(position);
        holder.tenSp.setText(category.getName());
        Glide.with(context).load(category.getImages()).into(holder.images);
        holder.id= category.getId();
    }

    // trả về số lượng phần tử hiển thị trên danh sách (list)
    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }
}
