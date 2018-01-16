package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.app.Activity;
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
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class TalentResister_Location_Activity extends AppCompatActivity {
    private String Talent1, Talent2, Talent3;
    private String[] Locations;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private String[] Loc;
    private GeoPoint[] arrGp;
    AutoCompleteTextView Location_autoEdit1;

    Context mContext;
    ListView location_ListView;
    ArrayList<String> location_ArrayList;
    TalentResister_Location_Adapter talentLocation_Adapter;
    Button location_addBtn;

    LinearLayout TalentResister_LocationLL;
    LinearLayout TalentResister_Location_Txt_LL;
    Button TalentResister_Location_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        mContext = getApplicationContext();

        TalentResister_LocationList talentResisterLocationList = new TalentResister_LocationList();
        final String Location_List[] = talentResisterLocationList.Location_List;
        ArrayAdapter<String> Location_ListAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.talentresister_location_spinnertext,Location_List);
        Location_autoEdit1 = (AutoCompleteTextView) findViewById(R.id.TalentResister_Location);
        Location_autoEdit1.setAdapter(Location_ListAdapter);
        Location_autoEdit1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }

            }
        });


        TalentResister_LocationLL = (LinearLayout) findViewById(R.id.TalentResister_LocationLL);
        TalentResister_Location_Txt_LL = (LinearLayout) findViewById(R.id.TalentResister_Location_Txt_LL);
        TalentResister_Location_Btn = (Button) findViewById(R.id.TalentResister_Location_Btn);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_LocationLL_height = metrics.heightPixels/14;
        int TalentResister_Location_Txt_LL_height = metrics.heightPixels/10;
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

        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);

        if(HavingDataFlag) {
            Data = (MyTalent)i.getSerializableExtra("Data");
            Loc = Data.getLocationArray();

            for(int index = 0; index < Loc.length; index++) {
                if(!Loc[index].isEmpty())
                    location_ArrayList.add(Loc[index]);
            }

        }

        talentLocation_Adapter = new TalentResister_Location_Adapter(mContext, location_ArrayList);
        location_ListView.setAdapter(talentLocation_Adapter);
        location_addBtn = (Button) findViewById(R.id.addLoctionBtn);
        location_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Edittxt = Location_autoEdit1.getText().toString();
                talentLocation_Adapter = new TalentResister_Location_Adapter(getBaseContext(), location_ArrayList);
                TalentResister_LocationList locationlist = new TalentResister_LocationList();
                int count = 0;
                for(int a=0; a<locationlist.Location_List.length; a++)
                {
                    if (Location_autoEdit1.getText().toString().equals(locationlist.Location_List[a]))
                    {
                        count++;
                    }
                }
                 if (Edittxt.length()==0 || location_ArrayList.size()>=3)
                                {
                                    return;
                                }

                else if (count == 0)
                {
                    Toast.makeText(mContext,"주소가 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }


                else if (location_ArrayList.size()==0)
                {
                    location_ArrayList.add(Edittxt);
                    Location_autoEdit1.setText("");
                    location_ListView.setAdapter(talentLocation_Adapter);
                }


                else if (location_ArrayList.size()==1)
                {
                    if (location_ArrayList.get(0).equals(Edittxt))
                    {
                        Toast.makeText(mContext, "주소가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        location_ArrayList.add(Edittxt);
                        Location_autoEdit1.setText("");
                        location_ListView.setAdapter(talentLocation_Adapter);
                    }
                }
                else if (location_ArrayList.size()==2)
                {
                    if (location_ArrayList.get(1).equals(Edittxt)||location_ArrayList.get(0).equals(Edittxt))
                    {
                        Toast.makeText(mContext, "주소가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                        {
                        location_ArrayList.add(Edittxt);
                        Location_autoEdit1.setText("");
                        location_ListView.setAdapter(talentLocation_Adapter);
                    }
                }
            }
        });
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
