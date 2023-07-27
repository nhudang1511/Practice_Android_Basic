package com.example.a20110235_foodapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.a20110235_foodapps.ListProductfCate;
import com.example.a20110235_foodapps.R;
import com.example.a20110235_foodapps.model.Category;

import java.util.List;

public class CategoryAdaper extends RecyclerView.Adapter<CategoryAdaper.MyViewHolder> {
    private List<Category> array;
    private Context context;

    public CategoryAdaper(List<Category> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_cate;
        public TextView txt_cate;
        private int id_cate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cate=itemView.findViewById(R.id.image_product);
            txt_cate=itemView.findViewById(R.id.tvNameProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn category"+txt_cate.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ListProductfCate.class);
                    intent.putExtra("idCategory",id_cate);
                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Category category =array.get(position);
        holder.txt_cate.setText(category.getName());
        holder.id_cate=category.getId();
        Glide.with(context)
                .load(category.getImages())
                .into(holder.img_cate);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
