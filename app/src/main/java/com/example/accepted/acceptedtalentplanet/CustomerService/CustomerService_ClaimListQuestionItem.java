package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimListQuestionItem {

    private String ClaimTitle;
    private String AnswerorNot;
    private String RegistDate;

    public String getClaimTitle() {
        return ClaimTitle;
    }

    public String getAnswerorNot() {
        return AnswerorNot;
    }

    public String getRegistDate() {
        return RegistDate;
    }

    public void setClaimTitle(String ClaimTitle) {
        this.ClaimTitle = ClaimTitle;
    }

    public void setAnswerorNot(String AnswerorNot) {
        this.AnswerorNot = AnswerorNot;
    }

    public void setRegistDate(String RegistDate) {
        this.RegistDate = RegistDate;
    }

    public CustomerService_ClaimListQuestionItem(String ClaimTitle, String AnswerorNot, String RegistDate)
    {
        this.ClaimTitle = ClaimTitle;
        this.AnswerorNot = AnswerorNot;
        this.RegistDate = RegistDate;
    }

}
