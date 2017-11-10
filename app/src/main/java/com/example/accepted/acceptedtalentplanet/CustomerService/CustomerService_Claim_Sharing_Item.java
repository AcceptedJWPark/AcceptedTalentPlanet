package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-09-29.
 */

public class CustomerService_Claim_Sharing_Item {

    private String name;
    private String date;
    private String talentCondition;
    private String keyword1;
    private String keyword2;
    private String keyword3;

    public String getname() {
        return name;
    }

    public String getdate() {
        return date;
    }

    public String gettalentCondition() {
        return talentCondition;
    }

    public String getkeyword1() {
        return keyword1;
    }

    public String getkeyword2() {
        return keyword2;
    }

    public String getkeyword3() {
        return keyword3;
    }


    public void setname(String name) {
        this.name = name;
    }

    public void setdate(String talent) {
        this.date = date;
    }



    public void setTalentCondition(String talentCondition) {
        this.talentCondition = talentCondition;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }


    public CustomerService_Claim_Sharing_Item(String name, String date, String keyword1, String keyword2, String keyword3, String talentCondition)
        {
            this.name = name;
            this.date = date;
            this.keyword1 = keyword1;
            this.keyword2 = keyword2;
            this.keyword3 = keyword3;
            this.talentCondition = talentCondition;
    }

}
