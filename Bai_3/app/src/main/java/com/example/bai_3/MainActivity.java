package com.example.bai_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_select = findViewById(R.id.btn_select);
//        spinner = findViewById(R.id.spinner);
//        String[] a = {"VT","YL","VT","YL"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
//        adapter.notifyDataSetChanged();
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        btn_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onDiaglogShow();
//            }
//        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogCustome diaLogCustome = new DiaLogCustome(MainActivity.this);
                diaLogCustome.show();
            }
        });
    }

//    public void onDiaglogShow(){
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle("Xac nhan")
//                .setMessage("Yes or no")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "Dong Y", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "Khong dong y", Toast.LENGTH_SHORT).show();
//                    }
//                }).create();
//        dialog.show();
//    }
}