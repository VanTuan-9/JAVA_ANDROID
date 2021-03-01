package com.example.btvn3.BaseAdapter_Alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btvn3.R;

import java.util.List;


public class BaseAdapter_Alarm extends BaseAdapter {
    Context context;
    int layout;
    List<Alarm> listAlarms;

    public BaseAdapter_Alarm(Context context, int layout, List<Alarm> listAlarms) {
        this.context = context;
        this.layout = layout;
        this.listAlarms = listAlarms;
    }

    @Override
    public int getCount() {
        return listAlarms.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,parent,false);

        ImageView image_alarm = convertView.findViewById(R.id.img_alarm);
        TextView tv_time1 = convertView.findViewById(R.id.tv_time1);
        TextView title = convertView.findViewById(R.id.title);

        image_alarm.setImageResource(R.drawable.alarm_clock_64);
        tv_time1.setText(listAlarms.get(position).getTime1());
        title.setText(listAlarms.get(position).getTitle());

        return convertView;
    }
}
