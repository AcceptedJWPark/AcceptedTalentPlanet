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
import android.widget.Toast;

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

}
