package com.example.accepted.acceptedtalentplanet.SharingList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Accepted on 2017-09-29.
 */

public class SharingList_Activity extends AppCompatActivity {

    Context mContext;
    ListView SharingList_ListView_Give;
    ListView SharingList_ListView_Take;
    ArrayList<SharingList_Item> SharingList_arrayList_Give;
    ArrayList<SharingList_Item> SharingList_arrayList_Take;
    SharingList_Adapter SharingList_Adapter;
    LinearLayout SharingList_PreBtn;

    Button SharingList_ShowGive;
    Button SharingList_ShowTake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharinglist_activity);

        mContext = getApplicationContext();

        SharingList_ListView_Give = (ListView) findViewById(R.id.SharingList_ListView_Give);
        SharingList_ListView_Take = (ListView) findViewById(R.id.SharingList_ListView_Take);

        SharingList_arrayList_Give = new ArrayList<>();
        SharingList_Adapter = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList_Give);
        SharingList_ListView_Give.setAdapter(SharingList_Adapter);

        SharingList_arrayList_Take = new ArrayList<>();
        SharingList_Adapter = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList_Take);
        SharingList_ListView_Take.setAdapter(SharingList_Adapter);

        //TODO:한 개의 어레이 리스트와 어뎁터로 두 개의 리스트뷰에 적용 시킬 수는 없나?
        SharingList_arrayList_Give.add(new SharingList_Item("박종우"+"님과 ",3,"2017.12.02 04:14","기타","기타 연주","기타 독학",1));
        SharingList_arrayList_Give.add(new SharingList_Item("민권홍"+"님과 ",4,"2017.12.03 11:22","스타크래프트","스타 리마스터","스타크래프트 파이썬",1));
        SharingList_arrayList_Give.add(new SharingList_Item("배대명"+"님과 ",4,"2017.12.04 13:01","농구","길거리 농구","농구 드리블",1));
        SharingList_arrayList_Give.add(new SharingList_Item("우승제"+"님과 ",5,"2017.12.04 19:22","축구","축구 드리블","축구 프리킥",1));
        SharingList_arrayList_Give.add(new SharingList_Item("우승제"+"님의 ",1,"2017.12.07 19:22","축구","축구 드리블","축구 프리킥",1));
        SharingList_arrayList_Give.add(new SharingList_Item("우승제"+"님의 ",2,"2017.12.09 19:22","수학","수능 수학","미적분",1));

        SharingList_arrayList_Take.add(new SharingList_Item("유성택"+"님과 ",1,"2017.12.01 14:24","노래","보컬","오디션",2));
        SharingList_arrayList_Take.add(new SharingList_Item("유성택"+"님과 ",2,"2017.12.03 14:24","연기","뮤지컬","공연",2));
        SharingList_arrayList_Take.add(new SharingList_Item("유성택"+"님과 ",3,"2017.12.03 14:24","드럼","드럼 연주","드럼 독학",2));
        SharingList_arrayList_Take.add(new SharingList_Item("김진만"+"님과 ",4,"2017.12.04 14:24","영어","영어 말하기","영어 스피킹",2));
        SharingList_arrayList_Take.add(new SharingList_Item("임하슬람"+"님과 ",4,"2017.12.02 14:24","수학1","미분과 적분","미적분",2));
        SharingList_arrayList_Take.add(new SharingList_Item("윤택"+"님과 ",5,"2017.12.02 14:24","공무원 시험","공무원 7급","공무원 9급",2));



        SharingList_ShowGive = (Button) findViewById(R.id.SharingList_ShowGive);
        SharingList_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingList_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                SharingList_ShowGive.setTextColor(getResources().getColor(R.color.textColor));
                SharingList_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                SharingList_ShowTake.setTextColor(Color.parseColor("#d2d2d2"));
                SharingList_ListView_Give.setVisibility(View.VISIBLE);
                SharingList_ListView_Take.setVisibility(View.GONE);
            }
        });
        SharingList_ShowTake = (Button) findViewById(R.id.SharingList_ShowTake);
        SharingList_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingList_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                SharingList_ShowTake.setTextColor(getResources().getColor(R.color.textColor));
                SharingList_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                SharingList_ShowGive.setTextColor(Color.parseColor("#d2d2d2"));
                SharingList_ListView_Take.setVisibility(View.VISIBLE);
                SharingList_ListView_Give.setVisibility(View.GONE);
            }
        });

        SharingList_PreBtn = (LinearLayout) findViewById(R.id.SharingList_PreBtn);
        SharingList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}



