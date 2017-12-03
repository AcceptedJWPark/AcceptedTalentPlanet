package com.example.accepted.acceptedtalentplanet.SharingList;

import android.widget.Spinner;

/**
 * Created by Accepted on 2017-09-29.
 */

public class SharingList_Item {

    private int TalentType_CODE;
    private int TalentConditionType_CODE;
    private String RegistDate;
    private String Keyword1;
    private String Keyword2;
    private String Keyword3;
    private String name;

    public int TalentType_CODE() {
        return TalentType_CODE;
    }
    public int getTalentConditionType_CODE() {
        return TalentConditionType_CODE;
    }
    public String getRegistDate() {
        return RegistDate;
    }
    public String getKeyword1() {
        return Keyword1;
    }
    public String getKeyword2() {
        return Keyword2;
    }
    public String getKeyword3() {
        return Keyword3;
    }
    public String getname() {
        return name;
    }

    public void setTalentType_CODE(int TalentType_CODE) {
        this.TalentType_CODE = TalentType_CODE;
    }
    public void setTalentConditionType_CODE(int TalentConditionType_CODE) {
        this.TalentConditionType_CODE = TalentConditionType_CODE;
    }
    public void setKeyword1(String Keyword1) {
        this.Keyword1 = Keyword1;
    }
    public void setKeyword2(String Keyword2) {
        this.Keyword2 = Keyword2;
    }
    public void setKeyword3(String Keyword3) {
        this.Keyword3 = Keyword3;
    }
    public void setname(String name) {
        this.name = name;
    }
    public void setRegistDate(String RegistDate) {
        this.RegistDate = RegistDate;
    }


    public SharingList_Item(String name,int TalentConditionType_CODE, String RegistDate, String Keyword1,String Keyword2,String Keyword3,int TalentType_CODE)
        {
            this.name = name;
            this.TalentConditionType_CODE = TalentConditionType_CODE;
            this.RegistDate = RegistDate;
            this.Keyword1 = Keyword1;
            this.Keyword2 = Keyword2;
            this.Keyword3 = Keyword3;
            this.TalentType_CODE = TalentType_CODE;
        }

}
