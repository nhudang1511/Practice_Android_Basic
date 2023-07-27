package com.example.retrofitloginsignup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitloginsignup.Model.Product;
import com.example.retrofitloginsignup.R;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product,null);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProduct.setText(product.getName());
        Glide.with(context).load(product.getImages()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return productList==null ? 0: productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        //TextView tvProduct, ImageView imgProduct được khai báo là các thuộc tính của ViewHolder
        public TextView tvProduct;
        public ImageView imgProduct;
        public ProductViewHolder(@NonNull View itemView){
            super(itemView);
            tvProduct = (TextView) itemView.findViewById(R.id.tvProduct);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Bạn đã chọn " + tvProduct.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
