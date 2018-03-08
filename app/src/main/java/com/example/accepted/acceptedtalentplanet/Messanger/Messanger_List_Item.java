package com.example.accepted.acceptedtalentplanet.Messanger;

/**
 * Created by Accepted on 2018-03-05.
 */

public class Messanger_List_Item {

    private int messsanger_Pic;
    private String messanger_Name;
    private String messanger_Content;
    private String messanger_Date;
    private int messanger_Count;
    private boolean messanger_DeleteBtn;

    public Messanger_List_Item(int messsanger_Pic, String messanger_Name, String messanger_Content, String messanger_Date, int messanger_Count, boolean messanger_DeleteBtn) {
        this.messsanger_Pic = messsanger_Pic;
        this.messanger_Name = messanger_Name;
        this.messanger_Content = messanger_Content;
        this.messanger_Date = messanger_Date;
        this.messanger_Count = messanger_Count;
        this.messanger_DeleteBtn = messanger_DeleteBtn;
    }

    public int getMesssanger_Pic() {
        return messsanger_Pic;
    }

    public void setMesssanger_Pic(int messsanger_Pic) {
        this.messsanger_Pic = messsanger_Pic;
    }

    public String getMessanger_Name() {
        return messanger_Name;
    }

    public void setMessanger_Name(String messanger_Name) {
        this.messanger_Name = messanger_Name;
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

    public int getMessanger_Count() {
        return messanger_Count;
    }

    public void setMessanger_Count(int messanger_Count) {
        this.messanger_Count = messanger_Count;
    }

    public boolean isMessanger_DeleteBtn() {
        return messanger_DeleteBtn;
    }

    public void setMessanger_DeleteBtn(boolean messanger_DeleteBtn) {
        this.messanger_DeleteBtn = messanger_DeleteBtn;
    }

}
