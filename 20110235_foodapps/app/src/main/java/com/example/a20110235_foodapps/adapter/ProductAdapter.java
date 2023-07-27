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
import com.example.a20110235_foodapps.DetailProduct;
import com.example.a20110235_foodapps.ListProductfCate;
import com.example.a20110235_foodapps.R;
import com.example.a20110235_foodapps.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    List<Product> array;
    Context context;

    public ProductAdapter(List<Product> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_product;
        public TextView txt_product;
        private int id_product;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product=itemView.findViewById(R.id.image_product);
            txt_product=itemView.findViewById(R.id.tvNameProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product"+txt_product.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailProduct.class);
                    intent.putExtra("idProduct",id_product);
                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Product product = array.get(position);
        holder.txt_product.setText(product.getStrMeal());
        holder.id_product=product.getIdMeal();
        Glide.with(context)
                .load(product.getStrMealThumb())
                .into(holder.img_product);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
