package com.example.accepted.acceptedtalentplanet;

/**
 * Created by Accepted on 2017-09-29.
 */

public class BestTalent_ListItem {

    private int picture;
    private int rank;
    private String talent;
    private String category;
    private String number;
    private String name;
    private String bar;
    private String profile;

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public String getTalent() {
        return talent;
    }

    public String getCategory() {
        return category;
    }

    public String getNumber() {
        return number;
    }
    public String getProfile() {
        return profile;

    } public String getBar() {
        return bar;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BestTalent_ListItem(int picture, int rank, String talent, String name, String category, String number,String bar, String profile)
        {
            this.picture = picture;
            this.rank = rank;
            this.talent = talent;
            this.name = name;
            this.category = category;
            this.number = number;
            this.bar = bar;
            this.profile = profile;
    }

}
