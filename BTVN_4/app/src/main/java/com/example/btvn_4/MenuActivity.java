package com.example.btvn_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_4.RecycleViewAdapter.OnClickItemProduct;
import com.example.btvn_4.RecycleViewAdapter.Product;
import com.example.btvn_4.RecycleViewAdapter.RecycleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ImageButton ibtn_back,ibtn_cart;
    TextView tv_notification;
    RecyclerView rcv_product;
    int count = 0;
    List<Product> productList = new ArrayList<>();
    List<Product> productCart = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();

        productList.add(new Product(R.drawable.dep_64,"2 ĐÔI FS_Dép Kẹp Xỏ Ngón Nam Phong Cách OHS707 (Đỏ - Vàng)","10000"));
        productList.add(new Product(R.drawable.dep1_64,"POSEE Dép nhựa quai ngang thiết kế phối màu trắng đen độc đáo PS3110","135000"));
        productList.add(new Product(R.drawable.dep2_64,"Dép Nam Nữ Thời Trang Polusion Logo Tam Giác Đế 2 Lớp Chất Liệu Cao Su Nguyên Khối","39000"));
        productList.add(new Product(R.drawable.dep3_64,"DÉP CAO SU NGUYÊN KHỐI MANG TRONG NHÀ QUAI NHÁM HÌNH CON CÁ","36000"));
        productList.add(new Product(R.drawable.dep_64,"2 ĐÔI FS_Dép Kẹp Xỏ Ngón Nam Phong Cách OHS707 (Đỏ - Vàng)","10000"));
        productList.add(new Product(R.drawable.dep1_64,"POSEE Dép nhựa quai ngang thiết kế phối màu trắng đen độc đáo PS3110","135000"));
        productList.add(new Product(R.drawable.dep2_64,"Dép Nam Nữ Thời Trang Polusion Logo Tam Giác Đế 2 Lớp Chất Liệu Cao Su Nguyên Khối","39000"));
        productList.add(new Product(R.drawable.dep3_64,"DÉP CAO SU NGUYÊN KHỐI MANG TRONG NHÀ QUAI NHÁM HÌNH CON CÁ","36000"));
        productList.add(new Product(R.drawable.dep_64,"2 ĐÔI FS_Dép Kẹp Xỏ Ngón Nam Phong Cách OHS707 (Đỏ - Vàng)","10000"));
        productList.add(new Product(R.drawable.dep1_64,"POSEE Dép nhựa quai ngang thiết kế phối màu trắng đen độc đáo PS3110","135000"));
        productList.add(new Product(R.drawable.dep2_64,"Dép Nam Nữ Thời Trang Polusion Logo Tam Giác Đế 2 Lớp Chất Liệu Cao Su Nguyên Khối","39000"));
        productList.add(new Product(R.drawable.dep3_64,"DÉP CAO SU NGUYÊN KHỐI MANG TRONG NHÀ QUAI NHÁM HÌNH CON CÁ","36000"));
        productList.add(new Product(R.drawable.dep_64,"2 ĐÔI FS_Dép Kẹp Xỏ Ngón Nam Phong Cách OHS707 (Đỏ - Vàng)","10000"));
        productList.add(new Product(R.drawable.dep1_64,"POSEE Dép nhựa quai ngang thiết kế phối màu trắng đen độc đáo PS3110","135000"));
        productList.add(new Product(R.drawable.dep2_64,"Dép Nam Nữ Thời Trang Polusion Logo Tam Giác Đế 2 Lớp Chất Liệu Cao Su Nguyên Khối","39000"));
        productList.add(new Product(R.drawable.dep3_64,"DÉP CAO SU NGUYÊN KHỐI MANG TRONG NHÀ QUAI NHÁM HÌNH CON CÁ","36000"));

        RecycleAdapter adapter = new RecycleAdapter(productList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_product.setLayoutManager(layoutManager);
        rcv_product.setAdapter(adapter);


        adapter.setOnClickItemProduct(new OnClickItemProduct() {
            @Override
            public void onClickImageProduct(Product product) {

            }

            @Override
            public void onClickNameProduct(Product product) {

            }

            @Override
            public void onClickAddProduct(Product product) {

                int dem =0;
                if(productCart.size() >0)
                    for (int i = 0;i<productCart.size();i++)
                        if(product == productCart.get(i)){
                            dem++;
                            break;
                        }
                if(productCart.size() == 0 || dem == 0){
                    count++;
                    tv_notification.setText(count + "");
                    Toast.makeText(MenuActivity.this, "Đã thêm!", Toast.LENGTH_SHORT).show();
                    productCart.add(product);
                }
                else
                    Toast.makeText(MenuActivity.this, "Đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });


        ibtn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productCart.size() > 0){
                    int imgProduct[] = new int[productCart.size()];
                    String nameProduct[] = new String[productCart.size()];
                    String priceProduct[] = new String[productCart.size()];

                    for (int i = 0;i<productCart.size();i++){
                        imgProduct[i] = productCart.get(i).getImgProduct();
                        nameProduct[i] = productCart.get(i).getInforProduct();
                        priceProduct[i] = productCart.get(i).getPriceProduct();
                    }
                    Intent intent = new Intent(MenuActivity.this,CartActivity.class);
                    intent.putExtra("ImgProduct",imgProduct);
                    intent.putExtra("NameProduct",nameProduct);
                    intent.putExtra("PriceProduct",priceProduct);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MenuActivity.this, "Không có sản phẩm nào trong giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        ibtn_back = findViewById(R.id.ibtn_back);
        ibtn_cart = findViewById(R.id.ibtn_cart);
        rcv_product = findViewById(R.id.rcv_product);
        tv_notification = findViewById(R.id.tv_notification);
    }

    public void Back(View view) {
        onBackPressed();
        return;
    }
}
