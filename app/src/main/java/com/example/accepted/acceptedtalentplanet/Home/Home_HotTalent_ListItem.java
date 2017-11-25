package com.example.accepted.acceptedtalentplanet.Home;

/**
 * Created by Accepted on 2017-09-29.
 */

public class Home_HotTalent_ListItem {

    private String rank;
    private String talent;
    private String category;
    private String number;

    public String getRank() {
        return rank;
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

    public void setRank(String rank) {
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

    public Home_HotTalent_ListItem(String rank, String talent, String category, String number)
        {
            this.rank = rank;
            this.talent = talent;
            this.category = category;
            this.number = number;
    }

}
