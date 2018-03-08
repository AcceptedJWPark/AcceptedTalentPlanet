package com.example.accepted.acceptedtalentplanet.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import java.util.ArrayList;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

public class Home_Activity extends AppCompatActivity {

    Context mContext;
    RecyclerView NewTalent_recyclerView;
    ListView HotTalent_listView;
    ListView BestTalent_listView;
    ArrayList<Home_NewTalent_ListItem> NewTalentList;
    ArrayList<Home_HotTalent_ListItem> HotTalentList;
    ArrayList<Home_BestTalent_ListItem> BestTalentList;
    ArrayList<Home_BestTalent_ListItem> BestTalentListAll;
    Home_NewTalent_ListView_Adapter NewTalent_Adapter;
    Home_HotTalent_ListView_Adapter HotTalent_Adapter;
    Home_BestTalent_ListView_Adapter BestTalent_Adapter;
    LinearLayoutManager NewTalentlayoutManager;

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    ImageView ActionBar_AlarmView;

    Button moreBtn;
    Button initBtn;

    TextView ToolbarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("Home");

        mContext = getApplicationContext();


        ((TextView) findViewById(R.id.toolbarTxt)).setText("Home");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,Home_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(Home_Activity.this,mClicklistener);

        //New Talent 코드
        NewTalent_recyclerView = (RecyclerView) findViewById(R.id.Home_NewTalent_recycler_view);
        NewTalent_recyclerView.setHasFixedSize(true);

        NewTalentlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        NewTalentList = new ArrayList<>();
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기1", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기2", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기3", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기4", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기5", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기6", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기7", "정우성"));
        NewTalentList.add(new Home_NewTalent_ListItem(R.drawable.testpicture, "#연기8", "정우성"));

        NewTalent_Adapter = new Home_NewTalent_ListView_Adapter(NewTalentList);
        NewTalent_recyclerView.setLayoutManager(NewTalentlayoutManager);
        NewTalent_recyclerView.setAdapter(NewTalent_Adapter);

        //Hot Talent 코드
        HotTalent_listView = (ListView) findViewById(R.id.Home_HotTalent_ListView);

        HotTalentList = new ArrayList<>();
        HotTalentList.add(new Home_HotTalent_ListItem("No.1", "#Piano", "예체능 / 음악", "3,781"));
        HotTalentList.add(new Home_HotTalent_ListItem("No.2", "#기타", "예체능 / 음악", "2,181"));
        HotTalentList.add(new Home_HotTalent_ListItem("No.3", "#영어", "외국어 / 영어", "1,741"));
        HotTalentList.add(new Home_HotTalent_ListItem("No.4", "#헬스", "예체능 / 운동", "981"));
        HotTalentList.add(new Home_HotTalent_ListItem("No.5", "#공무원 시험", "시험 / 공무원", "781"));

        HotTalent_Adapter = new Home_HotTalent_ListView_Adapter(mContext, HotTalentList);
        HotTalent_listView.setAdapter(HotTalent_Adapter);


        //Best Talent 코드
        BestTalent_listView = (ListView) findViewById(R.id.Home_BestTalent_ListView);

        BestTalentListAll = new ArrayList<>();
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 1, "#Piano", "정우성", "예체능 / 음악", "37건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 2, "#기타", "정우성", "예체능 / 음악", "21건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 3, "#영어", "정우성", "외국어 / 영어", "15건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 4, "#헬스", "정우성", "예체능 / 운동", "7건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 5, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 6, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 7, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 8, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 9, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 10, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 11, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 12, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 13, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 14, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 15, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 16, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        BestTalentListAll.add(new Home_BestTalent_ListItem(R.drawable.testpicture, 17, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));

        initBestTalentList();

        BestTalent_Adapter = new Home_BestTalent_ListView_Adapter(mContext, BestTalentList);
        BestTalent_listView.setAdapter(BestTalent_Adapter);
        View footer = getLayoutInflater().inflate(R.layout.home_besttalent_footer, null, false);
        BestTalent_listView.addFooterView(footer);

        moreBtn = (Button) findViewById(R.id.testBtn2);
        initBtn = (Button) findViewById(R.id.testBtn2);



        //Drawer Layout Event (아무 곳이나 터치만 해도 Drawer Layout이 그냥 닫힘.)
        slidingMenuDL = (DrawerLayout) findViewById(R.id.Home_listboxDL);

        //ToolBar 적용하기
        drawerView = (View) findViewById(R.id.Home_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.ActionBar_Listview);
        DrawerCloseImg = (ImageView) findViewById(R.id.DrawerCloseImg);
        ActionBar_AlarmView = (ImageView) findViewById(R.id.ActionBar_AlarmView);


        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });

        DrawerCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.closeDrawer(drawerView);
            }
        });

        ActionBar_AlarmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Alarm_Activity.class);
                startActivity(intent);
            }
        });

        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

    }





    public void addBestTalentList(){
        int index = BestTalent_Adapter.getCount();
        int cnt;
        Log.d("addBestTalentCalled", "index = "+ index);
        if(index + 5 <= BestTalentListAll.size())
            cnt = 4;
        else{
            cnt = BestTalentListAll.size() - index - 1;
        }

        for(int i = index - 1; i < index + cnt; i++){
            BestTalentList.add(new Home_BestTalent_ListItem(R.drawable.testpicture, i+2, "#공무원 시험", "정우성", "시험 / 공무원 ", "5건", "-", "Profile 보기"));
        }

        BestTalent_Adapter.notifyDataSetChanged();
        setListViewHeight(BestTalent_listView);
    }

    public void initBestTalentList(){

        BestTalentList = new ArrayList<>();

        int index = (BestTalentListAll.size() > 5)?5:BestTalentListAll.size();
        for(int i = 0; i < index; i++){
            BestTalentList.add(BestTalentListAll.get(i));
        }

        if(BestTalent_Adapter != null){
            BestTalent_Adapter.setItem(BestTalentList);
            BestTalent_Adapter.notifyDataSetChanged();
        }
        setListViewHeight(BestTalent_listView);
    }

    public void moreBtnCilcked(View v){
        addBestTalentList();
        if(BestTalent_Adapter.getCount() == BestTalentListAll.size()){

            moreBtn.setVisibility(View.INVISIBLE);
            initBtn.setVisibility(View.VISIBLE);
        }
    }

    public void initBtnClicked(View v){
        initBestTalentList();

        moreBtn.setVisibility(View.VISIBLE);
        initBtn.setVisibility(View.INVISIBLE);
    }

    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            Log.d("Height = ", String.valueOf(listItem.getMeasuredHeight()));
            totalHeight += listItem.getMeasuredHeight();
        }

        Log.d("Total Height = ", String.valueOf(totalHeight));
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + 30;

        listView.setLayoutParams(params);
        listView.requestLayout();


    }


}