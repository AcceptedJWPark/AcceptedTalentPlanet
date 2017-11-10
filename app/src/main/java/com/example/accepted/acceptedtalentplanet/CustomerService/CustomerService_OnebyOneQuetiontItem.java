package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_OnebyOneQuetiontItem {

    private String QuestionTitle;
    private String AnswerorNot;
    private String RegistDate;

    public String getQuestionTitle() {
        return QuestionTitle;
    }

    public String getAnswerorNot() {
        return AnswerorNot;
    }

    public String getRegistDate() {
        return RegistDate;
    }

    public void setQuestionTitle(String QuestionTitle) {
        this.QuestionTitle = QuestionTitle;
    }

    public void setAnswerorNot(String AnswerorNot) {
        this.AnswerorNot = AnswerorNot;
    }

    public void setRegistDate(String RegistDate) {
        this.RegistDate = RegistDate;
    }

    public CustomerService_OnebyOneQuetiontItem(String QuestionTitle, String AnswerorNot, String RegistDate)
    {
        this.QuestionTitle = QuestionTitle;
        this.AnswerorNot = AnswerorNot;
        this.RegistDate = RegistDate;
    }

}
