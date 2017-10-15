package com.example.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Context mContext;
    RecyclerView NewTalent_recyclerView;
    ListView HotTalent_listView;
    ListView BestTalent_listView;
    ArrayList<NewTalent_ListItem> NewTalentList;
    ArrayList<HotTalent_ListItem> HotTalentList;
    ArrayList<BestTalent_ListItem> BestTalentList;
    NewTalent_ListView_Adapter NewTalent_Adapter;
    HotTalent_ListView_Adapter HotTalent_Adapter;
    BestTalent_ListView_Adapter BestTalent_Adapter;
    LinearLayoutManager NewTalentlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = getApplicationContext();

        //New Talent 코드
        NewTalent_recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        NewTalent_recyclerView.setHasFixedSize(true);

        NewTalentlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        NewTalentList = new ArrayList<>();
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기1", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기2", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기3", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기4", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기5", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기6", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기7", "정우성"));
        NewTalentList.add(new NewTalent_ListItem(R.drawable.textpicture, "#연기8", "정우성"));

        NewTalent_Adapter = new NewTalent_ListView_Adapter(NewTalentList);
        NewTalent_recyclerView.setLayoutManager(NewTalentlayoutManager);
        NewTalent_recyclerView.setAdapter(NewTalent_Adapter);

        //Hot Talent 코드
        HotTalent_listView = (ListView) findViewById(R.id.HotTalent_lv);

        HotTalentList = new ArrayList<>();
        HotTalentList.add(new HotTalent_ListItem("No.1", "#Piano", "예체능 / 음악", "3,781"));
        HotTalentList.add(new HotTalent_ListItem("No.2", "#기타", "예체능 / 음악", "2,181"));
        HotTalentList.add(new HotTalent_ListItem("No.3", "#영어", "외국어 / 영어", "1,741"));
        HotTalentList.add(new HotTalent_ListItem("No.4", "#헬스", "예체능 / 운동", "981"));
        HotTalentList.add(new HotTalent_ListItem("No.5", "#공무원 시험", "시험 / 공무원", "781"));

        HotTalent_Adapter = new HotTalent_ListView_Adapter(mContext, HotTalentList);
        HotTalent_listView.setAdapter(HotTalent_Adapter);


        //Best Talent 코드
        BestTalent_listView = (ListView) findViewById(R.id.BestTalent_lv);

        BestTalentList = new ArrayList<>();
        BestTalentList.add(new BestTalent_ListItem(R.drawable.textpicture, 1, "#Piano", "정우성", "예체능 / 음악", "37건", "-", "Profile 보기"));
        BestTalentList.add(new BestTalent_ListItem(R.drawable.textpicture, 2, "#기타", "정우성", "예체능 / 음악", "21건", "-", "Profile 보기"));
        BestTalentList.add(new BestTalent_ListItem(R.drawable.textpicture, 3, "#영어", "정우성", "외국어 / 영어", "15건", "-", "Profile 보기"));
        BestTalentList.add(new BestTalent_ListItem(R.drawable.textpicture, 4, "#헬스", "정우성", "예체능 / 운동", "7건", "-", "Profile 보기"));
        BestTalentList.add(new BestTalent_ListItem(R.drawable.textpicture, 5, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));

        BestTalent_Adapter = new BestTalent_ListView_Adapter(mContext, BestTalentList);
        BestTalent_listView.setAdapter(BestTalent_Adapter);
        View footer = getLayoutInflater().inflate(R.layout.besttalent_footer, null, false);
        BestTalent_listView.addFooterView(footer);




    }


}