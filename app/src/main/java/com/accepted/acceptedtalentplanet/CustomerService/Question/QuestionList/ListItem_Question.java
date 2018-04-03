package com.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ListItem_Question {

    private String title;
    private String isAnswer;
    private String date;

    public String getTitle() {
        return title;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String QuestionTitle) {
        this.title = QuestionTitle;
    }

    public void setIsAnswer(String AnswerorNot) {
        this.isAnswer = AnswerorNot;
    }

    public void setDate(String RegistDate) {
        this.date = RegistDate;
    }

    public ListItem_Question(String title, String isAnswer, String date)
    {
        this.title = title;
        this.isAnswer = isAnswer;
        this.date = date;
    }

}
