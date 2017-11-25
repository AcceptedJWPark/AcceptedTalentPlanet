package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;

public class TalentResister_Level_Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private String Talent1, Talent2, Talent3;
    private String Location1, Location2, Location3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private int Level;
    CheckBox[] cbs = new CheckBox[5];
    CheckBox cb1, cb2, cb3, cb4, cb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_level);
        Intent i = getIntent();
        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);

        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");

        Location1 = i.getStringExtra("loc1");
        Location2 = i.getStringExtra("loc2");
        Location3 = i.getStringExtra("loc3");

        cb1 = (CheckBox)findViewById(R.id.TalentResister_Talent_Level1);
        cb2 = (CheckBox)findViewById(R.id.TalentResister_Talent_Level2);
        cb3 = (CheckBox)findViewById(R.id.TalentResister_Talent_Level3);
        cb4 = (CheckBox)findViewById(R.id.TalentResister_Talent_Level4);
        cb5 = (CheckBox)findViewById(R.id.TalentResister_Talent_Level5);

        cbs[0] = cb1;
        cbs[1] = cb2;
        cbs[2] = cb3;
        cbs[3] = cb4;
        cbs[4] = cb5;

        if(HavingDataFlag){
            Data = (MyTalent) i.getSerializableExtra("Data");
            Level = Data.getIntegerLevel();
            cbs[Level-1].setChecked(true);
        }



        for(int index = 0; index < 5; index ++)
            cbs[index].setOnCheckedChangeListener(this);

        TextView LevelKey1= (TextView) findViewById(R.id.Level_key1);
        SpannableString Level_content1 = new SpannableString(Talent1);
        Level_content1.setSpan(new UnderlineSpan(), 0, Level_content1 .length(), 0);
        LevelKey1.setText(Level_content1);

        TextView LevelKey2 = (TextView) findViewById(R.id.Level_key2);
        SpannableString Level_content2 = new SpannableString(Talent2);
        Level_content2.setSpan(new UnderlineSpan(), 0, Level_content2.length(), 0);
        LevelKey2.setText(Level_content2);

        TextView LevelKey3 = (TextView) findViewById(R.id.Level_key3);
        SpannableString Level_content3 = new SpannableString(Talent3);
        Level_content3.setSpan(new UnderlineSpan(), 0, Level_content3.length(), 0);
        LevelKey3.setText(Level_content3);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if(buttonView.getId() == R.id.TalentResister_Talent_Level1 && isChecked){
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.TalentResister_Talent_Level2 && isChecked){
            cb1.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.TalentResister_Talent_Level3 && isChecked){
            cb2.setChecked(false);
            cb1.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.TalentResister_Talent_Level4 && isChecked){
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb1.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.TalentResister_Talent_Level5 && isChecked){
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb1.setChecked(false);
        }
    }

    public void goNext(View v){
        Intent i = new Intent(this, TalentResister_Point_Activity.class);
        i.putExtra("talentFlag", TalentRegister_Flag);
        i.putExtra("talent1", Talent1);
        i.putExtra("talent2", Talent2);
        i.putExtra("talent3", Talent3);
        i.putExtra("loc1", Location1);
        i.putExtra("loc2", Location2);
        i.putExtra("loc3", Location3);
        i.putExtra("level", findLevel());
        i.putExtra("HavingDataFlag", HavingDataFlag);
        if(HavingDataFlag){
            i.putExtra("Data", Data);
        }
        startActivity(i);

    }

    public int findLevel(){
        for(int i = 0; i < 5; i++){
            if(cbs[i].isChecked()){
                return i + 1;
            }
        }
        return -1;
    }



}
