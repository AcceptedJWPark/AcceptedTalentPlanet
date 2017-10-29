package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_Location extends AppCompatActivity {
    private String Talent1, Talent2, Talent3;
    private String Location1, Location2, Location3;
    private boolean TalentRegister_Flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        Intent i = getIntent();
        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");
        TextView LocationKey1= (TextView) findViewById(R.id.Location_key1);
        SpannableString Location_content1 = new SpannableString("서울시 마포구");
        Location_content1 .setSpan(new UnderlineSpan(), 0, Location_content1 .length(), 0);
        LocationKey1.setText(Location_content1);

        TextView LocationKey2 = (TextView) findViewById(R.id.Location_key2);
        SpannableString Location_content2 = new SpannableString("경기도 일산동구");
        Location_content2.setSpan(new UnderlineSpan(), 0, Location_content2.length(), 0);
        LocationKey2.setText(Location_content2);

        TextView LocationKey3 = (TextView) findViewById(R.id.Location_key3);
        SpannableString Location_content3 = new SpannableString("경기도 파주시");
        Location_content3.setSpan(new UnderlineSpan(), 0, Location_content3.length(), 0);
        LocationKey3.setText(Location_content3);

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
        Intent i = new Intent(this, TalentResisterActivity_Level.class);
        i.putExtra("talentFlag", TalentRegister_Flag);
        i.putExtra("talent1", Talent1);
        i.putExtra("talent2", Talent2);
        i.putExtra("talent3", Talent3);
        i.putExtra("loc1", Location1);
        i.putExtra("loc2", Location2);
        i.putExtra("loc3", Location3);
        startActivity(i);

    }

}
