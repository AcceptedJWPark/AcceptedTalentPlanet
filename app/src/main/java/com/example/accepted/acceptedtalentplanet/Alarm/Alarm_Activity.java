package com.example.accepted.acceptedtalentplanet.Alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_ClaimListActivity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_OnebyOneQuestionListActivity;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Popup_Activity;

import java.util.ArrayList;

public class Alarm_Activity extends AppCompatActivity {

    Context mContext;


    ArrayList<Alarm_ListItem> Alarm_ArrayList;
    Alarm_Adapter Alarm_Adapter;
    ListView Alarm_List;

    LinearLayout Alarm_PreBtn;

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

        mContext = getApplicationContext();
        Alarm_ArrayList = new ArrayList<>();

        //TODO:알람 Case에 맞게 데이터 받기.

        Alarm_List = (ListView) findViewById(R.id.Alarm_List);
        Alarm_Adapter = new Alarm_Adapter(Alarm_Activity.this, Alarm_ArrayList);
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.testpicture2,"박종우","2017.12.03 10:24", 1,1));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.testpicture2,"민권홍","2017.12.03 12:45", 1,2));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.textpicture,"김진만","2017.12.04 14:16", 2,1));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.testpicture2,"김용인","2017.12.05 19:18", 2,2));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.testpicture2,"배대명","2017.12.06 20:56", 2,3));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.textpicture,"우승제","2017.12.06 15:19", 2,4));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.testpicture2,"유성택","2017.12.04 02:27", 3,1));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.textpicture,"최지웅","2016.09.08 05:23", 3,2));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.testpicture2,"김정태","2016.10.04 09:51", 3,3));
        Alarm_ArrayList.add(new Alarm_ListItem("2016.11.03 15:41", 4));
        Alarm_ArrayList.add(new Alarm_ListItem("2016.12.01 17:05", 5));
        Alarm_ArrayList.add(new Alarm_ListItem(R.drawable.textpicture,"김대지","2016.10.04 09:51", 6));

        Alarm_List.setAdapter(Alarm_Adapter);
        Alarm_Adapter.notifyDataSetChanged();
        Alarm_List.setOnItemClickListener(mItemClickListener);


        Alarm_PreBtn = (LinearLayout) findViewById(R.id.Alarm_PreBtn);
        Alarm_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(String.valueOf(position),"포지션 값");
            Intent i;
            switch (Alarm_ArrayList.get(position).getactivityChange_CODE()){
                case 1:
                    if (Alarm_ArrayList.get(position).getalarmType_CODE()==1) {
                        i = new Intent(mContext, InterestingList_Activity.class);
                        i.putExtra("TalentFlag", "Give");
                        view.getContext().startActivity(i);
                    }
                    else {
                        i = new Intent(mContext, InterestingList_Activity.class);
                        i.putExtra("TalentFlag", "Take");
                        view.getContext().startActivity(i);
                    }
                    break;
                case 2:
                    if(Alarm_ArrayList.get(position).getalarmType_CODE()==1 || Alarm_ArrayList.get(position).getalarmType_CODE()==3 )
                    {
                    i = new Intent(mContext, TalentCondition_Activity.class);
                    i.putExtra("TalentCondition_TalentFlag", "Give");
                    view.getContext().startActivity(i);
                    }
                    else if (Alarm_ArrayList.get(position).getalarmType_CODE()==2 || Alarm_ArrayList.get(position).getalarmType_CODE()==4) {
                        i = new Intent(mContext, TalentCondition_Activity.class);
                        i.putExtra("TalentCondition_TalentFlag", "Take");
                        view.getContext().startActivity(i);
                    }
                    break;
                case 3:
                    i = new Intent(mContext, TalentSharing_Popup_Activity.class);
                    view.getContext().startActivity(i);
                    break;
                case 4:
                    i = new Intent(mContext, CustomerService_OnebyOneQuestionListActivity.class);
                    view.getContext().startActivity(i);
                    break;

                case 5:
                    i = new Intent(mContext, CustomerService_ClaimListActivity.class);
                    view.getContext().startActivity(i);
                    break;
                //Message case 6:
            }
        }
    };
}