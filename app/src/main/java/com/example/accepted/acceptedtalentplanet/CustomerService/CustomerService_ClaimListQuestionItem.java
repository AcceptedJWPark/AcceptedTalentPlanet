package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimListQuestionItem {

    private String ClaimTitle;
    private String AnswerorNot;

    public String getClaimTitle() {
        return ClaimTitle;
    }

    public String getAnswerorNot() {
        return AnswerorNot;
    }


    public void setClaimTitle(String ClaimTitle) {
        this.ClaimTitle = ClaimTitle;
    }

    public void setAnswerorNot(String AnswerorNot) {
        this.AnswerorNot = AnswerorNot;
    }


    public CustomerService_ClaimListQuestionItem(String ClaimTitle, String AnswerorNot, String RegistDate)
    {
        this.ClaimTitle = ClaimTitle;
        this.AnswerorNot = AnswerorNot;
    }

}
