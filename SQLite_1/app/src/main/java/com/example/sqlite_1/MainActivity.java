package com.example.sqlite_1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DataBase dataBase;
    ListView lv_Student;
    ArrayList<Student> nameStudent;
    Button btn_Add,btn_Delete,btn_Repair;
    EditText edt_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this,"QLSinhVien.sqlite",null,1);

        dataBase.QueryData("DROP TABLE IF EXISTS SinhVien");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS SinhVien (id INTEGER PRIMARY KEY AUTOINCREMENT, TenSV NVARCHAR(200))");
        Cursor getData = dataBase.GetData("SELECT * FROM SinhVien");

        lv_Student = findViewById(R.id.lv_Student);
        btn_Add = findViewById(R.id.btn_Add);
        btn_Delete = findViewById(R.id.btn_Delete);
        btn_Repair = findViewById(R.id.btn_Repair);
        edt_Name = findViewById(R.id.edt_name);
        nameStudent = new ArrayList<>();
        while (getData.moveToNext()) {
            Student student = new Student();
            student.setIdStudent(getData.getString(0));
            student.setNameStudent(getData.getString(1));
            nameStudent.add(student);
        }
        if(nameStudent.size() >0 ) {
            BaseAdapter baseAdapter = new BaseAdapter(this, android.R.layout.simple_list_item_1, nameStudent);
            baseAdapter.notifyDataSetChanged();
            lv_Student.setAdapter(baseAdapter);
        }

        View v[] = {btn_Add,btn_Repair,btn_Delete};
        for (View i : v)
            i.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Add:
                String name = edt_Name.getText().toString();
                if(name.length() > 0)
                    dataBase.QueryData("INSERT INTO SinhVien VALUES (null,'"+ name +"')");
            case R.id.btn_Delete:
                for(int id: lv_Student.getAdapter().get)
        }
        Cursor getData = dataBase.GetData("SELECT * FROM SinhVien");

        nameStudent = new ArrayList<>();
        while (getData.moveToNext()) {
            Student student = new Student();
            student.setIdStudent(getData.getString(0));
            student.setNameStudent(getData.getString(1));
            nameStudent.add(student);
        }
        if(nameStudent.size() >0 ) {
            BaseAdapter baseAdapter = new BaseAdapter(this,R.layout.layout_listname, nameStudent);
            baseAdapter.notifyDataSetChanged();
            lv_Student.setAdapter(baseAdapter);
        }
    }
}