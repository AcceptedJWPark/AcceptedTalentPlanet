package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2017-11-05.
 */

public class MyProfileData {
    String UserName, Gender, Job, Birth;
    String[] TalentGive, TalentTake;

    public MyProfileData(){

    }

    public void setMyProfile(String UserName, String Gender, String Birth, String Job, String[] TalentGive, String[] TalentTake){
        this.UserName = UserName;
        this.Gender = Gender;
        this.Birth = Birth;
        this.Job = Job;
        this.TalentGive = TalentGive;
        this.TalentTake = TalentTake;
    }

    public String getUserName(){
        return this.UserName;
    }

    public String getGender(){
        return this.Gender;
    }

    public String getBirth(){
        return this.Birth;
    }

    public String getJob(){
        return this.Job;
    }

    public String[] getTalentGive(){
        return this.TalentGive;
    }

    public String[] getTalentTake(){
        return this.TalentTake;
    }

}
