package com.example.accepted.acceptedtalentplanet.Alarm;


/**
 * Created by Accepted on 2017-10-24.
 */

public class ListItem {


    private int picture;
    private String name;
    private String text;
    private String date;
    private boolean isDeleteClicked;

    private int countMessage;

    private int deleteIcon;
    private int activityChangeType;
    private int conditionType;

    private int talentID;


    public int getpicture() {
        return picture;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String gettxt() {
        return text;
    }
    public int getalarmType_CODE() {
        return conditionType;
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
        this.date = registDate1;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setactivityChange_CODE(int alarmType_CODE) {
        this.activityChangeType = alarmType_CODE;

    }

    public int getCountMessage() {
        return countMessage;
    }
    public int getTalentID(){return talentID;}

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }
    public void setTalentID(int talentID){this.talentID = talentID;}

    public void setDeleteIcon(int deleteIcon) {
        this.deleteIcon = deleteIcon;
    }
    public void setalarmType_CODE(int alarmType_CODE) {
        this.conditionType = alarmType_CODE;
    }
    public void setdeleteClicked(boolean deleteClicked) {
        this.isDeleteClicked = deleteClicked;
    }

    public ListItem(int picture, String name, int talentID, String text, String date, int activityChangeType, int conditionType, int deleteIcon, boolean deleteClicked)
    {
        this.picture = picture;
        this.name = name;
        this.talentID = talentID;
        this.text = text;
        this.date = date;
        this.activityChangeType = activityChangeType;
        this.conditionType = conditionType;
        this.deleteIcon = deleteIcon;
        this.isDeleteClicked = deleteClicked;
    }

    public ListItem(int picture, String name, String text, String date, int activityChangeType, int deleteIcon, boolean deleteClicked)
    {
        this.picture = picture;
        this.name = name;
        this.text = text;
        this.date = date;
        this.activityChangeType = activityChangeType;
        this.deleteIcon = deleteIcon;
        this.isDeleteClicked = deleteClicked;
    }

    public ListItem(int picture, String name, String text, int countMessage, String date,  int activityChangeType, int deleteIcon, boolean deleteClicked)
    {
        this.picture = picture;
        this.name = name;
        this.text = text;
        this.countMessage = countMessage;
        this.date = date;
        this.activityChangeType = activityChangeType;
        this.deleteIcon = deleteIcon;
        this.isDeleteClicked = deleteClicked;
    }



}

