package com.example.btvn_4.RecycleViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_4.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    List<Product> productList;
    Context context;
    OnClickItemProduct onClickItemProduct;

    public void setOnClickItemProduct(OnClickItemProduct onClickItemProduct) {
        this.onClickItemProduct = onClickItemProduct;
    }

    public RecycleAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_recycleview_adapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.img_product.setImageResource(product.getImgProduct());
        holder.tv_inforProduct.setText(product.getInforProduct());
        holder.tv_priceProduct.setText(product.getPriceProduct());
        holder.img_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemProduct.onClickImageProduct(product);
            }
        });
        holder.tv_inforProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemProduct.onClickNameProduct(product);
            }
        });
        holder.btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemProduct.onClickAddProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product;
        TextView tv_inforProduct,tv_priceProduct;
        Button btn_addProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.img_product);
            tv_inforProduct = itemView.findViewById(R.id.tv_inforProduct);
            tv_priceProduct = itemView.findViewById(R.id.tv_priceProduct);
            btn_addProduct = itemView.findViewById(R.id.btn_addProduct);
        }
    }
}
