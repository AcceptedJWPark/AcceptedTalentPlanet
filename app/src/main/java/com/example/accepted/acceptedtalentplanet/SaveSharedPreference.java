package com.example.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import static android.text.TextUtils.isEmpty;

/**
 * Created by kwonhong on 2017-10-14.
 */

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String SERVER_IP = "http://13.124.141.242/Accepted/";
    static final String SERVER_IP2 = "http://192.168.123.3:8080/Accepted/";
    static final String PREF_GIVE_DATA = "giveData";
    static final String PREF_TAKE_DATA = "takeData";
    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefUsrName(Context ctx, String userName){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setPrefUsrId(Context ctx, String useId){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, useId);
        editor.commit();
    }
    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserId(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static void clearUserInfo(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }

    public static boolean checkSession(){
        return !isEmpty(PREF_USER_ID);
    }

    public static String getServerIp(){
        return SERVER_IP2;
    }

    public static String getLevel(String Level){
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

    public static void setGiveTalentData(Context ctx, MyTalent Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data);
        editor.putString(PREF_GIVE_DATA, json);
        editor.commit();
    }

    public static MyTalent getGiveTalentData(Context ctx){
        Gson gson = new Gson();
        String json = getSharedPreferences(ctx).getString(PREF_GIVE_DATA, "");
        MyTalent data = gson.fromJson(json, MyTalent.class);
        return data;
    }

    public static void setTakeTalentData(Context ctx, MyTalent Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data);
        editor.putString(PREF_TAKE_DATA, json);
        editor.commit();
    }

    public static MyTalent getTakeTalentData(Context ctx){
        Gson gson = new Gson();
        String json = getSharedPreferences(ctx).getString(PREF_TAKE_DATA, "");
        MyTalent data = gson.fromJson(json, MyTalent.class);
        return data;
    }
}
