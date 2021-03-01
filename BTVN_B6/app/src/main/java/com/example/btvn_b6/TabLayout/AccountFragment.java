package com.example.btvn_b6.TabLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_b6.R;
import com.example.btvn_b6.RecycleView.Account.AccountAdapter;
import com.example.btvn_b6.RecycleView.Account.OnClickCountProductCart;
import com.example.btvn_b6.RecycleView.Home.Product;
import com.example.btvn_b6.SQLite.DAO.ProductCart;
import com.example.btvn_b6.databinding.LayoutAccountFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment{
    List<Product> products = new ArrayList<>();
    LayoutAccountFragmentBinding binding;
    RecyclerView rcv_account;
    ProductCart cart;
    Toast toast;
    int sumMoney = 0;
    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.layout_account_fragment,container,false);
        cart = new ProductCart(getContext());
        cart.Open();
        products = cart.GetProductsCart();
        ListProductCart();
        binding.btnBuyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBuy();
            }
        });
        return binding.getRoot();
    }

    public void ListProductCart(){
        sumMoney = 0;
        final AccountAdapter adapter = new AccountAdapter(products,getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.rcvAccount.setLayoutManager(layoutManager);
        binding.rcvAccount.setAdapter(adapter);
        for (int i = 0;i<products.size();i++){
            sumMoney += Integer.parseInt(products.get(i).getPriceProduct().substring(0,products.get(i).getPriceProduct().length()-1));
        }
        binding.tvSumMoneyCart.setText(sumMoney + "đ");
        adapter.setOnClickCountProductCart(new OnClickCountProductCart() {
            @Override
            public void onClickMinusProductCart(Product product, int count) {
                sumMoney -= Integer.parseInt(product.getPriceProduct().substring(0,product.getPriceProduct().length()-1));
                binding.tvSumMoneyCart.setText(sumMoney + "đ");
                if(product.getCountProduct()==0){
                    products.remove(count);
                }
                ListProductCart();
            }

            @Override
            public void onClickPlusProductCart(Product product) {
                sumMoney += Integer.parseInt(product.getPriceProduct().substring(0,product.getPriceProduct().length()-1));
                binding.tvSumMoneyCart.setText(sumMoney + "đ");
            }
        });
    }

    public void DialogBuy(){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_buy);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        window.setAttributes(windowAttributes);
        dialog.setCancelable(true);

        if(products.size() != 0)
            dialog.show();
        else {
            toast = Toast.makeText(getContext(), "Chưa có sản phẩm nào trong giỏ hàng!", Toast.LENGTH_SHORT);
            toast.show();
        }
        Button btn_yes = dialog.findViewById(R.id.yes);
        Button btn_no = dialog.findViewById(R.id.no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast = Toast.makeText(getContext(), "Đã thanh toán xong!", Toast.LENGTH_SHORT);
                toast.show();
                cart.DelAllProductCart();
                products.clear();
                ListProductCart();
                dialog.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast = Toast.makeText(getContext(), "Bạn nên thanh toán đi :v", Toast.LENGTH_SHORT);
                toast.show();
                dialog.dismiss();
            }
        });
    }
}
