package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_UpdateListItem {

    private String UpdateTitle;
    private String UpdateSummary;
    private String Date;

    public String getUpdateTitle() {
        return UpdateTitle;
    }

    public String getUpdateSummary() {
        return UpdateSummary;
    }

    public String getUpdateDate() {
        return Date;
    }

    public void setUpdateTitle(String UpdateTitle) {
        this.UpdateTitle = UpdateTitle;
    }

    public void UpdateSummary(String UpdateSummary) {
        this.UpdateSummary = UpdateSummary;
    }

    public void Date(String Date) {
        this.Date = Date;
    }

    public CustomerService_UpdateListItem (String UpdateTitle, String UpdateSummary, String date)
    {
        this.UpdateTitle = UpdateTitle;
        this.UpdateSummary = UpdateSummary;
        this.Date = date;
    }

}
