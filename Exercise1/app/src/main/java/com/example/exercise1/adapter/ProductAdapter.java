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
import com.example.exercise1.ProductDetailActivity;
import com.example.exercise1.R;
import com.example.exercise1.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product == null){
            return;
        }
        holder.tv_product.setText(String.valueOf(product.getStrMeal()));
        Glide.with(context)
                .load(product.getStrMealThumb())
                .into(holder.img_product);
        holder.idProduct = product.getIdMeal();
    }

    @Override
    public int getItemCount() {
        if(productList != null){
            return productList.size();
        }
        return 0;
    }

    public  class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_product;
        private ImageView img_product;
        private String idProduct;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_product = itemView.findViewById(R.id.tv_product);
            img_product = itemView.findViewById(R.id.img_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("idProduct", idProduct);
                    context.startActivity(intent);
                }
            });
        }
    }
}
