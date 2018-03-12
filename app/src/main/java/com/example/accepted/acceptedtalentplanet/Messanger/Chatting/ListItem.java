package com.example.accepted.acceptedtalentplanet.Messanger.Chatting;

/**
 * Created by Accepted on 2018-03-05.
 */

public class ListItem {

    private int messsanger_Pic;
    private String messanger_Content;
    private String messanger_Date;
    private int message_Type;
    private boolean picture_Type;
    private boolean time_Changed;
    private boolean date_Changed;


    public ListItem(int messsanger_Pic, String messanger_Content, String messanger_Date, int message_Type, boolean picture_Type, boolean time_Changed, boolean date_Changed) {
        this.messsanger_Pic = messsanger_Pic;
        this.messanger_Content = messanger_Content;
        this.messanger_Date = messanger_Date;
        this.message_Type = message_Type;
        this.picture_Type = picture_Type;
        this.time_Changed = time_Changed;
        this.date_Changed = date_Changed;
    }

    public int getMesssanger_Pic() {
        return messsanger_Pic;
    }

    public void setMesssanger_Pic(int messsanger_Pic) {
        this.messsanger_Pic = messsanger_Pic;
    }

    public String getMessanger_Content() {
        return messanger_Content;
    }

    public void setMessanger_Content(String messanger_Content) {
        this.messanger_Content = messanger_Content;
    }

    public String getMessanger_Date() {
        return messanger_Date;
    }

    public void setMessanger_Date(String messanger_Date) {
        this.messanger_Date = messanger_Date;
    }

    public int getMessage_Type() {
        return message_Type;
    }

    public void setMessage_Type(int message_Type) {
        this.message_Type = message_Type;
    }

    public boolean isPicture_Type() {
        return picture_Type;
    }

    public void setPicture_Type(boolean picture_Type) {
        this.picture_Type = picture_Type;
    }

    public boolean isTime_Changed() {
        return time_Changed;
    }

    public void setTime_Changed(boolean time_Changed) {
        this.time_Changed = time_Changed;
    }

    public boolean isDate_Changed() {
        return date_Changed;
    }

    public void setDate_Changed(boolean date_Changed) {
        this.date_Changed = date_Changed;
    }
}
