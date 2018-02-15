package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class TalentResister_Location_Activity extends AppCompatActivity implements OnMapReadyCallback {
    private String Talent1, Talent2, Talent3;
    private String[] Locations;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private String[] Loc;
    private GeoPoint[] arrGp;

    Context mContext;
    ListView location_ListView;
    ArrayList<String> location_ArrayList;
    TalentResister_Location_Adapter talentLocation_Adapter;
    Button location_addBtn;

    LinearLayout TalentResister_Location_Txt_LL;
    LinearLayout TalentResister_LocationLL;
    Button TalentResister_Location_Btn;

    String big_Area;
    String mid_Area;
    String sum_Location;

    int resourceId_mid;
    int resourceId_small;
    String [] items_mid;
    final static String [] ITEM_MID = {"구/군/시"} ;

    Spinner TalentResister_Location_Spinner1;
    Spinner TalentResister_Location_Spinner2;
    Spinner TalentResister_Location_Spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        mContext = getApplicationContext();



        TalentResister_LocationLL = (LinearLayout) findViewById(R.id.TalentResister_LocationLL);
        TalentResister_Location_Txt_LL = (LinearLayout) findViewById(R.id.TalentResister_Location_Txt_LL);
        TalentResister_Location_Btn = (Button) findViewById(R.id.TalentResister_Location_Btn);

        TalentResister_Location_Spinner1 = (Spinner) findViewById(R.id.TalentResister_Location_Spinner1);
        TalentResister_Location_Spinner2 = (Spinner) findViewById(R.id.TalentResister_Location_Spinner2);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_LocationLL_height = metrics.heightPixels/20;
        int TalentResister_Location_Txt_LL_height = metrics.heightPixels/16;
        int TalentResister_Location_Btn_height = metrics.heightPixels/24;

        ViewGroup.LayoutParams params1 = TalentResister_LocationLL.getLayoutParams();
        ViewGroup.LayoutParams params2 = TalentResister_Location_Txt_LL.getLayoutParams();
        ViewGroup.LayoutParams params3 = TalentResister_Location_Btn.getLayoutParams();

        params1.height = TalentResister_LocationLL_height;
        params2.height = TalentResister_Location_Txt_LL_height;
        params3.height = TalentResister_Location_Btn_height;

        TalentResister_LocationLL.setLayoutParams(params1);
        TalentResister_Location_Txt_LL.setLayoutParams(params2);
        TalentResister_Location_Btn.setLayoutParams(params3);



        location_ListView = (ListView) findViewById(R.id.location_ListView);
        location_ArrayList = new ArrayList<>();




        Intent i = getIntent();
        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");

        if(HavingDataFlag) {
            Data = (MyTalent)i.getSerializableExtra("Data");
            Loc = Data.getLocationArray();

            for(int index = 0; index < Loc.length; index++) {
                if(!Loc[index].isEmpty())
                    location_ArrayList.add(Loc[index]);
            }

        }
        System.out.println(location_ArrayList.size());

        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);



        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.시도, R.layout.talentsearching_location_spinnertext);
        TalentResister_Location_Spinner1.setAdapter(adapter);


        TalentResister_Location_Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                big_Area = TalentResister_Location_Spinner1.getSelectedItem().toString();

                if (position == 0) {
                    ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(mContext, R.layout.talentsearching_location_spinnertext, ITEM_MID);
                    adapter2.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    TalentResister_Location_Spinner2.setAdapter(adapter2);
                } else {

                    try {
                        resourceId_mid = R.array.class.getField(big_Area).getInt(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }

                    items_mid = getResources().getStringArray(resourceId_mid);

                    ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(mContext, R.layout.talentsearching_location_spinnertext, items_mid);
                    adapter2.setDropDownViewResource(R.layout.talentsearching_location_spinnertext);
                    TalentResister_Location_Spinner2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        talentLocation_Adapter = new TalentResister_Location_Adapter(mContext, location_ArrayList);
        location_addBtn = (Button) findViewById(R.id.addLoctionBtn);
        location_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_Location = big_Area + " " + TalentResister_Location_Spinner2.getSelectedItem();
                if(TalentResister_Location_Spinner1.getSelectedItemPosition() == 0 || TalentResister_Location_Spinner2.getSelectedItemPosition() == 0)
                {
                    Toast.makeText(mContext,"주소는 구/군/시 단위까지 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    location_ArrayList.add(sum_Location);
                    location_ListView.setAdapter(talentLocation_Adapter);
                    TalentResister_Location_Spinner1.setSelection(0);
                    TalentResister_Location_Spinner2.setSelection(0);
                }
            }
        });

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap map){
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }



    //TODO: 장소 3개 입력이 안되면 오류 발생, 1개 이상 입력되면 넘어가야함
    public void goNext(View v){

        if(location_ArrayList.size() < 1){
            Toast.makeText(getApplicationContext(), "장소 1개 이상 필수 입력입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Locations = new String[]{"", "", ""};

        for(int index = 0; index < location_ArrayList.size(); index++){
            Locations[index] = location_ArrayList.get(index);
        }

        Intent i = new Intent(this, TalentResister_Level_Activity.class);
        i.putExtra("talentFlag", TalentRegister_Flag);
        i.putExtra("talent1", Talent1);
        i.putExtra("talent2", Talent2);
        i.putExtra("talent3", Talent3);
        i.putExtra("loc1", Locations[0]);
        i.putExtra("loc2", Locations[1]);
        i.putExtra("loc3", Locations[2]);
        i.putExtra("HavingDataFlag", HavingDataFlag);
//        GetLocationGeoPointTask asyncTask = new GetLocationGeoPointTask();
//        asyncTask.execute();

        arrGp = new GeoPoint[]{new GeoPoint(0,0), new GeoPoint(0,0), new GeoPoint(0,0)};
        for(int index = 0; index < location_ArrayList.size(); index++){
            arrGp[index] = findGeoPoint(Locations[index]);
        }
        SaveSharedPreference.setGeoPointArr(mContext, arrGp);

        if(HavingDataFlag){
            i.putExtra("Data", Data);
        }
        startActivity(i);

    }



    private class GetLocationGeoPointTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(Void... voids){
            try{
                arrGp = new GeoPoint[]{new GeoPoint(0,0), new GeoPoint(0,0), new GeoPoint(0,0)};
                for(int index = 0; index < location_ArrayList.size(); index++){
                    arrGp[index] = findGeoPoint(Locations[index]);
                }
                SaveSharedPreference.setGeoPointArr(mContext, arrGp);
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void...values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void s){
            super.onPostExecute(s);
        }



    }

    private GeoPoint findGeoPoint(String address) {
        Geocoder geocoder = new Geocoder(this);
        Address addr;
        GeoPoint location = null;
        try {
            List<Address> listAddress = geocoder.getFromLocationName(address, 1);
            if (listAddress.size() > 0) { // 주소값이 존재 하면
                addr = listAddress.get(0); // Address형태로
                double lat = addr.getLatitude();
                double lng = addr.getLongitude();
                location = new GeoPoint(lat, lng);

                Log.d("Location Log", address + " : " + lat + ", " +lng);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }


}
