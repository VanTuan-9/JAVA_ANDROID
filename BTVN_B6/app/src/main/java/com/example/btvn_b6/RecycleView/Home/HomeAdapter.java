package com.example.btvn_b6.RecycleView.Home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btvn_b6.R;
import com.example.btvn_b6.SQLite.DAO.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<Product> products;
    Context context;
    List<Product> productsCart = new ArrayList<>();
    ProductCart productCart;
    Toast toast = null;

    public HomeAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_product,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        productCart = new ProductCart(parent.getContext());
        productCart.Open();
//        productCart.Open();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, final int position) {
        final Product product = products.get(position);
        Glide.with(context).load(product.getUrlImgProduct()).into(holder.img_product_home);
        holder.tv_name_product_home.setText(product.getNameProduct());
        holder.tv_price_product_home.setText(product.getPriceProduct());
        holder.tv_rating_product_home.setText(product.getRatingProduct() + "");
        holder.rtb_rating_product_home.setRating(product.getRatingProduct());
        holder.btn_buy_product_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsCart.clear();
                productsCart.addAll(productCart.GetProductsCart());
                if(productsCart.size() != 0){
                    for(int i = 0 ;i < productsCart.size() ; i++){
                        Log.d("ID1",product.getId()+"");
                        Log.d("ID2",productsCart.get(i).getId()+"");
                        if(product.getId() == productsCart.get(i).getId())
                        {
                            toast = Toast.makeText(context, "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }
                    }
                }
                toast = Toast.makeText(context, "Đã thêm!", Toast.LENGTH_SHORT);
                toast.show();

//                productsCart.add(product);
                productCart.AddProductCart(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product_home;
        TextView tv_name_product_home;
        TextView tv_price_product_home;
        RatingBar rtb_rating_product_home;
        TextView tv_rating_product_home;
        Button btn_buy_product_home;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product_home = itemView.findViewById(R.id.img_product_home);
            tv_name_product_home = itemView.findViewById(R.id.tv_name_product_home);
            tv_price_product_home = itemView.findViewById(R.id.tv_price_product_home);
            rtb_rating_product_home = itemView.findViewById(R.id.rtb_rating_product_home);
            tv_rating_product_home = itemView.findViewById(R.id.tv_rating_product_home);
            btn_buy_product_home = itemView.findViewById(R.id.btn_buy_product_home);
        }
    }
}
