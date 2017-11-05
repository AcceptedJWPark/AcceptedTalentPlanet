package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_Talent extends AppCompatActivity {
    private String Talent1, Talent2, Talent3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private String[] TalentArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_talent);
        Intent i = getIntent();

        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);

        if(HavingDataFlag){
            Data = (MyTalent)i.getSerializableExtra("Data");
            TalentArr = Data.getKeywordArray();
            TextView TalentKey1= (TextView) findViewById(R.id.Talent_key1);
            SpannableString content1 = new SpannableString(TalentArr[0]);
            content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
            TalentKey1.setText(content1);

            TextView TalentKey2 = (TextView) findViewById(R.id.Talent_key2);
            SpannableString content2 = new SpannableString(TalentArr[1]);
            content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
            TalentKey2.setText(content2);

            TextView TalentKey3 = (TextView) findViewById(R.id.Talent_key3);
            SpannableString content3 = new SpannableString(TalentArr[2]);
            content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
            TalentKey3.setText(content3);

            Talent1 = TalentArr[0];
            Talent2 = TalentArr[1];
            Talent3 = TalentArr[2];
        }



    }
    public void registTalent1(View v){

        TextView TalentKey1= (TextView) findViewById(R.id.TalentResister_Talent_Keyword1);
        TextView TalentKey2= (TextView) findViewById(R.id.Talent_key1);
        Talent1 = TalentKey1.getText().toString();
        SpannableString content1 = new SpannableString(Talent1);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        TalentKey2.setText(content1);

    }

    public void registTalent2(View v){

        TextView TalentKey1= (TextView) findViewById(R.id.TalentResister_Talent_Keyword2);
        TextView TalentKey2= (TextView) findViewById(R.id.Talent_key2);
        Talent2 = TalentKey1.getText().toString();
        SpannableString content1 = new SpannableString(Talent2);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        TalentKey2.setText(content1);

    }

    public void registTalent3(View v){

        TextView TalentKey1= (TextView) findViewById(R.id.TalentResister_Talent_Keyword3);
        TextView TalentKey2= (TextView) findViewById(R.id.Talent_key3);
        Talent3 = TalentKey1.getText().toString();
        SpannableString content1 = new SpannableString(Talent3);
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        TalentKey2.setText(content1);

    }

    public void goNext(View v){
        Intent i = new Intent(this, TalentResisterActivity_Location.class);
        i.putExtra("talentFlag", TalentRegister_Flag);
        i.putExtra("HavingDataFlag", HavingDataFlag);

        i.putExtra("talent1", Talent1);
        i.putExtra("talent2", Talent2);
        i.putExtra("talent3", Talent3);
        if(HavingDataFlag) {
            i.putExtra("Data", Data);
        }
        startActivity(i);

    }

}
