package com.example.sqlite_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapter extends android.widget.BaseAdapter {
    Context context;
    int layout;
    ArrayList<Student> list = new ArrayList<>();

    public BaseAdapter(Context context, int layout, ArrayList<Student> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout,parent,false);

        TextView idStudent = (TextView) convertView.findViewById(R.id.tv_id);
        TextView nameStudent = (TextView) convertView.findViewById(R.id.tv_name);

        idStudent.setText(list.get(position).getIdStudent());
        nameStudent.setText(list.get(position).getNameStudent());

        return convertView;
    }
}
