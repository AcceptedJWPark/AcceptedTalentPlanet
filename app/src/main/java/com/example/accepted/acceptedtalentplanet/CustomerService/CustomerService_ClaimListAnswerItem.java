package com.example.accepted.acceptedtalentplanet.CustomerService;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimListAnswerItem {

    private String Claim_Name;
    private String Claim_Type;
    private String Claim_RegistDate;
    private String Claim;
    private String Claim_Answer;

    public String getClaim_Name() {
        return Claim_Name;
    }

    public String getClaim_Type() {
        return Claim_Type;
    }

    public String getClaim_RegistDate() {
        return Claim_RegistDate;
    }

    public String getClaim() {
        return Claim;
    }
    public String getClaim_Answer() {
        return Claim_Answer;
    }



    public void setClaim_Name(String Claim_Name) {
        this.Claim_Name = Claim_Name;
    }

    public void setClaim_Type(String Claim_Type) {
        this.Claim_Type = Claim_Type;
    }

    public void setClaim_RegistDate(String Claim_RegistDate) {
        this.Claim_RegistDate = Claim_RegistDate;
    }

    public void setClaim(String Claim) {
        this.Claim = Claim;
    }
    public void setClaim_Answer(String Claim_Answer) {
        this.Claim = Claim_Answer;
    }


    public CustomerService_ClaimListAnswerItem(String Claim_Name, String Claim_Type,String Claim_RegistDate,String Claim, String Claim_Answer)
    {
        this.Claim_Name = Claim_Name;
        this.Claim_Type = Claim_Type;
        this.Claim_RegistDate = Claim_RegistDate;
        this.Claim = Claim;
        this.Claim_Answer = Claim_Answer;
    }

}
