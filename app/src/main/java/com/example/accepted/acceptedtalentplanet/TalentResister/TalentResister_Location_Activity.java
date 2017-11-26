package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

public class TalentResister_Location_Activity extends AppCompatActivity {
    private String Talent1, Talent2, Talent3;
    private String Location1, Location2, Location3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private String[] Loc;
    AutoCompleteTextView Location_autoEdit1;

    Context mContext;
    ListView location_ListView;
    ArrayList<String> location_ArrayList;
    TalentResister_Location_Adapter talentLocation_Adapter;
    Button location_addBtn;

    LinearLayout TalentResister_LocationLL;
    LinearLayout TalentResister_Location_Txt_LL;
    Button TalentResister_Location_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        mContext = getApplicationContext();

        TalentResister_LocationList talentResisterLocationList = new TalentResister_LocationList();
        final String Location_List[] = talentResisterLocationList.Location_List;
        ArrayAdapter<String> Location_ListAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.talentresister_location_spinnertext,Location_List);
        Location_autoEdit1 = (AutoCompleteTextView) findViewById(R.id.TalentResister_Location);
        Location_autoEdit1.setAdapter(Location_ListAdapter);

        Location_autoEdit1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
        });


        TalentResister_LocationLL = (LinearLayout) findViewById(R.id.TalentResister_LocationLL);
        TalentResister_Location_Txt_LL = (LinearLayout) findViewById(R.id.TalentResister_Location_Txt_LL);
        TalentResister_Location_Btn = (Button) findViewById(R.id.TalentResister_Location_Btn);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_LocationLL_height = metrics.heightPixels/12;
        int TalentResister_Location_Txt_LL_height = metrics.heightPixels/10;
        int TalentResister_Location_Btn_height = metrics.heightPixels/24;

        ViewGroup.LayoutParams params1 = TalentResister_LocationLL.getLayoutParams();
        ViewGroup.LayoutParams params2 = TalentResister_Location_Txt_LL.getLayoutParams();
        ViewGroup.LayoutParams params3 = TalentResister_Location_Btn.getLayoutParams();

        params1.height = TalentResister_LocationLL_height;
        params2.height = TalentResister_Location_Txt_LL_height;
        params3.height = TalentResister_Location_Btn_height;

        TalentResister_LocationLL.setLayoutParams(params1);
        TalentResister_Location_Txt_LL.setLayoutParams(params2);
        TalentResister_Location_Btn.setLayoutParams(params3);



        location_ListView = (ListView) findViewById(R.id.location_ListView);
        location_ArrayList = new ArrayList<>();

        set_Location_ListViewHeightBasedOnItems(location_ListView);

        Intent i = getIntent();
        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");

        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);

        if(HavingDataFlag) {
            Data = (MyTalent)i.getSerializableExtra("Data");
            Loc = Data.getLocationArray();

            for(int index = 0; index < Loc.length; index++)
                location_ArrayList.add(Loc[index]);

        }


        talentLocation_Adapter = new TalentResister_Location_Adapter(mContext, location_ArrayList);
        location_ListView.setAdapter(talentLocation_Adapter);

        location_addBtn = (Button) findViewById(R.id.addLoctionBtn);
        location_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Location_autoEdit1.getText().toString().length()==0)
                {
                    return;
                }
                if(location_ArrayList.size()>=3)
                {
                    return;
                }
                location_ArrayList.add(Location_autoEdit1.getText().toString());
                Location_autoEdit1.setText("");
                talentLocation_Adapter = new TalentResister_Location_Adapter(getBaseContext(), location_ArrayList);
                location_ListView.setAdapter(talentLocation_Adapter);
            }
        });


    }


    public void goNext(View v){
        if(location_ArrayList.size() < 3){
            Toast.makeText(getApplicationContext(), "장소는 3개를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Location1 = location_ArrayList.get(0);
        Location2 = location_ArrayList.get(1);
        Location3 = location_ArrayList.get(2);
        Intent i = new Intent(this, TalentResister_Level_Activity.class);
        i.putExtra("talentFlag", TalentRegister_Flag);
        i.putExtra("talent1", Talent1);
        i.putExtra("talent2", Talent2);
        i.putExtra("talent3", Talent3);
        i.putExtra("loc1", Location1);
        i.putExtra("loc2", Location2);
        i.putExtra("loc3", Location3);
        i.putExtra("HavingDataFlag", HavingDataFlag);
        if(HavingDataFlag){
            i.putExtra("Data", Data);
        }
        startActivity(i);

    }

    public void set_Location_ListViewHeightBasedOnItems(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null)
        {
            return;
        }
        int numberOfItems = listAdapter.getCount();
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        int totalDividersHeight = listView.getDividerHeight() *  (numberOfItems - 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

}
