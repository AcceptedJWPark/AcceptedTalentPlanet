package com.example.accepted.acceptedtalentplanet.Alarm;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Alarm_ListItem {


    private int picture;
    private String name;
    private String txt;
    private String registDate1;
    private String registDate2;
    private int activityChange_CODE;
    private int alarmType_CODE;


    public int getpicture() {
        return picture;
    }
    public String getName() {
        return name;
    }
    public String getregistDate1() {
        return registDate1;
    }
    public String getregistDate2() {
        return registDate2;
    }
    public String gettxt() {
        return txt;
    }
    public int getalarmType_CODE() {
        return alarmType_CODE;
    }
    public int getactivityChange_CODE() {
        return activityChange_CODE;
    }

    public void setpicture(int picture) {
        this.picture = picture;
    }
    public void settxt(String txt) {
        this.txt = txt;
    }
    public void setregistDate1(String registDate1) {
        this.registDate1 = registDate1;
    }
    public void setregistDate2(String registDate2) {
        this.registDate2 = registDate2;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setactivityChange_CODE(int alarmType_CODE) {
        this.activityChange_CODE = alarmType_CODE;
    }
    public void setalarmType_CODE(int alarmType_CODE) {
        this.alarmType_CODE = alarmType_CODE;
    }

    public Alarm_ListItem(int picture, String name, String registDate1, int activityChange_CODE, int alarmType_CODE)
    {
        this.picture = picture;
        this.name = name;
        this.registDate1 = registDate1;
        this.activityChange_CODE = activityChange_CODE;
        this.alarmType_CODE = alarmType_CODE;
    }

    public Alarm_ListItem(String registDate1, int activityChange_CODE)
    {
        this.registDate1 = registDate1;
        this.activityChange_CODE = activityChange_CODE;
    }

    public Alarm_ListItem(int picture, String name, String registDate1, int activityChange_CODE)
    {
        this.picture = picture;
        this.name = name;
        this.registDate1 = registDate1;
        this.activityChange_CODE = activityChange_CODE;
    }

}

