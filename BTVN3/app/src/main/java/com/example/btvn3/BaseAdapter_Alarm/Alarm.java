package com.example.btvn3.BaseAdapter_Alarm;

public class Alarm {
    int image_alarm;
    String time1,title;

    public Alarm() {
    }

    public Alarm(int image_alarm, String time1, String title) {
        this.image_alarm = image_alarm;
        this.time1 = time1;
        this.title = title;
    }

    public int getImage_alarm() {
        return image_alarm;
    }

    public void setImage_alarm(int image_alarm) {
        this.image_alarm = image_alarm;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
