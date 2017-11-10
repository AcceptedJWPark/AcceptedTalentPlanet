package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimListAnswerItem {

    private String Question;
    private String Answer;

    public String getQuestion() {
        return Question;
    }

    public String getAnswer() {
        return Answer;
    }


    public void setQuestion(String QuestionTitle) {
        this.Question = Question;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }


    public CustomerService_ClaimListAnswerItem(String Question, String Answer)
    {
        this.Question = Question;
        this.Answer = Answer;
    }

}
