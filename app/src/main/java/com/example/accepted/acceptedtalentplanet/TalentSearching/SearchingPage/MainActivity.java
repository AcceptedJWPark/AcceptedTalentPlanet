package com.example.accepted.acceptedtalentplanet.TalentSearching.SearchingPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.accepted.acceptedtalentplanet.LocationData;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.TalentSearching.Keyword_Adapter;

import java.util.ArrayList;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-11-24.
 */

public class MainActivity extends AppCompatActivity {

    Spinner location_bigcategory_spinner;
    Spinner location_smallcategory_spinner;
    Spinner level_start;
    Spinner level_end;
    Button TalentSearching_SaveBtn;
    EditText TalentSearching_KeywordInput;
    EditText TalentSearching_PointInput1;
    EditText TalentSearching_PointInput2;

    ListView TalentSearching_KeywordListView;

    ArrayList<String> TalentSearching_talent_ArrayList;
    Keyword_Adapter TalentSearching_talent_Adapter;
    Button keyword_addBtn;
    LinearLayout TalentSearching_TxtView;
    LinearLayout TalentSearching_LL1;
    LinearLayout TalentSearching_LL2;
    LinearLayout TalentSearching_LL3;
    LinearLayout TalentSearching_LL4;


    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsearching_searchingpage_activity);

        mContext = getApplicationContext();



