package com.example.btvn_4;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btvn_4.RecycleViewAdapterCart.OnClickItemProductCart;
import com.example.btvn_4.RecycleViewAdapterCart.ProductCart;
import com.example.btvn_4.RecycleViewAdapterCart.RecycleViewAdapterCart;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ImageButton ibtn_back_cart;
    List<ProductCart> productCarts = new ArrayList<>();
    RecyclerView rcv_product_cart;
    TextView countPrice;
    Button btn_buy;
    int price = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ibtn_back_cart = findViewById(R.id.ibtn_back_cart);
        rcv_product_cart = findViewById(R.id.rcv_product_cart);
        countPrice = findViewById(R.id.countPrice);
        btn_buy = findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(CartActivity.this);
                dialog.setTitle("Cảnh báo!");
                dialog.setMessage("Bạn thật sự muốn thanh toán???");
                dialog.setPositiveButton("Thanh toán", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CartActivity.this, "Chúc mừng bạn đã mua được dép xịn!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CartActivity.this,MenuActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CartActivity.this, "Bạn nên thanh toán để có dép!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create().show();
            }
        });

        ibtn_back_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                return;
            }
        });

        int imgProduct[];
        String nameProduct[];
        String priceProduct[];
        final Intent intent = getIntent();
        imgProduct = intent.getIntArrayExtra("ImgProduct");
        nameProduct = intent.getStringArrayExtra("NameProduct");
        priceProduct = intent.getStringArrayExtra("PriceProduct");
        for (int i = 0;i< imgProduct.length;i++){
            ProductCart productCart = new ProductCart();
            productCart.setImgProductCart(imgProduct[i]);
            productCart.setNameProductCart(nameProduct[i]);
            productCart.setPriceProductCart(priceProduct[i]);
            productCart.setCountProductCart(1);
            productCarts.add(productCart);
            price += Integer.parseInt(priceProduct[i]);
            countPrice.setText(String.valueOf(price));
        }
        ClickCart();

    }

    private void ClickCart() {
        if(productCarts.size()>0){
            RecycleViewAdapterCart cart = new RecycleViewAdapterCart(productCarts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
            rcv_product_cart.setLayoutManager(layoutManager);
            rcv_product_cart.setAdapter(cart);
            cart.setOnClickItemProductCart(new OnClickItemProductCart() {
                @Override
                public void onClickMinusProductCart(ProductCart productCart,int x,int y) {
                    if(productCart.getCountProductCart() > 0){
                        price -= Integer.parseInt(productCart.getPriceProductCart());
                        countPrice.setText(String.valueOf(price));
                        if(productCart.getCountProductCart()-1 == 0){
                            productCarts.remove(x);
                            ClickCart();
                            if(y == 1){
                                Intent intent = new Intent(CartActivity.this,MenuActivity.class);
                                Toast.makeText(CartActivity.this, "Giỏ hàng rỗng!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        }
                    }
                }

                @Override
                public void onClickPlusProductCart(ProductCart productCart) {
                    price += Integer.parseInt(productCart.getPriceProductCart());
                    countPrice.setText(String.valueOf(price));
                }
            });
        }
    }
}
