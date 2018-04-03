package com.accepted.acceptedtalentplanet.MyProfile.CompleteList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class ListItem {

    private int talentType;
    private String condition;
    private String date;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String name;
    private String point;

    public int TalentType_CODE() {
        return talentType;
    }
    public String getCondition() {
        return condition;
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
    public String getpoint() {
        return point;
    }



    public void setTalentType(int TalentType_CODE) {
        this.talentType = TalentType_CODE;
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
    public void setpoint(String point) {
        this.point = point;
    }
    public void setCondition(String TalentCondition) {
        this.condition = TalentCondition;
    }


    public ListItem(int talentType, String name, String date, String Keyword1, String Keyword2, String Keyword3, String point)
        {
            this.talentType = talentType;
            this.name = name;
            this.date = date;
            this.keyword1 = Keyword1;
            this.keyword2 = Keyword2;
            this.keyword3 = Keyword3;
            this.point = point;
        }

}
