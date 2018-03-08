package com.example.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ListItem_Answer {

    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }


    public void setQuestion(String QuestionTitle) {
        this.question = question;
    }

    public void setAnswer(String Answer) {
        this.answer = Answer;
    }


    public ListItem_Answer(String Question, String Answer)
    {
        this.question = Question;
        this.answer = Answer;
    }

}
