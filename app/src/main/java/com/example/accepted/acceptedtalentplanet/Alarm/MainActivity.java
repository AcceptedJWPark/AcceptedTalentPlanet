package com.example.accepted.acceptedtalentplanet.Alarm;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.MyFirebaseMessagingService;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyFirebaseMessagingService.MessageReceivedListener {

    private Context mContext;


    private ArrayList<ListItem> arrayList;
    private Adapter adapter;
    private ListView listView;

    private ImageView lv_PreContainer;
    private ImageView iv_DeleteIcon;

    private boolean idDeleteClicked;




    // 관심 보냄 - 재능 드림 : 1 - 1
    // 공유 진행 - 재능 드림 : 2 - 1
    // 공유 진행 - 관심 재능 : 2 - 2
    // 공유 완료 - 재능 드림 :  2 - 3
    // 공유 완료 - 관심 재능 : 2 - 4
    // 관심 취소 : 3 - 1
    // 진행 취소 - 재능 드림 : 3 - 2
    // 진행 취소 - 관심 재능 : 3 - 3
    // 1:1 문의 답변완료 : 4
    // 신고하기 답변완료 : 5
   // 메세지 : 6


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null)
        {notificationManager.cancel(0);}


        mContext = getApplicationContext();
        arrayList = SaveSharedPreference.getPrefAlarmArry(mContext);

        Log.d("Alarm", "AlarmArray is null: " + (arrayList == null));




        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

        //TODO:알람 Case에 맞게 데이터 받기.

        listView = (ListView) findViewById(R.id.listView_Alarm);
        listView.setOnItemClickListener(mItemClickListener);

        lv_PreContainer = (ImageView) findViewById(R.id.iv_PreContainer_Alarm);
        lv_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_DeleteIcon = (ImageView) findViewById(R.id.iv_DeleteIcon_Alarm);
        idDeleteClicked = false;

        iv_DeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idDeleteClicked) {
                    Log.d("asfd", "a");
                    idDeleteClicked = true;
                    adapter.switchingFlag(idDeleteClicked);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Log.d("asfd", "b");
                    idDeleteClicked = false;
                    listView.setAdapter(adapter);
                    adapter.switchingFlag(idDeleteClicked);
                    adapter.notifyDataSetChanged();
                }
                }

        });



        initView();

    }

    private void initView(){
        arrayList = SaveSharedPreference.getPrefAlarmArry(mContext);

        Log.d("Alarm", "AlarmArray is null: " + (arrayList == null));

        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

        //TODO:알람 Case에 맞게 데이터 받기.

        adapter = new Adapter(MainActivity.this, arrayList);


        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.initFlag();

    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(String.valueOf(position),"포지션 값");
            Intent i;
            switch (arrayList.get(position).getactivityChange_CODE()){
                case 1:
                    if (arrayList.get(position).getalarmType_CODE()==1) {
                        i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.InterestingList.MainActivity.class);
                        i.putExtra("TalentFlag", "Give");
                        view.getContext().startActivity(i);
                    }
                    else {
                        i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.InterestingList.MainActivity.class);
                        i.putExtra("TalentFlag", "Take");
                        view.getContext().startActivity(i);
                    }
                    break;
                case 2:
                    if(arrayList.get(position).getalarmType_CODE()==1 || arrayList.get(position).getalarmType_CODE()==3 )
                    {
                    i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                    i.putExtra("TalentCondition_TalentFlag", "Give");
                    view.getContext().startActivity(i);
                    }
                    else if (arrayList.get(position).getalarmType_CODE()==2 || arrayList.get(position).getalarmType_CODE()==4) {
                        i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                        i.putExtra("TalentCondition_TalentFlag", "Take");
                        view.getContext().startActivity(i);
                    }
                    break;
                case 3:
                    i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity.class);
                    view.getContext().startActivity(i);
                    break;
                case 4:
                    i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList.MainActivity.class);
                    view.getContext().startActivity(i);
                    break;

                case 5:
                    i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList.MainActivity.class);
                    view.getContext().startActivity(i);
                    break;
                //Message case 6:
                case 6:
                    i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity.class);
                    view.getContext().startActivity(i);
                    break;
            }
        }
    };

    @Override
    public void onMessageRecieved(){
        Message msg = handler.obtainMessage();
        handler.sendMessage(msg);
    }

    @Override
    public void onPause(){
        super.onPause();
        MyFirebaseMessagingService.setOnMessageReceivedListener(null);
    }

    @Override
    public void onResume(){
        super.onResume();
        MyFirebaseMessagingService.setOnMessageReceivedListener(this);
    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            initView();
        }
    };

}