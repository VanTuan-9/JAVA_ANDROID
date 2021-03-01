package com.example.btvn_b6;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.btvn_b6.RecycleView.Home.Product;
import com.example.btvn_b6.SQLite.DAO.ProductCart;
import com.example.btvn_b6.TabLayout.FragmentAdapter;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageButton ibtn_cancel, ibtn_cart;
    private long backTime;
    Toast toast;

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24_blue);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_notifications_active_24_blue);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_shopping_cart_24);


        ibtn_cancel = findViewById(R.id.ibtn_cancel);
        ibtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(backTime + 2000 > System.currentTimeMillis()){
            toast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            toast = Toast.makeText(this, "Nhấn liên tục 2 lần để thoát!", Toast.LENGTH_SHORT);
            toast.show();
        }
        backTime = System.currentTimeMillis();
    }
    public void Display(){
        ProductCart productCart = new ProductCart(this);
        productCart.Open();
        List<Product> products = productCart.GetProductsCart();

        BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawable.setVisible(true);

        BadgeDrawable badgeDrawable1 = tabLayout.getTabAt(1).getOrCreateBadge();
        badgeDrawable1.setVisible(true);
        badgeDrawable1.setNumber(1);
        badgeDrawable1.setMaxCharacterCount(2);

        BadgeDrawable badgeDrawable2 = tabLayout.getTabAt(2).getOrCreateBadge();
        badgeDrawable2.setVisible(true);
        badgeDrawable2.setNumber(products.size());
    }
}