package com.example.btvn_b6;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        progressBar = findViewById(R.id.progress);
        Process();
    }
    public void Process(){
        progressBar.setProgress(0);
        progressBar.setMax(501500);
        Thread thread = new Thread(){
            @Override
            public void run() {
                int time = 0, max = 501500,i=0;
                while (time<max){
                    try {
                        i++;
                        sleep(1);
                        time +=1+i;
                        progressBar.setProgress(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        thread.start();
    }
}