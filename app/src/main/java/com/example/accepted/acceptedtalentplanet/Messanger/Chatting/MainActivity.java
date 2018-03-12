package com.example.accepted.acceptedtalentplanet.Messanger.Chatting;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Loading_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-03-06.
 */

public class MainActivity extends AppCompatActivity {

    Context mContext;
    ListView messanger_Chatting_LV;
    Adapter messanger_chatting_adapter;
    ArrayList<ListItem> messanger_chatting_ArrayList;
    LinearLayout Messanger_Chatting_SendBtn;
    EditText Messanger_Chatting_EditTxt;
    TextView Massanger_title;

    int interval = 100;
    final int maxInterval = 5000;

    String receiverID;
    int roomID;

    LinearLayout Messanger_Chatting_EditTxtLL;

    boolean time_Changed;
    boolean date_Changed;
    int message_Type; // Send : 1, Get : 2
    boolean picture_Type;

    // SQLite 설정
    SQLiteDatabase sqliteDatabase = null;

    Thread thread1;
    boolean running;

    String lastMessageID = "0";
    Bitmap picture = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messanger_chatting);

        mContext = getApplicationContext();
        receiverID = getIntent().getStringExtra("userID");
        roomID = getIntent().getIntExtra("roomID", 0);

        Massanger_title = (TextView)findViewById(R.id.Massanger_title);
        Massanger_title.setText(getIntent().getStringExtra("userName"));

        String dbName = "/accepted.db";
        try {
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        String getStartMessageID = "SELECT START_MESSAGE_ID FROM TB_CHAT_ROOM WHERE ROOM_ID = " + roomID;
        Cursor cursor = sqliteDatabase.rawQuery(getStartMessageID, null);
        cursor.moveToFirst();

        lastMessageID = cursor.getString(0);



        messanger_chatting_ArrayList = new ArrayList<>();
        messanger_chatting_adapter = new Adapter(messanger_chatting_ArrayList, mContext);
        messanger_chatting_adapter = new Messanger_Chatting_Adapter(messanger_chatting_ArrayList, mContext, picture);

        messanger_Chatting_LV = (ListView) findViewById(R.id.Messanger_Chatting_ListView);

        Messanger_Chatting_SendBtn = (LinearLayout) findViewById(R.id.Messanger_Chatting_SendBtn);
        Messanger_Chatting_EditTxt = (EditText) findViewById(R.id.Messanger_Chatting_EditTxt);
        Messanger_Chatting_EditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                InputMethodManager inputMethodManager = (InputMethodManager) Messanger_Chatting.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


        Messanger_Chatting_EditTxtLL = (LinearLayout) findViewById(R.id.Messanger_Chatting_EditTxtLL);


        Messanger_Chatting_SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String chattingEditTxt = Messanger_Chatting_EditTxt.getText().toString();

                if(chattingEditTxt.isEmpty()){
                    Toast.makeText(mContext, "메세지를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendMessage(chattingEditTxt);
                Messanger_Chatting_EditTxt.setText("");

            }
        });


        getPicture();

    }

    public boolean refreshChatLog(){
        String selectBasicChat = "SELECT * FROM TB_CHAT_LOG WHERE ROOM_ID = " + roomID +" AND MESSAGE_ID > "+lastMessageID+"";
        Cursor cursor = sqliteDatabase.rawQuery(selectBasicChat, null);
        cursor.moveToFirst();
        boolean isRunning = false;
        while(!cursor.isAfterLast()){
            isRunning = true;

            String sender = cursor.getString(2);
            String content = cursor.getString(3);
            String creationDate = cursor.getString(4);
            String[] nowDateTemp = creationDate.split(",");
            final String nowDate = nowDateTemp[0];
            final String nowTime = nowDateTemp[1].substring(0, 8);

            if(sender.equals(receiverID)) {
                message_Type = 2;
                picture_Type = true;
            }
            else{
                message_Type = 1;
                picture_Type = false;
            }

            if(messanger_chatting_ArrayList.size() == 0){
                time_Changed = true;
                date_Changed = true;
                messanger_chatting_ArrayList.add(new Messanger_Chatting_Item(R.drawable.testpicture, content, creationDate, message_Type, picture_Type, time_Changed, date_Changed));
            }else{
                int prevPosition = messanger_chatting_ArrayList.size() - 1;
                Messanger_Chatting_Item temp = messanger_chatting_ArrayList.get(prevPosition);
                String prevDate = temp.getMessanger_Date();
                String prevTime;
                String[] dateTemp = prevDate.split(",");
                prevDate = dateTemp[0];
                if(!chattingEditTxt.equals(""))
                {
                int prevPosition = 0;
                if (messanger_chatting_ArrayList.size() == 0) {
                    messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", 1, false, true, false));
                    messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
                    messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount()-1);
                    messanger_chatting_adapter.notifyDataSetChanged();
                    return;
                } else if (messanger_chatting_ArrayList.size() > 0) {
                    prevPosition = messanger_chatting_ArrayList.size() - 1;
                    ListItem temp = messanger_chatting_ArrayList.get(prevPosition);
                    picture_Type = false;
                    message_Type = 1;

                prevTime = dateTemp[1].substring(0, 8);
                if(prevDate.equals(nowDate)){
                    date_Changed = false;
                    if(temp.getMessage_Type() != message_Type){
                        time_Changed = true;
                    }else {
                        if(prevTime.equals(nowTime)){
                            if(message_Type == 2){
                                picture_Type = false;
                            }
                            temp.setTime_Changed(false);
                            messanger_chatting_ArrayList.set(prevPosition, temp);
                        }
                    }
                }else{
                    date_Changed = true;
                    time_Changed = true;
                }

                messanger_chatting_ArrayList.add(new Messanger_Chatting_Item(R.drawable.testpicture, content, creationDate, message_Type, picture_Type, time_Changed, date_Changed));

            }
            cursor.moveToNext();
        }



        if(isRunning){
            cursor.moveToPrevious();
            lastMessageID = String.valueOf(cursor.getInt(0));
            Log.d("lastMessageID", lastMessageID);

            String updateSql = "UPDATE TB_CHAT_LOG SET READED_FLAG = 'Y' WHERE ROOM_ID = " + roomID;
            sqliteDatabase.execSQL(updateSql);
        }

        cursor.close();

        return isRunning;
    }

    public void sendMessage(final String content){


        final Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
        final String nowDateStr = simpleDateFormat.format(date);
        String[] nowDateTemp = nowDateStr.split(",");
        final String nowDate = nowDateTemp[0];
        final String nowTime = nowDateTemp[1].substring(0, 8);

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Chat/sendMessage.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    refreshChatLog();
                    Log.d("result = ", response);
                    JSONObject obj = new JSONObject(response);
                    SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm");
                    String dateStr = sdf.format(date);
                    if(obj.getString("MESSAGE_ID") != null && !obj.getString("MESSAGE_ID").isEmpty()){
                        sqliteDatabase.execSQL("INSERT INTO TB_CHAT_LOG(MESSAGE_ID, ROOM_ID, USER_ID, CONTENT, CREATION_DATE) VALUES (" + obj.getString("MESSAGE_ID") + ","+roomID+" ,'"+SaveSharedPreference.getUserId(mContext)+"','"+content+"','"+nowDateStr+"')");
                    }else{
                        Toast.makeText(mContext, "메세지 전송 실패.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("senderID", SaveSharedPreference.getUserId(mContext));
                params.put("receiverID", receiverID);
                params.put("content", content);
                params.put("sendDate", nowDateStr);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getPicture(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Chat/getPicture.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getString("FILE_DATA").equals("Tk9EQVRB")) {
                        Log.d("picture", obj.getString("FILE_DATA"));
                        picture = SaveSharedPreference.StringToBitMap(obj.getString("FILE_DATA"));
                        sqliteDatabase.execSQL("UPDATE TB_CHAT_ROOM SET PICTURE = '" + obj.getString("FILE_DATA") + "' WHERE ROOM_ID = " + roomID);

                    }

                    messanger_chatting_adapter = new Messanger_Chatting_Adapter(messanger_chatting_ArrayList, mContext, picture);

                    if(refreshChatLog()) {
                        messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
                        messanger_chatting_adapter.notifyDataSetChanged();
                        messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount() - 1);
                    }
                    thread1 = new Messanger_Chatting.PollingThread();
                    thread1.start();
                            if (messanger_chatting_ArrayList.get(prevPosition).getMessage_Type() == 2)
                            {
                                time_Changed = true;
                            }
                            else{
                                temp.setTime_Changed(false);
                                time_Changed = true;
                            }
                        }
                    }
                    messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", message_Type, picture_Type, time_Changed, date_Changed));
                    messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
                    messanger_chatting_adapter.notifyDataSetChanged();
                messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount()-1);
                Messanger_Chatting_EditTxt.setText("");
                }
                else
                {
                    return;
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", receiverID);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    protected void onResume(){
        super.onResume();

        running = true;
    }

    protected void onPause(){
        super.onPause();

        running = false;
    }

    class PollingThread extends Thread {
        @Override
        public void run(){
            while(running){
                Bundle bd = new Bundle();
                if(refreshChatLog()){
                    bd.putBoolean("arg", true);
                }else{
                    bd.putBoolean("arg", false);
                }

                Message msg = m_hd.obtainMessage();
                msg.setData(bd);

                m_hd.sendMessage(msg);

                try {
                    Thread.sleep(interval);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    Handler m_hd = new Handler(){
        public void handleMessage(android.os.Message msg){
            Bundle bd = msg.getData();

            boolean isRunning = bd.getBoolean("arg", false);
            if(isRunning){
                messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
                messanger_chatting_adapter.notifyDataSetChanged();
                messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount() - 1);
            }
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        running = false;
        if(thread1 != null)
            thread1.interrupt();
        sqliteDatabase.close();
    }



//        Messanger_Chatting_GetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final String chattingEditTxt = Messanger_Chatting_EditTxt.getText().toString();
//                Messanger_Chatting_EditTxt.setText("");
//
//                message_Type = 2;
//                picture_Type = true;
//
//                if (Messanger_Chatting_TimeChanged.isChecked()) {
//                    time_Changed = true;
//                } else {
//                    time_Changed = false;
//                }
//
//
//                if (Messanger_Chatting_DateChanged.isChecked()) {
//                    date_Changed = true;
//                } else {
//                    date_Changed = false;
//                }
//
//                if(!chattingEditTxt.equals("")) {
//                    int prevPosition = 0;
//                    if (messanger_chatting_ArrayList.size() == 0) {
//                        messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", 2, true, true, false));
//                        messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
//                        messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount() - 1);
//                        return;
//                    } else if (messanger_chatting_ArrayList.size() > 0) {
//                        prevPosition = messanger_chatting_ArrayList.size() - 1;
//                        ListItem temp = messanger_chatting_ArrayList.get(prevPosition);
//                        picture_Type = true;
//                        message_Type = 2;
//                        Log.d("time_Changed1 = ", String.valueOf(time_Changed));
//
//                        if (date_Changed) {
//                            picture_Type = true;
//                            time_Changed = true;
//                        } else if (!time_Changed) {
//                            messanger_chatting_ArrayList.set(prevPosition, temp);
//                            if (messanger_chatting_ArrayList.get(prevPosition).getMessage_Type() == 1) {
//                                picture_Type = true;
//                                time_Changed = true;
//                            } else {
//                                time_Changed = true;
//                                picture_Type = false;
//                                temp.setTime_Changed(false);
//                            }
//                            Log.d("time_Changed2 = ", String.valueOf(time_Changed));
//                        } else {
//                            temp.setTime_Changed(true);
//                            messanger_chatting_ArrayList.set(prevPosition, temp);
//                            System.out.println(time_Changed);
//                            Log.d("time_Changed3 = ", String.valueOf(time_Changed));
//                        }
//                    }
//                    messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", message_Type, picture_Type, time_Changed, date_Changed));
//                    messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
//                    messanger_chatting_adapter.notifyDataSetChanged();
//                    messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount() - 1);
//                }
//                else
//                {
//                    return;
//                }
//            }
//        });


}
