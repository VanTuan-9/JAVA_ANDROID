package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demosqlite.SQLite.DataBaseNhanVien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtSinhVien;
    Button them,xoa,sua;
    List<String> dsSinhVien;
    ListView listSinhVien;

    DataBaseNhanVien dataBaseNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseNhanVien = new DataBaseNhanVien(this,"QuanLySV.sqlite",null,1);
        dataBaseNhanVien.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(id INTEGER PRIMARY KEY AUTOINCREMENT,TenSV NVARCHAR(200))");
        dataBaseNhanVien.QueryData("INSERT INTO SinhVien VALUES (null, Phan Thị Yến Linh)");

        Cursor dataName = dataBaseNhanVien.GetData("SELECT * FROM SinhVien");
        while (dataName.moveToNext())
        {
            String name = dataName.getString(1);
            Toast.makeText(this, "Tên bẹp Yến: "+ name, Toast.LENGTH_SHORT).show();
        }
//        edtSinhVien = (EditText) findViewById(R.id.edt_Input);
//        them = (Button) findViewById(R.id.btn_Add);
//        xoa = (Button) findViewById(R.id.btn_Delete);
//        sua = (Button) findViewById(R.id.btn_Repair);
//        listSinhVien = (ListView) findViewById(R.id.list_Student);
//        them.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_Add:
                dsSinhVien = new ArrayList<>();
                dsSinhVien.add(edtSinhVien.getText().toString());
                Toast.makeText(this, ""+dsSinhVien.get(0), Toast.LENGTH_SHORT).show();
                BaseAdapter adapter = new BaseAdapter(this,android.R.layout.simple_list_item_1,dsSinhVien);
                adapter.notifyDataSetChanged();
                listSinhVien.setAdapter(adapter);
                break;
        }
    }
}