package com.example.btvn_b6.TabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.btvn_b6.R;
import com.example.btvn_b6.RecycleView.Home.HomeAdapter;
import com.example.btvn_b6.RecycleView.Home.Product;
import com.example.btvn_b6.Volley.JsonVolley;
import com.example.btvn_b6.databinding.LayoutHomeFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    LayoutHomeFragmentBinding binding;
    List<Product> products = new ArrayList<>();

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.layout_home_fragment,container,false);
        getAPI();
        return binding.getRoot();
    }

    public void ListProduct(final List<Product> products){
        HomeAdapter adapter = new HomeAdapter(products,getContext());
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.rcvHome.setLayoutManager(layoutManager);
        binding.rcvHome.setAdapter(adapter);
    }

    private int getAPI(){
        final int[] kt = {0};
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://data-products.herokuapp.com/", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<JSONObject> jsonObjects = new ArrayList<>();
                            for (int i =0;i<response.length();i++){
                                jsonObjects.add(response.getJSONObject(i));
                                products.add(new Product(Integer.parseInt(jsonObjects.get(i).getString("id")),
                                        jsonObjects.get(i).getString("nameProduct"),
                                        jsonObjects.get(i).getString("priceProduct"),
                                        jsonObjects.get(i).getString("urlImgProduct"),
                                        Float.parseFloat(jsonObjects.get(i).getString("ratingProduct")),1));
                            }
                            ListProduct(products);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        JsonVolley.getInstance(getContext()).getRequestQueue().add(jsonArrayRequest);
        return 1;
    }

}
