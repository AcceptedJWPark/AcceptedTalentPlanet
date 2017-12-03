package com.example.accepted.acceptedtalentplanet.FriendList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class FriendList_Item {

    private int TalentConditionType_CODE;
    private int TalentType_CODE;
    private int picture;
    private String name;
    private String Keyword1;
    private String Keyword2;
    private String Keyword3;

    public int getTalentConditionType_CODE() {
        return TalentConditionType_CODE;
    }
    public int getTalentType_CODE() {
        return TalentType_CODE;
    }
    public int getpicture() {
        return picture;
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
    public void setpicture(int picture) {
        this.picture = picture;
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


    public FriendList_Item(int picture,String name, String Keyword1, String Keyword2, String Keyword3, int TalentConditionType_CODE, int TalentType_CODE)
        {
            this.picture = picture;
            this.name = name;
            this.Keyword1 = Keyword1;
            this.Keyword2 = Keyword2;
            this.Keyword3 = Keyword3;
            this.TalentConditionType_CODE = TalentConditionType_CODE;
            this.TalentType_CODE = TalentType_CODE;
        }

}
