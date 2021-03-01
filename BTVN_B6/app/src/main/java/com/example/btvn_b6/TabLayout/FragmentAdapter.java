package com.example.btvn_b6.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btvn_b6.RecycleView.Home.Product;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    final List<Product> products = new ArrayList<>();

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
               return HomeFragment.newInstance();
            case 1:
               return NotificationFragment.newInstance();
            case 2:
                return AccountFragment.newInstance();
            default:
               return HomeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
//
//    @Override
//    public void onClickBuyProductFragment(List<Product> product) {
//        products.addAll(product);
//    }
}
