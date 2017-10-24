package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;
import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_loacation);
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

}
