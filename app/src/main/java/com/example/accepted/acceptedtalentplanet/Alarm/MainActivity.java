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

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;


    private ArrayList<ListItem> arrayList;
    private Adapter adapter;
    private ListView listView;

    private LinearLayout ll_PreContainer;
    private LinearLayout ll_DeleteIcon;

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

        mContext = getApplicationContext();
        arrayList = new ArrayList<>();

        //TODO:알람 Case에 맞게 데이터 받기.

        //TODO: 삭제 버튼 누르면 삭제 아이콘 나타나도록.
        listView = (ListView) findViewById(R.id.listView_Alarm);
        adapter = new Adapter(MainActivity.this, arrayList);

        ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_Alarm);
        ll_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        idDeleteClicked = false;
        AlarmArrayList_addData();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(mItemClickListener);


        ll_DeleteIcon = (LinearLayout) findViewById(R.id.ll_DeleteIcon_Alarm);
        ll_DeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idDeleteClicked) {
                    Log.d("asfd", "a");
                    idDeleteClicked = true;
                    listView.setAdapter(adapter);
                    adapter.switchingFlag(idDeleteClicked);
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

    }

    public void AlarmArrayList_addData()
    {
        arrayList.add(new ListItem(R.drawable.testpicture,"박종우","2017.12.03 10:24", 1,1,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"민권홍","2017.12.03 12:45", 1,2,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"김진만","2017.12.04 14:16", 2,1,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"김용인","2017.12.05 19:18", 2,2,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"배대명","2017.12.06 20:56", 2,3,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"우승제","2017.12.06 15:19", 2,4,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"유성택","2017.12.04 02:27", 3,1,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"최지웅","2016.09.08 05:23", 3,2,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"김정태","2016.10.04 09:51", 3,3,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem("2016.11.03 15:41", 4,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem("2016.12.01 17:05", 5,android.R.drawable.presence_busy, idDeleteClicked));
        arrayList.add(new ListItem(R.drawable.testpicture,"김대지","2016.10.04 09:51", 6,android.R.drawable.presence_busy, idDeleteClicked));
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
            }
        }
    };
}