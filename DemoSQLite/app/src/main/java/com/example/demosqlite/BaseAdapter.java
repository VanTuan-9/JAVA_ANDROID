package com.example.demosqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

public class BaseAdapter extends android.widget.BaseAdapter {

    Context context;
    int layout;
    List<String> dsSinhVien;

    public BaseAdapter(Context context, int layout, List<String> dsSinhVien) {
        this.context = context;
        this.layout = layout;
        this.dsSinhVien = dsSinhVien;
    }

    @Override
    public int getCount() {
        return dsSinhVien.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,parent,false);

        EditText tenSinhVien = (EditText) convertView.findViewById(R.id.edt_Input);

        tenSinhVien.setText(dsSinhVien.get(position));

        return convertView;
    }
}
