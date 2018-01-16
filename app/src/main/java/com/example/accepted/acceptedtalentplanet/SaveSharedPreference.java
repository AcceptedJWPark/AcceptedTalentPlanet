package com.example.accepted.acceptedtalentplanet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_SearchingPage_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;

/**
 * Created by kwonhong on 2017-10-14.
 */

public class SaveSharedPreference{
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String SERVER_IP = "http://13.124.141.242/Accepted/";
    static final String SERVER_IP2 = "http://192.168.123.3:8080/Accepted/";
    static final String PREF_GIVE_DATA = "giveData";
    static final String PREF_TAKE_DATA = "takeData";
    static final String PREF_GEO_POINT1 = "geoPoint1";
    static final String PREF_GEO_POINT2 = "geoPoint2";
    static final String PREF_GEO_POINT3 = "geoPoint3";
    static final String PREF_FRIEND_ARRAY = "friendList";
    static final String PREF_TALENT_POINT = "talentPoint";

    static DrawerLayout slidingMenuDL;
    static View drawerView;


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

    public static void setPrefTalentPoint(Context ctx, int point){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_TALENT_POINT, point);
        editor.commit();
    }

    public static int getTalentPoint(Context ctx){
        return getSharedPreferences(ctx).getInt(PREF_TALENT_POINT, 0);
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

    public static String getLevel(String Level) {
        switch (Level) {
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

    public static void setGeoPointArr(Context ctx, GeoPoint[] Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data[0]);
        editor.putString(PREF_GEO_POINT1, json);
        json = gson.toJson(Data[1]);
        editor.putString(PREF_GEO_POINT2, json);
        json = gson.toJson(Data[2]);
        editor.putString(PREF_GEO_POINT3, json);
        editor.commit();
    }

    public static GeoPoint[] getGeoPointArr(Context ctx){
        Gson gson = new Gson();
        GeoPoint[] data = new GeoPoint[3];

        String json = getSharedPreferences(ctx).getString(PREF_GEO_POINT1, "");
        data[0] = gson.fromJson(json, GeoPoint.class);
        json = getSharedPreferences(ctx).getString(PREF_GEO_POINT2, "");
        data[1] = gson.fromJson(json, GeoPoint.class);
        json = getSharedPreferences(ctx).getString(PREF_GEO_POINT3, "");
        data[2] = gson.fromJson(json, GeoPoint.class);

        return data;
    }

    public static byte[] getFileDataFromDrawable(Context ctx, int id){
        Drawable drawable = ContextCompat.getDrawable(ctx, id);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] getFileDataFromDrawable(Context ctx, Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void putFriend(Context ctx, Friend friend){
        ArrayList<Friend> friendList = getFriendList(ctx);
        friendList.add(friend);

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(friendList);
        editor.putString(PREF_FRIEND_ARRAY, json);
        editor.commit();

    }

    public static void removeFriend(Context ctx, Friend friend){
        ArrayList<Friend> frinedList = getFriendList(ctx);

        for(Friend f : frinedList){
            if(f.getUserID().equals(friend.getUserID()) && f.getPartnerTalentType().equals(friend.getPartnerTalentType())){
                frinedList.remove(f);
                break;
            }
        }

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(frinedList);
        editor.putString(PREF_FRIEND_ARRAY, json);
        editor.commit();
    }

    public static ArrayList<Friend> getFriendList(Context ctx){
        Gson gson = new Gson();

        String json = getSharedPreferences(ctx).getString(PREF_FRIEND_ARRAY, "");
        Type listType = new TypeToken<ArrayList<Friend>>(){}.getType();
        ArrayList<Friend> friendList = gson.fromJson(json, listType);
        if(friendList == null)
            friendList = new ArrayList<>();

        return friendList;
    }


    public static void DrawerLayout_ClickEvent(Context context, View.OnClickListener listener){

        ((ImageView) ((Activity)context).findViewById(R.id.ActionBar_Listview)).setOnClickListener(listener);
        ((ImageView) ((Activity)context).findViewById(R.id.DrawerCloseImg)).setOnClickListener(listener);
        ((ImageView) ((Activity)context).findViewById(R.id.ActionBar_AlarmView)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_Profile)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_MyTalentResister)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_MyTalentProcess)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_TSharing)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_TalentSearching)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_SharingList)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_MessageBox)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_FriendList)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_ServiceCenter)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_System)).setOnClickListener(listener);
        ((LinearLayout) ((Activity)context).findViewById(R.id.SlidingMenu_LogOut)).setOnClickListener(listener);

    }
    public static void DrawerLayout_Open(View view, Context mContext, DrawerLayout drawerLayout, View drawerView) {

        Intent i;

        switch (view.getId())
        {

            case R.id.ActionBar_Listview :
            {
                drawerLayout.openDrawer(drawerView);
                break;
            }

            case R.id.DrawerCloseImg :
            {
                drawerLayout.closeDrawer(drawerView);
                break;
            }

            case R.id.ActionBar_AlarmView :
            {
                i = new Intent(mContext, Alarm_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_Profile : {
                i = new Intent(mContext, MyProfile_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_MyTalentResister : {
                i = new Intent(mContext, TalentResister_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_MyTalentProcess : {
                i = new Intent(mContext, TalentCondition_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_TSharing : {
                i = new Intent(mContext, TalentSharing_Activity.class);
                mContext.startActivity(i);
                break;
            }
            case R.id.SlidingMenu_TalentSearching : {
                i = new Intent(mContext, TalentSearching_SearchingPage_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_SharingList : {
                i = new Intent(mContext, SharingList_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_MessageBox : {
                break;
            }
            case R.id.SlidingMenu_FriendList : {
                i = new Intent(mContext, FriendList_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_ServiceCenter : {
                i = new Intent(mContext, CustomerService_MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_System : {
                i = new Intent(mContext, System_Activity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_LogOut : {
                clearUserInfo(mContext);
                i = new Intent(mContext, Login_Activity.class);
                mContext.startActivity(i);
                ((Activity)mContext).finish();
                break;

            }
        }
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
