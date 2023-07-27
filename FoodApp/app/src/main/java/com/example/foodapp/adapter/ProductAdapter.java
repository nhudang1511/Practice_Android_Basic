package com.example.foodapp.adapter;

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
import com.example.foodapp.ProductDetail;
import com.example.foodapp.R;
import com.example.foodapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public ProductAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null){
            return;
        }
        holder.txt_product.setText(product.getNameMeal());
        holder.idProduct = product.getIdMeal();
        Glide.with(mContext).load(product.getImgMeal()).into(holder.img_product);
    }

    @Override
    public int getItemCount() {
        if (productList != null){
            return productList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_product;
        private ImageView img_product;
        private String idProduct;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_product = itemView.findViewById(R.id.tv_product);
            img_product = itemView.findViewById(R.id.img_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductDetail.class);
                    intent.putExtra("idProduct", idProduct);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
