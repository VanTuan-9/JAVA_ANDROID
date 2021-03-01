package com.example.btvn_b6;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btvn_b6.Volley.Account;
import com.example.btvn_b6.Volley.JsonVolley;
import com.example.btvn_b6.databinding.ActivityLoginBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ArrayList<Account> accounts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        JsonArrayRequest();
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i<accounts.size();i++){
                    if(binding.edtUsername.getText().toString().compareTo(accounts.get(i).getUsername()) == 0
                            && binding.edtPassword.getText().toString().compareTo(accounts.get(i).getPassword()) == 0){
                        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                        intent.putExtra("account",accounts.get(i));
                        startActivity(intent);
                        finish();
                        return;
                    }
                }
                Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialogLogin();

            }
        });
    }
    private void JsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://data-clients.herokuapp.com/users", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String a = response.toString();
                        ChangeFromJsonToJava(a);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        JsonVolley.getInstance(this).getRequestQueue().add(jsonArrayRequest);
    }

    private void PostStringtRequest(final String data){
        String url = "https://data-clients.herokuapp.com/users";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return data.getBytes("utf-8");
                }catch (Exception e){
                    e.getMessage();
                    return null;
                }
            }
        };

        JsonVolley.getInstance(this).getRequestQueue().add(stringRequest);
    }

    private void ChangeFromJsonToJava(String jsonText){
        try {
            JSONArray jsonArray = new JSONArray(jsonText);
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (int i = 0 ;i< jsonArray.length();i++){
                jsonObjects.add(jsonArray.getJSONObject(i));
                accounts.add(new Account(jsonObjects.get(i).getString("userName"),jsonObjects.get(i).getString("passWord")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String ChangeFromJavaToJson(Account account){
        JSONObject jsonObject = new JSONObject();
        String a = null;
        try {
            jsonObject.put("userName",account.getUsername());
            jsonObject.put("passWord",account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public void OpenDialogLogin(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_registration);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT); // set chiều rộng với chiều cao chứa dialog
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // set background ngoài dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        // xét tắt khi ấn bên ngoài dialog
        dialog.setCancelable(true);

        final EditText edt_new_username = dialog.findViewById(R.id.edt_new_username);
        final EditText edt_new_password = dialog.findViewById(R.id.edt_new_password);
        final EditText edt_enter_new_password = dialog.findViewById(R.id.edt_enter_new_password);
        Button btn_registration = dialog.findViewById(R.id.btn_registration);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);



        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String a = edt_new_username.getText().toString();
                final String b = edt_new_password.getText().toString();
                final String c = edt_enter_new_password.getText().toString();
                if(a.length() == 0 || b.length() == 0 || c.length() == 0){
                    Toast.makeText(LoginActivity.this,"Chưa điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                }

                else if(b.compareTo(c) != 0){
                    Toast.makeText(LoginActivity.this, "Mật khẩu không trùng nhau!", Toast.LENGTH_SHORT).show();
                }
                else{
                    int dem = 0;
                    for (int i = 0;i<accounts.size();i++){
                        if(a.compareTo(accounts.get(i).getUsername())==0){
                            Toast.makeText(LoginActivity.this,"Tài khoản đã tồn tại!",Toast.LENGTH_SHORT).show();
                            dem = 1;
                            break;
                        }
                    }
                    if(dem == 0){
                        Toast.makeText(LoginActivity.this, "Đăng kí thành công!\nMời bạn đăng nhập.", Toast.LENGTH_SHORT).show();
                        final Account account = new Account(a,b);
                        PostStringtRequest(ChangeFromJavaToJson(account));
                        accounts.add(account);
                        dialog.dismiss();
                    }
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}