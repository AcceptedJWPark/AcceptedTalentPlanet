package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.LocationList.LocationList;
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
    Talent_Location_Adapter talentLocation_Adapter;
    Button location_addBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        mContext = getApplicationContext();

        LocationList locationList = new LocationList();
        final String Location_List[] = locationList.Location_List;
        ArrayAdapter<String> Location_ListAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line,Location_List);
        Location_autoEdit1 = (AutoCompleteTextView) findViewById(R.id.TalentResister_Location);
        Location_autoEdit1.setAdapter(Location_ListAdapter);


        location_ListView = (ListView) findViewById(R.id.location_ListView);
        location_ArrayList = new ArrayList<>();
        location_ArrayList.add("경기도 파주시 광탄면");
        location_ArrayList.add("서울특별시 마포구 상수동");
        location_ArrayList.add("경기도 일산동구 정발산동");
        talentLocation_Adapter = new Talent_Location_Adapter(mContext, location_ArrayList);
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
                talentLocation_Adapter = new Talent_Location_Adapter(getBaseContext(), location_ArrayList);
                location_ListView.setAdapter(talentLocation_Adapter);
            }
        });


        Intent i = getIntent();
        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");

        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);

        if(HavingDataFlag) {
            Data = (MyTalent)i.getSerializableExtra("Data");
            Loc = Data.getLocationArray();
            TextView LocationKey1 = (TextView) findViewById(R.id.Location_key1);
            SpannableString Location_content1 = new SpannableString(Loc[0]);
            Location_content1.setSpan(new UnderlineSpan(), 0, Location_content1.length(), 0);
            LocationKey1.setText(Location_content1);

            TextView LocationKey2 = (TextView) findViewById(R.id.Location_key2);
            SpannableString Location_content2 = new SpannableString(Loc[1]);
            Location_content2.setSpan(new UnderlineSpan(), 0, Location_content2.length(), 0);
            LocationKey2.setText(Location_content2);

            TextView LocationKey3 = (TextView) findViewById(R.id.Location_key3);
            SpannableString Location_content3 = new SpannableString(Loc[2]);
            Location_content3.setSpan(new UnderlineSpan(), 0, Location_content3.length(), 0);
            LocationKey3.setText(Location_content3);

            Location1 = Loc[0];
            Location2 = Loc[1];
            Location3 = Loc[2];
        }


    }

    public void registLocation1(View v){

        TextView LocationKey1= (TextView) findViewById(R.id.TalentResister_Talent_Location1);
        TextView LocationKey2= (TextView) findViewById(R.id.Location_key1);
        Location1 = LocationKey1.getText().toString();
        SpannableString content1 = new SpannableString(Location1);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        LocationKey2.setText(content1);

    }

    public void registLocation2(View v){

        TextView LocationKey1= (TextView) findViewById(R.id.TalentResister_Talent_Location2);
        TextView LocationKey2= (TextView) findViewById(R.id.Location_key2);
        Location2 = LocationKey1.getText().toString();
        SpannableString content1 = new SpannableString(Location2);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        LocationKey2.setText(content1);

    }

    public void registLocation3(View v){

        TextView LocationKey1= (TextView) findViewById(R.id.TalentResister_Talent_Location3);
        TextView LocationKey2= (TextView) findViewById(R.id.Location_key3);
        Location3 = LocationKey1.getText().toString();
        SpannableString content1 = new SpannableString(Location3);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        LocationKey2.setText(content1);

    }

    public void goNext(View v){
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

}
