package com.example.accepted.acceptedtalentplanet.SharingList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class ListItem {

    private int talentType;
    private int conditionType;
    private String date;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String name;
    private String talentID;
    private String myTalentID;

    public int TalentType_CODE() {
        return talentType;
    }
    public int getConditionType() {
        return conditionType;
    }
    public String getDate() {
        return date;
    }
    public String getKeyword1() {
        return keyword1;
    }
    public String getKeyword2() {
        return keyword2;
    }
    public String getKeyword3() {
        return keyword3;
    }
    public String getname() {
        return name;
    }
    public String getTalentID(){
        return talentID;
    }
    public int getTalentType(){return this.talentType;}
    public String getMyTalentID(){return this.myTalentID;}


    public void setTalentType(int TalentType_CODE) {
        this.talentType = TalentType_CODE;
    }
    public void setConditionType(int TalentConditionType_CODE) {
        this.conditionType = TalentConditionType_CODE;
    }
    public void setKeyword1(String Keyword1) {
        this.keyword1 = Keyword1;
    }
    public void setKeyword2(String Keyword2) {
        this.keyword2 = Keyword2;
    }
    public void setKeyword3(String Keyword3) {
        this.keyword3 = Keyword3;
    }
    public void setname(String name) {
        this.name = name;
    }
    public void setDate(String RegistDate) {
        this.date = RegistDate;
    }
    public void setMyTalentID(String myTalentID) { this.myTalentID = myTalentID; }


    public ListItem(String name, int conditionType, String date, String Keyword1, String Keyword2, String Keyword3, int talentType, String TalentID, String myTalentID)
        {
            this.name = name;
            this.conditionType = conditionType;
            this.date = date;
            this.keyword1 = Keyword1;
            this.keyword2 = Keyword2;
            this.keyword3 = Keyword3;
            this.talentType = talentType;
            this.talentID = TalentID;
            this.myTalentID = myTalentID;
        }

}
