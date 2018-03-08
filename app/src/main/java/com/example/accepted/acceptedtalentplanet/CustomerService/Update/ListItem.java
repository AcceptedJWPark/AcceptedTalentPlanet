package com.example.accepted.acceptedtalentplanet.CustomerService.Update;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ListItem {

    private String title;
    private String summary;
    private String date;

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getUpdateDate() {
        return date;
    }

    public void setTitle(String UpdateTitle) {
        this.title = UpdateTitle;
    }

    public void UpdateSummary(String UpdateSummary) {
        this.summary = UpdateSummary;
    }

    public void Date(String Date) {
        this.date = Date;
    }

    public ListItem(String title, String summary, String date)
    {
        this.title = title;
        this.summary = summary;
        this.date = date;
    }

}
