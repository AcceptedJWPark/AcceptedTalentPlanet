package com.example.accepted.acceptedtalentplanet.InterestingList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class InterestingList_ListItem {

    private int picture;
    private String name;
    private String talent1;
    private String talent2;
    private String talent3;
    private String talentType;
    private String registDate;
    private String talentID;

    public int getPicture() {
        return picture;
    }

    public String getName() {
        return name;
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

    public String getregistDate() {
        return registDate;
    }

    public String getTalentType() {
        return talentType;
    }

    public String getTalentID() { return talentID; }


    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setTalentType(String talentType) {
        this.talentType = talentType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTalent1(String talent1) {
        this.talent1 = talent1;
    }

    public void setTalent2(String talent2) {
        this.talent2 = talent2;
    }

    public void setTalent3(String talent3) {
        this.talent3 = talent3;
    }

    public void setregistDate(String registDate) {
        this.registDate = registDate;
    }

    public void setTalentID(String talentID) { this.talentID = talentID; }


    public InterestingList_ListItem(int pictire, String name, String talent1, String talent2, String talent3, String talentType, String registDate, String talentID )
    {
        this.picture = pictire;
        this.name = name;
        this.talent1 = talent1;
        this.talent2 = talent2;
        this.talent3 = talent3;
        this.talentType = talentType;
        this.registDate = registDate;
        this.talentID = talentID;
    }

}
