package com.example.accepted.acceptedtalentplanet.FriendList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class FriendList_Activity extends AppCompatActivity {

    Context mContext;
    ListView Friendlist_ListView_Give;
    ListView Friendlist_ListView_Take;
    ArrayList<FriendList_Item> Friendlist_arrayList_Give;
    ArrayList<FriendList_Item> Friendlist_arrayList_Take;
    FriendList_Adapter FriendList_Adapter;
    LinearLayout FriendList_PreBtn;

    Button Friendlist_ShowGive;
    Button Friendlist_ShowTake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlist_activity);

        mContext = getApplicationContext();

        Friendlist_ListView_Give = (ListView) findViewById(R.id.FriendList_ListView_GIVE);
        Friendlist_ListView_Take = (ListView) findViewById(R.id.FriendList_ListView_TAKE);

        Friendlist_arrayList_Give = new ArrayList<>();
        FriendList_Adapter = new FriendList_Adapter(mContext, Friendlist_arrayList_Give);
        Friendlist_ListView_Give.setAdapter(FriendList_Adapter);

        Friendlist_arrayList_Take = new ArrayList<>();
        FriendList_Adapter = new FriendList_Adapter(mContext, Friendlist_arrayList_Take);
        Friendlist_ListView_Take.setAdapter(FriendList_Adapter);

        //TODO:한 개의 어레이 리스트와 어뎁터로 두 개의 리스트뷰에 적용 시킬 수는 없나?
        //TODO:각 리스트 뷰에 해당 회원 프로필 팝업 클릭 이벤트
        Friendlist_arrayList_Give.add(new FriendList_Item(R.drawable.textpicture,"박종우","기타","기타 연주","기타 연습",1,1));
        Friendlist_arrayList_Give.add(new FriendList_Item(R.drawable.textpicture, "민권홍","스타크래프트","스타 리마스터","스타크래프트 파이썬",2,1));
        Friendlist_arrayList_Take.add(new FriendList_Item(R.drawable.textpicture,"우승제","드럼","드럼 연주","드럼 독학",1,2));
        Friendlist_arrayList_Take.add(new FriendList_Item(R.drawable.textpicture, "김용인","영어","영어 말하기","영어 스피킹",2,2));
        Friendlist_arrayList_Take.add(new FriendList_Item(R.drawable.textpicture,"배대명","수학1","미분과 적분","미적분",2,2));
        Friendlist_arrayList_Take.add(new FriendList_Item(R.drawable.textpicture,"유성택","공무원 시험","공무원 7급","공무원 9급",1,2));



        Friendlist_ShowGive = (Button) findViewById(R.id.FriendList_ShowGive);
        Friendlist_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friendlist_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                Friendlist_ShowGive.setTextColor(getResources().getColor(R.color.textColor));
                Friendlist_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                Friendlist_ShowTake.setTextColor(Color.parseColor("#d2d2d2"));
                Friendlist_ListView_Give.setVisibility(View.VISIBLE);
                Friendlist_ListView_Take.setVisibility(View.GONE);
            }
        });
        Friendlist_ShowTake = (Button) findViewById(R.id.FriendList_ShowTake);
        Friendlist_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friendlist_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                Friendlist_ShowTake.setTextColor(getResources().getColor(R.color.textColor));
                Friendlist_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                Friendlist_ShowGive.setTextColor(Color.parseColor("#d2d2d2"));
                Friendlist_ListView_Take.setVisibility(View.VISIBLE);
                Friendlist_ListView_Give.setVisibility(View.GONE);
            }
        });

        FriendList_PreBtn = (LinearLayout) findViewById(R.id.FriendList_PreBtn);
        FriendList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}



