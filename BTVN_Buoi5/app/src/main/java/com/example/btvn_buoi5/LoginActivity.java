package com.example.btvn_buoi5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.btvn_buoi5.Volley.Account;
import com.example.btvn_buoi5.Volley.JsonVolley;
import com.example.btvn_buoi5.databinding.ActivityLoginBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayRequest();
            }
        });

    }
    private void JsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://data-clients.herokuapp.com/users", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String a = response.toString();
                        ChangeJson(a);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        JsonVolley.getInstance(this).getRequestQueue().add(jsonArrayRequest);
    }

    public void ChangeJson(String jsonText){
        try {
            JSONArray jsonArray = new JSONArray(jsonText);
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            ArrayList<Account> accounts = new ArrayList<>();
            for (int i = 0 ;i< jsonArray.length();i++){
                jsonObjects.add(jsonArray.getJSONObject(i));
                accounts.add(new Account(jsonObjects.get(i).getString("userName"),jsonObjects.get(i).getString("passWord")));
            }
            for (int i = 0;i<accounts.size();i++){
                Log.d("TAG",binding.edtUsername.getText().toString());
                if(binding.edtUsername.getText().toString().compareTo(accounts.get(i).getUsername()) == 0
                        && binding.edtPassword.getText().toString().compareTo(accounts.get(i).getPassword()) == 0){
                    Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                    intent.putExtra("account",accounts.get(i));
                    startActivity(intent);
                    finish();
                    return;
                }
            }
            Toast.makeText(this,"Tài khoản hoặc mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}