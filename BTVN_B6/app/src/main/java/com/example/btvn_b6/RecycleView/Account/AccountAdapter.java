package com.example.btvn_b6.RecycleView.Account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btvn_b6.R;
import com.example.btvn_b6.RecycleView.Home.Product;
import com.example.btvn_b6.SQLite.DAO.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    List<Product> products;
    Context context;
    OnClickCountProductCart onClickCountProductCart;
    ProductCart productCart;

    public void setOnClickCountProductCart(OnClickCountProductCart onClickCountProductCart) {
        this.onClickCountProductCart = onClickCountProductCart;
    }

    public AccountAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_product_cart,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        productCart = new ProductCart(parent.getContext());
        productCart.Open();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = products.get(position);
        Glide.with(context).load(product.getUrlImgProduct()).into(holder.img_product_cart);
        holder.tv_name_product_cart.setText(product.getNameProduct());
        holder.tv_price_product_cart.setText(product.getPriceProduct());
        holder.tv_rating_product_cart.setText(product.getRatingProduct()+"");
        holder.tv_count_product_cart.setText(product.getCountProduct()+"");
        holder.rtb_rating_product_cart.setRating(product.getRatingProduct());
        holder.ibtn_minus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product.getCountProduct() == 1){
                    productCart.DelProductCart(product.getId());
                }
                else{
                    holder.tv_count_product_cart.setText(product.getCountProduct()+"");
                }
                products.get(position).setCountProduct(product.getCountProduct()-1);
                onClickCountProductCart.onClickMinusProductCart(product,position);
            }
        });
        holder.ibtn_plus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.get(position).setCountProduct(product.getCountProduct()+1);
                holder.tv_count_product_cart.setText(product.getCountProduct()+"");
                onClickCountProductCart.onClickPlusProductCart(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product_cart;
        ImageButton ibtn_minus_cart, ibtn_plus_cart;
        TextView tv_name_product_cart, tv_price_product_cart, tv_rating_product_cart, tv_count_product_cart;
        RatingBar rtb_rating_product_cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product_cart = itemView.findViewById(R.id.img_product_cart);
            ibtn_minus_cart = itemView.findViewById(R.id.ibtn_minus_cart);
            ibtn_plus_cart = itemView.findViewById(R.id.ibtn_plus_cart);
            tv_name_product_cart = itemView.findViewById(R.id.tv_name_product_cart);
            tv_price_product_cart = itemView.findViewById(R.id.tv_price_product_cart);
            tv_rating_product_cart = itemView.findViewById(R.id.tv_rating_product_cart);
            tv_count_product_cart = itemView.findViewById(R.id.tv_count_product_cart);
            rtb_rating_product_cart = itemView.findViewById(R.id.rtb_rating_product_cart);
        }
    }
}
