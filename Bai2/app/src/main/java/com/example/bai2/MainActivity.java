package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_userName,edt_password;
    Button btn_login;
    TextView tvdisplay;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btn_login.setOnClickListener(this);

    }

    public void AnhXa()
    {
        edt_userName = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        tvdisplay = findViewById(R.id.tvdisplay);
        radioGroup = findViewById(R.id.radioGR);
    }

    @Override
    public void onClick(View v) {
        String name = edt_userName.getText().toString();
        String password = edt_password.getText().toString();
        Toast.makeText(this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
        tvdisplay.setText("Name: "+ name + "\n" + "Password: " + password);
    }
}