package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_NoticeListItem {

    private String NoticeTitle;
    private String NoticeSummary;
    private String NoticeDate;

    public String getNoticeTitle() {
        return NoticeTitle;
    }

    public String getNoticeSummary() {
        return NoticeSummary;
    }

    public String getNoticeDate() {
        return NoticeDate;
    }

    public void setNoticeTitle(String NoticeTitle) {
        this.NoticeTitle = NoticeTitle;
    }

    public void NoticeSummary(String NoticeSummary) {
        this.NoticeSummary = NoticeSummary;
    }

    public void NoticeDate(String Date) {
        this.NoticeDate = Date;
    }

    public CustomerService_NoticeListItem(String NoticeTitle, String NoticeSummary, String NoticeDate)
    {
        this.NoticeTitle = NoticeTitle;
        this.NoticeSummary = NoticeSummary;
        this.NoticeDate = NoticeDate;
    }

}
