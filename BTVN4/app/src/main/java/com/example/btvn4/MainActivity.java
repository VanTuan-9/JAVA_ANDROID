package com.example.btvn4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edt_username,edt_password;
    Button btn_login;
    CheckBox cb_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                if(edt_username.getText().toString().length() == 0 || edt_password.getText().toString().length() == 0)
                    Toast.makeText(MainActivity.this, "Chưa nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else if(Pattern.matches(REGEX,edt_password.getText().toString()) == false)
                    Toast.makeText(MainActivity.this,"Mật khảu chưa đúng định dạng.",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    Bundle infor =  new Bundle();
                    infor.putString("username",edt_username.getText().toString());
                    infor.putString("password",edt_password.getText().toString());
                    intent.putExtras(infor);
                    startActivity(intent);
                }

            }
        });
    }

    private void AnhXa() {
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        cb_password = findViewById(R.id.cb_thongtin);
    }
}