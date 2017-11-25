package com.example.accepted.acceptedtalentplanet.TalentSearching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-24.
 */

public class TalentSearching_SearchingPage_Activity extends AppCompatActivity {

    Spinner location_bigcategory_spinner;
    Spinner location_smallcategory_spinner;
    Spinner level_start;
    Spinner level_end;
    Button TalentSearching_SaveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsearching_searchingpage_activity);

        LocationGroup talentSearching_locationGroup = new LocationGroup();
        String location_bigCategory[] = talentSearching_locationGroup.location_BigCategory;
        final String seoul_smallCategory[] = talentSearching_locationGroup.seoul_SmallCategory;
        final String busan_smallCategory[] = talentSearching_locationGroup.busan_SmallCategory;
        final String daegu_smallCategory[] = talentSearching_locationGroup.daegu_SmallCategory;
        final String incheon_smallCategory[] = talentSearching_locationGroup.incheon_SmallCategory;
        final String gwangju_smallCategory[] = talentSearching_locationGroup.gwangju_SmallCategory;
        final String daejeon_smallCategory[] = talentSearching_locationGroup.daejeon_SmallCategory;
        final String ulsan_smallCategory[] = talentSearching_locationGroup.ulsan_SmallCategory;
        final String sejong_smallCategory[] = talentSearching_locationGroup.sejong_SmallCategory;
        final String gyunggi_smallCategory[] = talentSearching_locationGroup.gyunggi_SmallCategory;
        final String gangwon_smallCategory[] = talentSearching_locationGroup.gangwon_SmallCategory;
        final String chungbuk_smallCategory[] = talentSearching_locationGroup.chungbuk_SmallCategory;
        final String chungnam_smallCategory[] = talentSearching_locationGroup.chungnam_SmallCategory;
        final String jeonbuk_smallCategory[] = talentSearching_locationGroup.jeonbuk_SmallCategory;
        final String jeonnam_smallCategory[] = talentSearching_locationGroup.jeonnam_SmallCategory;
        final String gyungbuk_smallCategory[] = talentSearching_locationGroup.gyungbuk_SmallCategory;
        final String gyungnam_smallCategory[] = talentSearching_locationGroup.gyungnam_SmallCategory;
        final String jeju_smallCategory[] = talentSearching_locationGroup.jeju_SmallCategory;

        final String level_startList[] = {"시작단계", "초급 수준", "중급 수준", "상급 수준", "전문가 수준"};
        final String level_end1List[] = {"시작단계", "초급 수준", "중급 수준", "상급 수준", "전문가 수준"};
        final String level_end2List[] = {"초급 수준", "중급 수준", "상급 수준", "전문가 수준"};
        final String level_end3List[] = {"중급 수준", "상급 수준", "전문가 수준"};
        final String level_end4List[] = {"상급 수준", "전문가 수준"};
        final String level_end5List[] = {"전문가 수준"};

        level_start = (Spinner) findViewById(R.id.level_start_spinner);
        level_end = (Spinner) findViewById(R.id.level_end_spinner);

        ArrayAdapter<CharSequence> level_spinner_adapter = new ArrayAdapter<CharSequence>(this, R.layout.talentsearching_location_spinnertext, level_startList);
        level_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
        level_start.setAdapter(level_spinner_adapter);
        level_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (level_start.getSelectedItem().toString() == "시작단계") {
                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end1List);
                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    level_end.setAdapter(level_end_spinner_adapter);
                }

                else if (level_start.getSelectedItem().toString() == "초급 수준") {
                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end2List);
                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    level_end.setAdapter(level_end_spinner_adapter);
                }
                else if (level_start.getSelectedItem().toString() == "중급 수준") {
                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end3List);
                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    level_end.setAdapter(level_end_spinner_adapter);
                }

                else if (level_start.getSelectedItem().toString() == "상급 수준") {
                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end4List);
                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    level_end.setAdapter(level_end_spinner_adapter);
                }
                else {
                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end5List);
                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    level_end.setAdapter(level_end_spinner_adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        location_bigcategory_spinner = (Spinner) findViewById(R.id.location_bigcategory_spinner);
        location_smallcategory_spinner = (Spinner) findViewById(R.id.location_smallcategory_spinner);

        ArrayAdapter<CharSequence> big_spinner_adapter = new ArrayAdapter<CharSequence>(this, R.layout.talentsearching_location_spinnertext, location_bigCategory);
        big_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
        location_bigcategory_spinner.setAdapter(big_spinner_adapter);
        location_bigcategory_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (location_bigcategory_spinner.getSelectedItem().toString() == "전체") {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, sejong_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "서울특별시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, seoul_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "부산광역시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, busan_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "대구광역시")
                {
                    location_smallcategory_spinner.setVisibility(View.VISIBLE);
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, daegu_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "인천광역시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, incheon_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "광주광역시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gwangju_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "대전광역시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, daejeon_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "울산광역시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, ulsan_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);


                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "세종특별자치시")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, sejong_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "경기도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gyunggi_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "강원도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gangwon_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "충청북도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, chungbuk_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "충청남도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, chungnam_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }
                else if (location_bigcategory_spinner.getSelectedItem().toString() == "전라북도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, jeonbuk_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "전라남도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, jeonnam_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "경상북도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gyungbuk_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else if (location_bigcategory_spinner.getSelectedItem().toString() == "경상남도")
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gyungnam_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }

                else
                {
                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, jeju_smallCategory);
                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);

                }


            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }

        });

        TalentSearching_SaveBtn = (Button)findViewById(R.id.TalentSearching_SaveBtn);
        TalentSearching_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}
