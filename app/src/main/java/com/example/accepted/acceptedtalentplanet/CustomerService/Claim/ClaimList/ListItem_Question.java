package com.example.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ListItem_Question {

    private String title;
    private String isAnswer;

    public String getTitle() {
        return title;
    }

    public String getIsAnswer() {
        return isAnswer;
    }


    public void setTitle(String ClaimTitle) {
        this.title = ClaimTitle;
    }

    public void setIsAnswer(String AnswerorNot) {
        this.isAnswer = AnswerorNot;
    }


    public ListItem_Question(String title, String isAnswer, String RegistDate)
    {
        this.title = title;
        this.isAnswer = isAnswer;
    }

}
