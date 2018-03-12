package com.example.accepted.acceptedtalentplanet.Alarm;


/**
 * Created by Accepted on 2017-10-24.
 */

public class ListItem {


    private int picture;
    private String name;
    private String text;
    private String date1;
    private String date2;
    private int deleteIcon;

    private int activityChangeType;
    private int alarmType;
    private boolean isDeleteClicked;


    public int getpicture() {
        return picture;
    }
    public String getName() {
        return name;
    }
    public String getregistDate1() {
        return date1;
    }
    public String getregistDate2() {
        return date2;
    }
    public String gettxt() {
        return text;
    }
    public int getalarmType_CODE() {
        return alarmType;
    }
    public int getactivityChange_CODE() {
        return activityChangeType;
    }
    public int getDeleteIcon() {
        return deleteIcon;
    }
    public boolean getdeleteClicked() {
        return isDeleteClicked;
    }

    public void setpicture(int picture) {
        this.picture = picture;
    }
    public void settxt(String txt) {
        this.text = txt;
    }
    public void setregistDate1(String registDate1) {
        this.date1 = registDate1;
    }
    public void setregistDate2(String registDate2) {
        this.date2 = registDate2;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setactivityChange_CODE(int alarmType_CODE) {
        this.activityChangeType = alarmType_CODE;

    }
    public void setDeleteIcon(int deleteIcon) {
        this.deleteIcon = deleteIcon;
    }
    public void setalarmType_CODE(int alarmType_CODE) {
        this.alarmType = alarmType_CODE;
    }
    public void setdeleteClicked(boolean deleteClicked) {
        this.isDeleteClicked = deleteClicked;
    }

    public ListItem(int picture, String name, String date1, int activityChangeType, int alarmType, int deleteIcon, boolean deleteClicked)
    {
        this.picture = picture;
        this.name = name;
        this.date1 = date1;
        this.activityChangeType = activityChangeType;
        this.alarmType = alarmType;
        this.deleteIcon = deleteIcon;
        this.isDeleteClicked = deleteClicked;
    }

    public ListItem(String date1, int activityChangeType, int deleteIcon, boolean deleteClicked)
    {
        this.date1 = date1;
        this.activityChangeType = activityChangeType;
        this.deleteIcon = deleteIcon;
        this.isDeleteClicked = deleteClicked;
    }

    public ListItem(int picture, String name, String date1, int activityChangeType, int deleteIcon, boolean deleteClicked)
    {
        this.picture = picture;
        this.name = name;
        this.date1 = date1;
        this.activityChangeType = activityChangeType;
        this.deleteIcon = deleteIcon;
        this.isDeleteClicked = deleteClicked;
    }



}

