package com.example.btvn_4.RecycleViewAdapterCart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_4.MenuActivity;
import com.example.btvn_4.R;
import com.example.btvn_4.RecycleViewAdapter.OnClickItemProduct;

import java.util.List;

public class RecycleViewAdapterCart extends RecyclerView.Adapter<RecycleViewAdapterCart.ViewHolder> {
    List<ProductCart> productCarts;
    OnClickItemProductCart onClickItemProductCart;

    public void setOnClickItemProductCart(OnClickItemProductCart onClickItemProductCart) {
        this.onClickItemProductCart = onClickItemProductCart;
    }

    public RecycleViewAdapterCart(List<ProductCart> productCarts) {
        this.productCarts = productCarts;

    }

    @NonNull
    @Override
    public RecycleViewAdapterCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_recycleview_addapter_cart,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewAdapterCart.ViewHolder holder, final int position) {
        final ProductCart productCart = productCarts.get(position);
        holder.img_Product_Cart.setImageResource(productCart.getImgProductCart());
        holder.tv_name_Product_Cart.setText(productCart.getNameProductCart());
        holder.tv_price_Product_Cart.setText(productCart.getPriceProductCart());
        holder.tv_number_cart.setText(productCart.getCountProductCart() + "");
        holder.ibtn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemProductCart.onClickMinusProductCart(productCart,position,productCarts.size());
                if(productCart.getCountProductCart()  > 0) {
                    productCart.setCountProductCart((productCart.getCountProductCart() - 1));
                    holder.tv_number_cart.setText((productCart.getCountProductCart()) + "");
                }
            }
        });
        holder.ibtn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemProductCart.onClickPlusProductCart(productCart);
                productCart.setCountProductCart((productCart.getCountProductCart() + 1));
                holder.tv_number_cart.setText((productCart.getCountProductCart()) + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return productCarts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_Product_Cart;
        ImageButton ibtn_minus, ibtn_plus;
        TextView tv_name_Product_Cart,tv_price_Product_Cart,tv_number_cart;
        RelativeLayout rl_cart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Product_Cart = itemView.findViewById(R.id.img_product_cart);
            ibtn_minus = itemView.findViewById(R.id.ibtn_minus);
            ibtn_plus = itemView.findViewById(R.id.ibtn_plus);
            tv_name_Product_Cart = itemView.findViewById(R.id.tv_inforProduct_cart);
            tv_price_Product_Cart = itemView.findViewById(R.id.tv_priceProduct_cart);
            tv_number_cart = itemView.findViewById(R.id.tv_number_cart);
            rl_cart = itemView.findViewById(R.id.rl_cart);
        }
    }
}