//        LocationData talentSearching_locationData = new LocationData();
//        final String location_bigCategory[] = talentSearching_locationData.location_BigCategory;
//        final String seoul_smallCategory[] = talentSearching_locationData.seoul_SmallCategory;
//        final String busan_smallCategory[] = talentSearching_locationData.busan_SmallCategory;
//        final String daegu_smallCategory[] = talentSearching_locationData.daegu_SmallCategory;
//        final String incheon_smallCategory[] = talentSearching_locationData.incheon_SmallCategory;
//        final String gwangju_smallCategory[] = talentSearching_locationData.gwangju_SmallCategory;
//        final String daejeon_smallCategory[] = talentSearching_locationData.daejeon_SmallCategory;
//        final String ulsan_smallCategory[] = talentSearching_locationData.ulsan_SmallCategory;
//        final String sejong_smallCategory[] = talentSearching_locationData.sejong_SmallCategory;
//        final String gyunggi_smallCategory[] = talentSearching_locationData.gyunggi_SmallCategory;
//        final String gangwon_smallCategory[] = talentSearching_locationData.gangwon_SmallCategory;
//        final String chungbuk_smallCategory[] = talentSearching_locationData.chungbuk_SmallCategory;
//        final String chungnam_smallCategory[] = talentSearching_locationData.chungnam_SmallCategory;
//        final String jeonbuk_smallCategory[] = talentSearching_locationData.jeonbuk_SmallCategory;
//        final String jeonnam_smallCategory[] = talentSearching_locationData.jeonnam_SmallCategory;
//        final String gyungbuk_smallCategory[] = talentSearching_locationData.gyungbuk_SmallCategory;
//        final String gyungnam_smallCategory[] = talentSearching_locationData.gyungnam_SmallCategory;
//        final String jeju_smallCategory[] = talentSearching_locationData.jeju_SmallCategory;
//
//        final String level_startList[] = {"시작 단계", "초급 이상", "중급 이상", "상급 이상", "전문가 이상"};
//        final String level_end1List[] = {"시작 단계", "초급 이하", "중급 이하", "상급 이하", "전문가 이하"};
//        final String level_end2List[] = {"초급 이하", "중급 이하", "상급 이하", "전문가 이하"};
//        final String level_end3List[] = {"중급 이하", "상급 이하", "전문가 이하"};
//        final String level_end4List[] = {"상급 이하", "전문가 이하"};
//        final String level_end5List[] = {"전문가 이하"};
//
//        level_start = (Spinner) findViewById(R.id.level_start_spinner);
//        level_end = (Spinner) findViewById(R.id.level_end_spinner);
//
//        ArrayAdapter<CharSequence> level_spinner_adapter = new ArrayAdapter<CharSequence>(this, R.layout.talentsearching_location_spinnertext, level_startList);
//        level_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//        level_start.setAdapter(level_spinner_adapter);
//        level_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//
//                if (level_start.getSelectedItem().toString() == "시작 단계") {
//                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end1List);
//                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    level_end.setAdapter(level_end_spinner_adapter);
//                }
//
//                else if (level_start.getSelectedItem().toString() == "초급 이상") {
//                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end2List);
//                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    level_end.setAdapter(level_end_spinner_adapter);
//                }
//                else if (level_start.getSelectedItem().toString() == "중급 이상") {
//                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end3List);
//                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    level_end.setAdapter(level_end_spinner_adapter);
//                }
//
//                else if (level_start.getSelectedItem().toString() == "상급 이상") {
//                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end4List);
//                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    level_end.setAdapter(level_end_spinner_adapter);
//                }
//                else {
//                    ArrayAdapter<CharSequence> level_end_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, level_end5List);
//                    level_end_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    level_end.setAdapter(level_end_spinner_adapter);
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//
//            }
//        });
//
//
//
//
//        TalentSearching_KeywordInput = (EditText) findViewById(R.id.TalentSearching_KeywordInput);
//        TalentSearching_PointInput1 = (EditText) findViewById(R.id.TalentSearching_PointInput1);
//        TalentSearching_PointInput2 = (EditText) findViewById(R.id.TalentSearching_PointInput2);
//
//
//        TalentSearching_KeywordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus)
//                {
//                    hideKeyboard(v, mContext);
//                }
//            }
//        });
//        TalentSearching_PointInput1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus)
//                {
//                    hideKeyboard(v, mContext);
//                }
//            }
//        });
//        TalentSearching_PointInput2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus)
//                {
//                    hideKeyboard(v, mContext);
//                }
//            }
//        });
//
//        TalentSearching_KeywordListView = (ListView) findViewById(R.id.TalentSearching_KeywordListView);
//        TalentSearching_talent_ArrayList = new ArrayList<>();
//        TalentSearching_talent_Adapter = new Keyword_Adapter(mContext, TalentSearching_talent_ArrayList);
//        TalentSearching_KeywordListView.setAdapter(TalentSearching_talent_Adapter);
//
//        keyword_addBtn = (Button) findViewById(R.id.keyword_addBtn);
//        TalentSearching_KeywordInput = (EditText) findViewById(R.id.TalentSearching_KeywordInput);
//        keyword_addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (TalentSearching_KeywordInput.getText().toString().length()==0)
//                {
//                    return;
//                }
//                if(TalentSearching_talent_ArrayList.size()>=3)
//                {
//                    return;
//                }
//                TalentSearching_talent_ArrayList.add(TalentSearching_KeywordInput.getText().toString());
//                TalentSearching_KeywordInput.setText("");
//                TalentSearching_talent_Adapter = new Keyword_Adapter(getBaseContext(), TalentSearching_talent_ArrayList);
//                TalentSearching_KeywordListView.setAdapter(TalentSearching_talent_Adapter);
//                TalentSearching_talent_Adapter.notifyDataSetChanged();
//            }
//        });
//
//        location_bigcategory_spinner = (Spinner) findViewById(R.id.location_bigcategory_spinner);
//        location_smallcategory_spinner = (Spinner) findViewById(R.id.location_smallcategory_spinner);
//
//        ArrayAdapter<CharSequence> big_spinner_adapter = new ArrayAdapter<CharSequence>(this, R.layout.talentsearching_location_spinnertext, location_bigCategory);
//        big_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//        location_bigcategory_spinner.setAdapter(big_spinner_adapter);
//        location_bigcategory_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (location_bigcategory_spinner.getSelectedItem().toString() == "전체") {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, sejong_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "서울특별시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, seoul_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "부산광역시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, busan_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "대구광역시")
//                {
//                    location_smallcategory_spinner.setVisibility(View.VISIBLE);
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, daegu_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "인천광역시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, incheon_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "광주광역시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gwangju_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "대전광역시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, daejeon_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "울산광역시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, ulsan_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "세종특별자치시")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, sejong_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "경기도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gyunggi_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "강원도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gangwon_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "충청북도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, chungbuk_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "충청남도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, chungnam_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "전라북도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, jeonbuk_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "전라남도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, jeonnam_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "경상북도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gyungbuk_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else if (location_bigcategory_spinner.getSelectedItem().toString() == "경상남도")
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, gyungnam_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//                else
//                {
//                    ArrayAdapter<CharSequence> small_spinner_adapter = new ArrayAdapter<CharSequence>(getBaseContext(), R.layout.talentsearching_location_spinnertext, jeju_smallCategory);
//                    small_spinner_adapter.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
//                    location_smallcategory_spinner.setAdapter(small_spinner_adapter);
//
//                }
//
//
//            }
//
//            @Override
//            public void onNothingSelected (AdapterView < ? > parent){
//
//            }
//
//        });
//
//        TalentSearching_SaveBtn = (Button)findViewById(R.id.TalentSearching_SaveBtn);
//        TalentSearching_SaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location1 = location_bigcategory_spinner.getSelectedItem().toString();
//                String location2 = location_smallcategory_spinner.getSelectedItem().toString();
//                String keyword = TalentSearching_KeywordInput.getText().toString();
//                String beginPoint = TalentSearching_PointInput1.getText().toString();
//                String endPoint = TalentSearching_PointInput2.getText().toString();
//
//                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSearching.MainActivity.class);
//                i.putExtra("keyword", (keyword == null)?"" : keyword);
//                i.putExtra("location1", (location1.equals("전체"))? "":location1);
//                i.putExtra("location2", (location2.equals("전체"))? "":location2);
//                i.putExtra("beginLevel", level_start.getSelectedItemPosition() + 1);
//                i.putExtra("endLevel", level_end.getSelectedItemPosition() + 1);
//                i.putExtra("beginPoint", (beginPoint.equals("")?"0":beginPoint));
//                i.putExtra("endPoint", (endPoint.equals("")?"0":endPoint));
//                startActivity(i);
//            }
//        });
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        windowManager.getDefaultDisplay().getMetrics(metrics);
//
//        TalentSearching_TxtView = (LinearLayout) findViewById(R.id.TalentSearching_TxtView);
//        TalentSearching_LL1 = (LinearLayout) findViewById(R.id.TalentSearching_LL1);
//        TalentSearching_LL2 = (LinearLayout) findViewById(R.id.TalentSearching_LL2);
//        TalentSearching_LL3 = (LinearLayout) findViewById(R.id.TalentSearching_LL3);
//        TalentSearching_LL4 = (LinearLayout) findViewById(R.id.TalentSearching_LL4);
//
//        int TalentSearching_Txt_height = (int) (metrics.heightPixels*0.083);
//        int TalentSearching_LL_height = (int) (metrics.heightPixels*0.045);
//        int TalentSearching_Btn_height = (int) (metrics.heightPixels*0.042);
//
//        ViewGroup.LayoutParams params1 = TalentSearching_TxtView.getLayoutParams();
//        ViewGroup.LayoutParams params2 = TalentSearching_LL1.getLayoutParams();
//        ViewGroup.LayoutParams params3 = TalentSearching_LL2.getLayoutParams();
//        ViewGroup.LayoutParams params4 = TalentSearching_LL3.getLayoutParams();
//        ViewGroup.LayoutParams params5 = TalentSearching_LL4.getLayoutParams();
//        ViewGroup.LayoutParams params7 = TalentSearching_SaveBtn.getLayoutParams();
//
//        params1.height = TalentSearching_Txt_height;
//        params2.height = TalentSearching_LL_height;
//        params3.height = TalentSearching_LL_height;
//        params4.height = TalentSearching_LL_height;
//        params5.height = TalentSearching_LL_height;
//        params7.height = TalentSearching_Btn_height;
//
//        TalentSearching_TxtView.setLayoutParams(params1);
//        TalentSearching_LL1.setLayoutParams(params2);
//        TalentSearching_LL2.setLayoutParams(params3);
//        TalentSearching_LL3.setLayoutParams(params4);
//        TalentSearching_LL4.setLayoutParams(params5);
//        TalentSearching_SaveBtn.setLayoutParams(params7);
//
//


    }

}
