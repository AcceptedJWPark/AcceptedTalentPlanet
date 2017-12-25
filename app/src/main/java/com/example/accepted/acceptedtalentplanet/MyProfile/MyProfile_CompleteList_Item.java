package com.example.accepted.acceptedtalentplanet.MyProfile;

/**
 * Created by Accepted on 2017-09-29.
 */

public class MyProfile_CompleteList_Item {

    private int TalentType_CODE;
    private String TalentCondition;
    private String RegistDate;
    private String Keyword1;
    private String Keyword2;
    private String Keyword3;
    private String name;
    private String point;

    public int TalentType_CODE() {
        return TalentType_CODE;
    }
    public String getTalentCondition() {
        return TalentCondition;
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
    public String getpoint() {
        return point;
    }



    public void setTalentType_CODE(int TalentType_CODE) {
        this.TalentType_CODE = TalentType_CODE;
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
    public void setpoint(String point) {
        this.point = point;
    }
    public void setTalentCondition(String TalentCondition) {
        this.TalentCondition= TalentCondition;
    }


    public MyProfile_CompleteList_Item( int TalentType_CODE, String name, String RegistDate, String Keyword1, String Keyword2, String Keyword3, String point)
        {
            this.TalentType_CODE = TalentType_CODE;
            this.name = name;
            this.RegistDate = RegistDate;
            this.Keyword1 = Keyword1;
            this.Keyword2 = Keyword2;
            this.Keyword3 = Keyword3;
            this.point = point;
        }

}
