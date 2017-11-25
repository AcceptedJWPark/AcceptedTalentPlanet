package com.example.accepted.acceptedtalentplanet.TalentSearching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.accepted.acceptedtalentplanet.LocationList.LocationList;
import com.example.accepted.acceptedtalentplanet.R;

import java.util.List;

/**
 * Created by Accepted on 2017-11-24.
 */

public class TalentSearching_Activity extends AppCompatActivity {

    Spinner location_bigcategory_spinner;
    Spinner location_smallcategory_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsearching_searchingbox_activity);

        LocationList locationList = new LocationList();
        LocationGroup talentSearching_locationGroup = new LocationGroup();
        String location_bigCategory[] = talentSearching_locationGroup.location_BigCategory;
        String seoul_smallCategory[] = talentSearching_locationGroup.seoul_SmallCategory;
        String busan_smallCategory[] = talentSearching_locationGroup.busan_SmallCategory;
        String daegu_smallCategory[] = talentSearching_locationGroup.daegu_SmallCategory;
        String incheon_smallCategory[] = talentSearching_locationGroup.incheon_SmallCategory;
        String gwangju_smallCategory[] = talentSearching_locationGroup.gwangju_SmallCategory;
        String daejeon_smallCategory[] = talentSearching_locationGroup.daejeon_SmallCategory;
        String ulsan_smallCategory[] = talentSearching_locationGroup.ulsan_SmallCategory;
        String sejong_smallCategory[] = talentSearching_locationGroup.sejong_SmallCategory;
        String gyunggi_smallCategory[] = talentSearching_locationGroup.gyunggi_SmallCategory;
        String gangwon_smallCategory[] = talentSearching_locationGroup.gangwon_SmallCategory;
        String chungbuk_smallCategory[] = talentSearching_locationGroup.chungbuk_SmallCategory;
        String chungnam_smallCategory[] = talentSearching_locationGroup.chungnam_SmallCategory;
        String jeonbuk_smallCategory[] = talentSearching_locationGroup.jeonbuk_SmallCategory;
        String jeonnam_smallCategory[] = talentSearching_locationGroup.jeonnam_SmallCategory;
        String gyungbuk_smallCategory[] = talentSearching_locationGroup.gyungbuk_SmallCategory;
        String gyungnam_smallCategory[] = talentSearching_locationGroup.gyungnam_SmallCategory;
        String jeju_smallCategory[] = talentSearching_locationGroup.jeju_SmallCategory;



        location_bigcategory_spinner = (Spinner) findViewById(R.id.location_bigcategory_spinner);
        location_smallcategory_spinner = (Spinner) findViewById(R.id.location_smallcategory_spinner);


        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.customerservice_claim_spinnertext, (List<String>) location_bigcategory_spinner);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_bigcategory_spinner.setAdapter(spinner_adapter);




    }

}
