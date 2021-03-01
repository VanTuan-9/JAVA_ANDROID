package com.example.btvn3.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.btvn3.MainActivity;
import com.example.btvn3.R;

public class CustomDialog extends Dialog {

    TimePicker tp_time;
    EditText ed_note;
    Button btn_ok,btn_cannel;
    String time = "",note;
    int a,b;

    public Context context;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);

        tp_time = findViewById(R.id.tp_time);
        ed_note = findViewById(R.id.ed_note);
        btn_cannel = findViewById(R.id.btn_cannel);
        btn_ok = findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickOk();
            }
        });

        btn_cannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickCannel();
            }
        });
    }

    public void ClickOk() {
        time = String.valueOf(tp_time.getHour()) + ":" + String.valueOf(tp_time.getMinute());
        note = ed_note.getText().toString();
        Log.d("Test",time);
        if(note.length() == 0 || time == null) {
            Toast.makeText(context, "Chưa nhập ghi chú", Toast.LENGTH_SHORT).show();
            return;
        }
        dismiss();
        if(note.length() != 0)
        {
            Toast.makeText(context, "Đã thêm.", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClickCannel() {
        dismiss();
    }
}
