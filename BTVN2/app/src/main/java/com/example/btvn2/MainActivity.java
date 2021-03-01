package com.example.btvn2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_hienthi,tv_ketqua;
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,
            btn_delete,btn_ac,btn_cong,btn_tru,btn_nhan,btn_chia,btn_cham,btn_bang,btn_doidau;
    String soDisplay1 = "",soXyLy = "0",soDisplay2 = "0";
    double number1 = 0,number2 = 0;
    int kt = 0,ktbang = 0,ktso = 0,ktdau = 0, dem =0,dem1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        int[] ids = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,
                R.id.btn_6,R.id.btn_7,R.id.btn_8,R.id.btn_9,R.id.btn_ac,R.id.btn_delete,
                R.id.btn_cong,R.id.btn_tru,R.id.btn_nhan,R.id.btn_chia,R.id.btn_cham,
                R.id.btn_bang,R.id.btn_doidau};
        for (int id : ids)
        {
            View v = (View) findViewById(id);
            v.setOnClickListener(this);
        }
    }

    public void AnhXa()
    {
        tv_hienthi = findViewById(R.id.tv_hienthi);
        tv_ketqua = findViewById(R.id.tv_ketqua);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_ac = findViewById(R.id.btn_ac);
        btn_delete = findViewById(R.id.btn_delete);
        btn_cong = findViewById(R.id.btn_cong);
        btn_tru = findViewById(R.id.btn_tru);
        btn_nhan = findViewById(R.id.btn_nhan);
        btn_chia = findViewById(R.id.btn_chia);
        btn_cham = findViewById(R.id.btn_cham);
        btn_bang = findViewById(R.id.btn_bang);
        btn_doidau = findViewById(R.id.btn_doidau);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_ac){
            soDisplay1 = "0";
            soDisplay2 = "0";
            soXyLy = "0";
            ktbang = 0; kt = 0; ktso = 0; ktdau = 0;
            number1 = 0;
        }
        else if(kt != 6 )
            switch (v.getId())
            {
                case R.id.btn_cong:
                    KetQua1();
                    if(kt != 6){
                        soDisplay1 += " + ";
                        kt = 1;
                    }
                    break;
                case R.id.btn_tru:
                    KetQua1();
                    if(kt != 6){
                        soDisplay1 += " - ";
                        kt = 2;
                    }
                    break;
                case R.id.btn_nhan:
                    KetQua1();
                    if(kt != 6){
                        soDisplay1 += " x ";
                        kt = 3;
                    }
                    break;
                case R.id.btn_chia:
                    KetQua1();
                    if(kt != 6){
                        soDisplay1 += " / ";
                        kt = 4;
                    }
                    break;
                case  R.id.btn_bang:
                    if(ktbang != 1)
                    {
                        if(soXyLy.charAt(soXyLy.length()-1) == '.') soXyLy = Delete(soXyLy,soXyLy.length() - 1);
                        if(kt == 1) number1 += Double.parseDouble(soXyLy);
                        else if(kt == 2) number1 -= Double.parseDouble(soXyLy);
                        else if(kt == 3) number1 *= Double.parseDouble(soXyLy);
                        else
                        {
                            if (soXyLy.compareTo("0") == 0) kt = 6;
                            else
                                number1 /= Double.parseDouble(soXyLy);
                        }
                        if(kt == 6)
                        {
                            soDisplay1 = "Ấn AC!";
                            soDisplay2 = "Lỗi";
                        }
                        else
                        {
//                            for(int i =0;i< soXyLy.length();i++)
//                            {
//                                if(soXyLy.charAt(i) == '.')
//                                {
//                                    dem = i;
//                                    break;
//                                }
//                                else dem =soXyLy.length();
//                            }
//                            int n ;
//                            if(number1 == (int) number1) n = String.valueOf((int)number1).length();
//                            else n = String.valueOf(number1).length();
//                                for(int i =0;i<n;i++)
//                                {
//                                    if(String.valueOf(number1).charAt(i) == '.')
//                                    {
//                                        dem1++;break;
//                                    }
//                                    else
//                                        dem1 = n;
//                                }
                            if(number1 == (int) number1)  soDisplay2 = String.valueOf((int)number1);
                            else soDisplay2 = String.valueOf(number1);
                            soDisplay1 += String.valueOf(soXyLy);
//                            dem =0;dem1= 0;
                        }
                        ktbang = 1;
                    }
                    break;
                case R.id.btn_delete:
                    if(ktbang != 1)
                    {
                        if(soXyLy.length() == 1)
                            soXyLy ="0";
                        else
                            soXyLy = Delete(soXyLy,soXyLy.length()-1);
                        soDisplay2 = soXyLy;
                    }
                    break;
                case R.id.btn_cham:
                    if(ktbang != 1) {
                        soXyLy += ".";
                        soDisplay2 = soXyLy;
                    }
                    break;
                case R.id.btn_doidau:
                    if(ktbang != 1) {
                        if (soXyLy.compareTo("0") == 0)
                            soXyLy = "";
                        soXyLy += "-";
                        soDisplay2 = soXyLy;
                    }
                    break;
                default:
                    if(ktbang != 1)
                    {
                        if(soXyLy.compareTo("0") == 0)
                            soXyLy = "";
                        soXyLy += ((Button)v).getText().toString();
                        soDisplay2 = soXyLy;
                        if(ktso < 2) ktso++;
                    }
            }

        tv_ketqua.setText(soDisplay2);
        tv_hienthi.setText(soDisplay1);
    }

    public String Delete(String a,int x)
    {
        StringBuilder stringBuilder = new StringBuilder(a);
        stringBuilder.deleteCharAt(x);
        return stringBuilder.toString();
    }

    public String chen(String a,int vtricham,int soluongdu)
    {
        String b ="";
        if(vtricham>=3 || vtricham == a.length())
        {
            if(soluongdu !=0 )
            {
                for(int i =0;i<soluongdu;i++)
                    b+= String.valueOf(a.charAt(i));
                if(vtricham>= 3)  b+=",";
            }
            if(vtricham-soluongdu>=3)
            {
                for(int i =soluongdu;i<vtricham;i++) {
                    b += String.valueOf(a.charAt(i));
                    if ((i-soluongdu+1)%3 == 0 && i<vtricham-2) b += ",";
                }
            }
        }
        else b = a;
        return b;
    }
    public void KetQua1()
    {
        if(ktdau<2) ktdau++;
        if(ktbang != 1)
        {
            if(soXyLy.charAt(soXyLy.length()-1) == '.') soXyLy = Delete(soXyLy,soXyLy.length() - 1);
            if(ktso == 0) number1 = 0;
            else if(ktdau == 1)
                number1 = Double.parseDouble(soXyLy);
            else
            {
                if(kt == 1) number1 += Double.parseDouble(soXyLy);
                else if(kt == 2) number1 -= Double.parseDouble(soXyLy);
                else if(kt == 3) number1 *= Double.parseDouble(soXyLy);
                else
                {
                    if (soXyLy.compareTo("0") == 0) kt = 6;
                    else
                        number1 /= Double.parseDouble(soXyLy);
                }
            }
            if(soDisplay1.compareTo("0") == 0)   soDisplay1 = "";
//            for(int i =0;i< soXyLy.length();i++)
//            {
//                if(soXyLy.charAt(i) == '.')
//                {
//                    dem = i;
//                    break;
//                }
//                else dem =soXyLy.length();
//            }
            if(Double.parseDouble(soXyLy) == (int) Double.parseDouble(soXyLy))    soDisplay1 += String.valueOf((int)Double.parseDouble(soXyLy));
            else soDisplay1 += soXyLy;
//            dem =0;
        }
        if(number1 == (int)number1) soDisplay2 = String.valueOf((int)number1);
        else soDisplay2 = String.valueOf(number1);
        soXyLy = "0";
        ktbang = 0;
        if(kt == 6) soDisplay1 = "Ấn AC";
    }

}