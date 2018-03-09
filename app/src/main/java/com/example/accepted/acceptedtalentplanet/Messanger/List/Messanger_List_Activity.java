package com.example.accepted.acceptedtalentplanet.Messanger.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Messanger_List_Activity extends AppCompatActivity {

    Context mContext;
    ArrayList<Messanger_List_Item> messanger_Arraylist;
    Messanger_List_Adapter messanger_ArrayAdapter;
    ListView messanger_Listview;

    LinearLayout MessangerList_Prebtn;
    LinearLayout MessangerList_Deletebtn;

    boolean deleteBtn_Clicked;

    SQLiteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messanger_list);

        String dbName = "/accepted.db";
        try {
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        mContext = getApplicationContext();
        messanger_Arraylist = new ArrayList<>();
        messanger_ArrayAdapter = new Messanger_List_Adapter(messanger_Arraylist, Messanger_List_Activity.this);

        messanger_Listview = (ListView) findViewById(R.id.Messanger_List_ListView);
        messanger_Listview.setAdapter(messanger_ArrayAdapter);
        MessangerList_Deletebtn = (LinearLayout) findViewById(R.id.MessangerList_DeleteBtn);
        MessangerList_Prebtn = (LinearLayout) findViewById(R.id.MessangerList_PreBtn);
        MessangerList_Prebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn_Clicked = false;
        MessangerList_Deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!deleteBtn_Clicked)
                {
                    deleteBtn_Clicked = true;
                    messanger_Listview.setAdapter(messanger_ArrayAdapter);
                    messanger_ArrayAdapter.switchFlag_deleteBtn(true);
                    messanger_ArrayAdapter.notifyDataSetChanged();
                }
                else
                {
                    deleteBtn_Clicked = false;
                    messanger_Listview.setAdapter(messanger_ArrayAdapter);
                    messanger_ArrayAdapter.switchFlag_deleteBtn(false);
                    messanger_ArrayAdapter.notifyDataSetChanged();
                }
            }
        });

//        messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture,"박종우", "안녕하세요 박종우 입니다.","PM 16:12", 3,false));
//        messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture,"민권홍", "안녕하세요 민권홍 입니다.","18/Mar/05", 2,false));
//        messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture,"김용인", "안녕하세요 김용인 입니다.","18/Mar/06", 0,false));

        refreshChatLog();
    }

    public void refreshChatLog(){
        String selectBasicChat = "SELECT D01.ROOM_ID, D01.USER_NAME, D01.PICTURE, D03.UNREADED_COUNT, D06.CONTENT, D06.CREATION_DATE, D01.USER_ID, D01.START_MESSAGE_ID\n" +
                "FROM   TB_CHAT_ROOM D01\n" +
                "\t   LEFT OUTER JOIN (SELECT D02.ROOM_ID, COUNT(D02.ROOM_ID) AS UNREADED_COUNT\n" +
                "        FROM   TB_CHAT_LOG D02\n" +
                "        WHERE  D02.READED_FLAG = 'N'\n" +
                "        GROUP BY D02.ROOM_ID) D03 ON D01.ROOM_ID = D03.ROOM_ID\n" +
                "        LEFT OUTER JOIN (SELECT D04.ROOM_ID, D04.CONTENT, D04.CREATION_DATE\n" +
                "         FROM   TB_CHAT_LOG D04\n" +
                "         WHERE  D04.MESSAGE_ID IN (SELECT MAX(D05.MESSAGE_ID)\n" +
                "\t\t\t\t\t\t\t\t   FROM   TB_CHAT_LOG D05\n" +
                "                                   GROUP BY D05.ROOM_ID)) D06 ON D01.ROOM_ID = D06.ROOM_ID" +
                " WHERE D01.ACTIVATE_FLAG = 'Y'";
        Cursor cursor = sqliteDatabase.rawQuery(selectBasicChat, null);
        cursor.moveToFirst();


        boolean isRunning = false;

        final Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
        final String nowDateStr = simpleDateFormat.format(date);
        String[] nowDateTemp = nowDateStr.split(",");
        final String nowDate = nowDateTemp[0];
        while(!cursor.isAfterLast()){
            int roomID = cursor.getInt(0);
            String userName = cursor.getString(1);
            String pictureData = (cursor.getString(2) == null)? "NODATA" : cursor.getString(2);
            int unreadedCount = cursor.getInt(3);
            String lastMessage = (cursor.getString(4)==null) ? "NODATA" : cursor.getString(4);
            String lastDate = (cursor.getString(5) == null)? "NODATA" : cursor.getString(5);
            String userID = cursor.getString(6);

            if(!lastDate.equals("NODATA")) {
                String[] dateTemp = lastDate.split(",");
                lastDate = dateTemp[0];

                String dateTime = dateTemp[1].substring(0, 8);
                lastDate = (lastDate.equals(nowDate))?dateTime : lastDate;
            }



            Log.d("datas = ", roomID + userName + pictureData + unreadedCount + lastMessage + lastDate);
            Bitmap picture = null;
            if(pictureData != null && !pictureData.equals("NODATA")){
                picture = SaveSharedPreference.StringToBitMap(pictureData);
            }
            messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture, userName, userID, lastMessage ,lastDate, unreadedCount, false, roomID, picture));
            cursor.moveToNext();
        }
        cursor.close();
        sqliteDatabase.close();
        messanger_Listview.setAdapter(messanger_ArrayAdapter);
        messanger_ArrayAdapter.notifyDataSetChanged();
    }




}
