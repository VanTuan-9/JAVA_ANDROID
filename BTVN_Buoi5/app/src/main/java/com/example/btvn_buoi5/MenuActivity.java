package com.example.btvn_buoi5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btvn_buoi5.Volley.Account;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        Toast.makeText(this, "vt: " + account.getUsername(), Toast.LENGTH_SHORT).show();
    }
}