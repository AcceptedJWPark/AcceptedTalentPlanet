package com.example.accepted.acceptedtalentplanet.TalentSharing;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharing_ListItem {

    private int picture;
    private String name;
    private String talent1;
    private String talent2;
    private String talent3;
    private String percentage;
    private String showProfile;

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

    public String getPercentage() {
        return percentage;
    }

    public String getShowProfile() {
        return showProfile;
    }

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

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public void setShowProfile(String showProfile) {
        this.showProfile = showProfile;
    }

    public TalentSharing_ListItem(int pictire, String name, String talent1, String talent2, String talent3, String percentage, String showProfile)
    {
        this.picture = pictire;
        this.name = name;
        this.talent1 = talent1;
        this.talent2 = talent2;
        this.talent3 = talent3;
        this.percentage = percentage;
        this.showProfile = showProfile;
    }

}
