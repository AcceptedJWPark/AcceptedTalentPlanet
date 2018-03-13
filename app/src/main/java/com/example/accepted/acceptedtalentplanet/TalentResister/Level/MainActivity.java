package com.example.accepted.acceptedtalentplanet.TalentResister.Level;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private String Talent1, Talent2, Talent3;
    private String location;
    private boolean isTalentRegister;
    private boolean isHavingData;
    private MyTalent data;
    private int level;
    private CheckBox[] cbs = new CheckBox[5];
    private CheckBox cb1, cb2, cb3, cb4, cb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_level);
        Intent i = getIntent();
        isTalentRegister = i.getBooleanExtra("talentFlag", true);
        isHavingData = i.getBooleanExtra("isHavingData", false);

        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");

        location = i.getStringExtra("loc1");

        cb1 = (CheckBox)findViewById(R.id.cb1_TalentRegister);
        cb2 = (CheckBox)findViewById(R.id.cb2_TalentRegister);
        cb3 = (CheckBox)findViewById(R.id.cb3_TalentRegister);
        cb4 = (CheckBox)findViewById(R.id.cb4_TalentRegister);
        cb5 = (CheckBox)findViewById(R.id.cb5_TalentRegister);

        cbs[0] = cb1;
        cbs[1] = cb2;
        cbs[2] = cb3;
        cbs[3] = cb4;
        cbs[4] = cb5;

        if(isHavingData){
            data = (MyTalent) i.getSerializableExtra("data");
            level = data.getIntegerLevel();
            cbs[level -1].setChecked(true);
        }



        for(int index = 0; index < 5; index ++)
            cbs[index].setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if(buttonView.getId() == R.id.cb1_TalentRegister && isChecked){
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.cb2_TalentRegister && isChecked){
            cb1.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.cb3_TalentRegister && isChecked){
            cb2.setChecked(false);
            cb1.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.cb4_TalentRegister && isChecked){
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb1.setChecked(false);
            cb5.setChecked(false);
        }
        if(buttonView.getId() == R.id.cb5_TalentRegister && isChecked){
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb1.setChecked(false);
        }
    }

    public void goNext(View v) {

        if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked() && !cb5.isChecked()) {
            Toast.makeText(getApplicationContext(), "본인의 수준을 선택해주세요", Toast.LENGTH_SHORT).show();

        } else {
            Intent i = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentResister.Point.MainActivity.class);
            i.putExtra("talentFlag", isTalentRegister);
            i.putExtra("talent1", Talent1);
            i.putExtra("talent2", Talent2);
            i.putExtra("talent3", Talent3);
            i.putExtra("loc1", location);
            i.putExtra("level", findLevel());
            i.putExtra("isHavingData", isHavingData);
            if (isHavingData) {
                i.putExtra("data", data);
            }
            startActivity(i);

        }
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
