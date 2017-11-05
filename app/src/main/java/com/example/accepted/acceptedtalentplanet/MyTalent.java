package com.example.accepted.acceptedtalentplanet;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by kwonhong on 2017-11-05.
 */

public class MyTalent implements Serializable{
    String Keyword1, Keyword2, Keyword3;
    String Location1, Location2, Location3;
    String Point;
    String Level;
    boolean compFlag = false;
    public MyTalent()
    {

    };
    public void setMyTalent(String Keyword1, String Keyword2, String Keyword3, String Location1, String Location2, String Location3, String Point, String Level){
        this.Keyword1 = Keyword1;
        this.Keyword2 = Keyword2;
        this.Keyword3 = Keyword3;
        this.Location1 = Location1;
        this.Location2 = Location2;
        this.Location3 = Location3;
        this.Point = Point;
        this.Level = Level;
        this.compFlag = true;
    };

    public String[] getKeywordArray(){
        String arr[] = {Keyword1, Keyword2, Keyword3};
        return arr;
    }

    public String[] getLocationArray(){
        String arr[] = {Location1, Location2, Location3};
        return arr;
    }

    public int getPoint(){
        return Integer.parseInt(Point);
    }

    public String getLevel(){
        switch(Level){
            case "1":
                return "시작단계(Beginner)";
            case "2":
                return "초급(Elementary)";
            case "3":
                return "중급(Intermediate)";
            case "4":
                return "상급(Master)";
            default:
                return "전문가(Professional)";
        }
    }

    public int getIntegerLevel(){
        return Integer.parseInt(Level);
    }

    public boolean getCompFlag(){
        return compFlag;
    }

}
