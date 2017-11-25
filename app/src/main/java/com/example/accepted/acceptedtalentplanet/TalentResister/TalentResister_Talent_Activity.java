package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

public class TalentResister_Talent_Activity extends AppCompatActivity {
    private String Talent1, Talent2, Talent3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private String[] TalentArr;

    EditText Talent_autoEdit;

    Context mContext;
    ListView talent_ListView;
    ArrayList<String> talent_ArrayList;
    Talent_Location_Adapter talentLocation_Adapter;
    Button talent_addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_talent);

        mContext = getApplicationContext();

        talent_ListView = (ListView) findViewById(R.id.talent_ListView);
        talent_ArrayList = new ArrayList<>();


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

            for(int index = 0; index < TalentArr.length; index++)
                talent_ArrayList.add(TalentArr[index]);
        }


        talentLocation_Adapter = new Talent_Location_Adapter(mContext, talent_ArrayList);
        talent_ListView.setAdapter(talentLocation_Adapter);

        talent_addBtn = (Button) findViewById(R.id.addTalentBtn);
        Talent_autoEdit = (EditText) findViewById(R.id.TalentResister_Talent);

        talent_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Talent_autoEdit.getText().toString().length()==0)
                {
                    return;
                }
                if(talent_ArrayList.size()>=3)
                {
                    return;
                }
                talent_ArrayList.add(Talent_autoEdit.getText().toString());
                Talent_autoEdit.setText("");
                talentLocation_Adapter = new Talent_Location_Adapter(getBaseContext(), talent_ArrayList);
                talent_ListView.setAdapter(talentLocation_Adapter);
            }
        });


    }


    public void goNext(View v){

        if(talent_ArrayList.size() < 3){
            Toast.makeText(getApplicationContext(), "재능은 3개를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Talent1 = talent_ArrayList.get(0);
        Talent2 = talent_ArrayList.get(1);
        Talent3 = talent_ArrayList.get(2);
        Intent i = new Intent(this, TalentResister_Location_Activity.class);
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
