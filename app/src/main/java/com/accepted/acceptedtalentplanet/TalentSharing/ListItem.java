package com.accepted.acceptedtalentplanet.TalentSharing;

/**
 * Created by Accepted on 2017-10-24.
 */

public class ListItem implements Comparable<ListItem> {

    private int picture;
    private String name;
    private String talent1;
    private String talent2;
    private String talent3;
    private String talentID;
    private boolean isGiveTalent;
    private String status_flag;
    private String distance;
    private String userID;
    private String showProfile;
    private double distance_Compare;
    private String imagePath;

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

    public String getTalentID(){
        return talentID;
    }
    
    public boolean getTalentFlag() { return isGiveTalent; }
    
    public String getStatusFlag() { return status_flag; }
    
    public String getdistance() {
        return distance;
    }

    public String getShowProfile() {
        return showProfile;
    }

    public String getUserID(){ return userID; }

    public String getImagePath() { return imagePath; }

    public void setPicture(int picture) {
        this.picture = picture;
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

    public void setTalentID(String talentID){
        this.talentID = talentID;
    }

    public void setTalentFlag(String isGiveTalent) { this.isGiveTalent = (isGiveTalent.equals("Y")) ? true : false; }

    public void setStatusFlag(String status_flag) { this.status_flag = status_flag; }

    public void setdistance(String distance) {
        this.distance = distance;
    }

    public void setShowProfile(String showProfile) {
        this.showProfile = showProfile;
    }

    public void setUserID(String userID) { this.userID = userID; }

    public ListItem(int pictire, String name, String talent1, String talent2, String talent3, String talentID, String isGiveTalent, String status_flag, String distance, String showProfile, String userID, double distance_Compare, String imagePath)
    {
        this.picture = pictire;
        this.name = name;
        this.talent1 = talent1;
        this.talent2 = talent2;
        this.talent3 = talent3;
        this.talentID = talentID;
        this.isGiveTalent = (isGiveTalent.equals("Y")) ? true : false;
        this.status_flag = status_flag;
        this.distance = distance;
        this.showProfile = showProfile;
        this.userID = userID;
        this.distance_Compare = distance_Compare;
        this.imagePath = imagePath;
    }

    @Override
    public int compareTo(ListItem item){
        if(this.distance_Compare > item.distance_Compare)
            return 1;
        else if(this.distance_Compare < item.distance_Compare)
            return -1;
        else
            return 0;
    }

}
