package com.example.accepted.acceptedtalentplanet.CustomerService.Notice;

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

    public String getDate() {
        return date;
    }

    public void setTitle(String NoticeTitle) {
        this.title = NoticeTitle;
    }

    public void NoticeSummary(String NoticeSummary) {
        this.summary = NoticeSummary;
    }

    public void NoticeDate(String Date) {
        this.date = Date;
    }

    public ListItem(String title, String summary, String date)
    {
        this.title = title;
        this.summary = summary;
        this.date = date;
    }

}
