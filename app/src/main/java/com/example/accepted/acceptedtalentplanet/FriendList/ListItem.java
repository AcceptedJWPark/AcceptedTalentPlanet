package com.example.accepted.acceptedtalentplanet.FriendList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class ListItem {

    private int conditionType;
    private int talentType;
    private int picture;
    private String name;
    private String talent1;
    private String talent2;
    private String talent3;
    private String talentID;
    private String userID;

    public int getConditionType() {
        return conditionType;
    }
    public int getTalentType() {
        return talentType;
    }
    public int getpicture() {
        return picture;
    }
    public String getTalent1() {
        return talent1;
    }
    public String getTalent2() {
        return talent2;
    }
    public String getTalent3() {
        return talent3;
    }
    public String getname() {
        return name;
    }
    public String getTalentID(){ return talentID; }
    public String getUserID(){ return userID; }
    public void setTalentType(int TalentType_CODE) {
        this.talentType = TalentType_CODE;
    }
    public void setConditionType(int TalentConditionType_CODE) {
        this.conditionType = TalentConditionType_CODE;
    }
    public void setTalentID(String TalentID){
        this.talentID = TalentID;
    }
    public void setpicture(int picture) {
        this.picture = picture;
    }
    public void setTalent1(String Keyword1) {
        this.talent1 = Keyword1;
    }
    public void setTalent2(String Keyword2) {
        this.talent2 = Keyword2;
    }
    public void setTalent3(String Keyword3) {
        this.talent3 = Keyword3;
    }
    public void setname(String name) {
        this.name = name;
    }


    public ListItem(int picture, String name, String userID, String talent1, String talent2, String talent3, int conditionType, int talentType, String TalentID)
        {
            this.picture = picture;
            this.name = name;
            this.userID = userID;
            this.talent1 = talent1;
            this.talent2 = talent2;
            this.talent3 = talent3;
            this.conditionType = conditionType;
            this.talentType = talentType;
            this.talentID = TalentID;
        }

}
