package com.example.btvn3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.btvn3.BaseAdapter_Alarm.Alarm;
import com.example.btvn3.BaseAdapter_Alarm.BaseAdapter_Alarm;
import com.example.btvn3.CustomDialog.CustomDialog;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    ListView list_alarm;
    Button btn_add;
    List<Alarm> alarms;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_alarm = findViewById(R.id.list_alarm);
        btn_add = findViewById(R.id.btn_add);
        test = findViewById(R.id.test);
        alarms = new ArrayList<>();
        final Alarm alarm = new Alarm();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(MainActivity.this);
                dialog.show();
                alarms.add(alarm);
                test.setText(alarm.getTitle());
            }
        });
        if(alarms.size() != 0)
        {
            BaseAdapter_Alarm adapter_alarm = new BaseAdapter_Alarm(this,R.layout.layout_listview1,alarms);
            adapter_alarm.notifyDataSetChanged();
            list_alarm.setAdapter(adapter_alarm);
        }
    }

}