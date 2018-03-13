package com.example.accepted.acceptedtalentplanet.TalentResister.Location;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String keyword1, keyword2, keyword3;
    private String[] locationList;
    private String[] preLocationList;
    private boolean isRegisted;
    private boolean isHavingData;
    private MyTalent data;
    private GeoPoint[] geoPoint;

    private Context mContext;
    private ArrayList<String> arrayList;

    private LinearLayout ll_TxtContainer;
    private Button btn_Next;


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        mContext = getApplicationContext();



        ll_TxtContainer = (LinearLayout) findViewById(R.id.ll_TxtContainer_TalentResister);
        btn_Next = (Button) findViewById(R.id.btn_Next_TalentRegister);


        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_Location_Txt_LL_height = metrics.heightPixels/16;
        int TalentResister_Location_Btn_height = metrics.heightPixels/24;

        ViewGroup.LayoutParams params2 = ll_TxtContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = btn_Next.getLayoutParams();

        params2.height = TalentResister_Location_Txt_LL_height;
        params3.height = TalentResister_Location_Btn_height;

        ll_TxtContainer.setLayoutParams(params2);
        btn_Next.setLayoutParams(params3);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.frg_Map_TalentRegister);
        mapFragment.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.frg_AutoComplete_TalentRegister);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d("Place : ", String.valueOf(place.getName()));
                Location location = new Location("");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);

                setCurrentLocation(location, place.getName().toString(), place.getAddress().toString());
            }

            @Override
            public void onError(Status status) {
                Log.d("Error : ", String.valueOf(status));
            }
        });



        arrayList = new ArrayList<>();

        Intent i = getIntent();
        keyword1 = i.getStringExtra("talent1");
        keyword2 = i.getStringExtra("talent2");
        keyword3 = i.getStringExtra("talent3");


        isRegisted = i.getBooleanExtra("talentFlag", true);
        isHavingData = i.getBooleanExtra("isHavingData", false);

        if(isHavingData) {
            data = (MyTalent)i.getSerializableExtra("data");
            preLocationList = data.getLocationArray();

            for(int index = 0; index < preLocationList.length; index++) {
                if(!preLocationList[index].isEmpty())
                    arrayList.add(preLocationList[index]);
            }

        }








    }

    @Override
    public void onMapReady(final GoogleMap map){

//        if(isHavingData){
//            GeoPoint[] geoPoints = data.getArrGeoPoint();
//
//            if(geoPoints[0] != null && geoPoints[0].getLng() != 0 && preLocationList[0] != null){
//                Location location = new Location("");
//                location.setLongitude(geoPoints[0].getLng());
//                location.setLatitude(geoPoints[0].getLat());
//                setCurrentLocation(location, preLocationList[0], "선택위치");
//            }
//        }else{
            LatLng SEOUL = new LatLng(37.56, 126.97);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(SEOUL);
            markerOptions.title("서울");
            markerOptions.snippet("한국의 수도");
            map.addMarker(markerOptions);

            map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
            map.animateCamera(CameraUpdateFactory.zoomTo(10));
            gMap = map;
      //  }
    }

    private void setCurrentLocation(Location location, String name, String addr){
        gMap.clear();

        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title(name);
        markerOptions.snippet(addr);
        gMap.addMarker(markerOptions);

        gMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }



    //TODO: 장소 3개 입력이 안되면 오류 발생, 1개 이상 입력되면 넘어가야함
    public void goNext(View v){

        if(arrayList.size() < 1){
            Toast.makeText(getApplicationContext(), "장소 1개 이상 필수 입력입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        locationList = new String[]{"", "", ""};

        for(int index = 0; index < arrayList.size(); index++){
            locationList[index] = arrayList.get(index);
        }

        Intent i = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentResister.Level.MainActivity.class);
        i.putExtra("talentFlag", isRegisted);
        i.putExtra("talent1", keyword1);
        i.putExtra("talent2", keyword2);
        i.putExtra("talent3", keyword3);
        i.putExtra("loc1", locationList[0]);
        i.putExtra("loc2", locationList[1]);
        i.putExtra("loc3", locationList[2]);
        i.putExtra("isHavingData", isHavingData);
//        GetLocationGeoPointTask asyncTask = new GetLocationGeoPointTask();
//        asyncTask.execute();

        geoPoint = new GeoPoint[]{new GeoPoint(0,0), new GeoPoint(0,0), new GeoPoint(0,0)};
        for(int index = 0; index < arrayList.size(); index++){
            geoPoint[index] = findGeoPoint(locationList[index]);
        }
        SaveSharedPreference.setGeoPointArr(mContext, geoPoint);

        if(isHavingData){
            i.putExtra("data", data);
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
                geoPoint = new GeoPoint[]{new GeoPoint(0,0), new GeoPoint(0,0), new GeoPoint(0,0)};
                for(int index = 0; index < arrayList.size(); index++){
                    geoPoint[index] = findGeoPoint(locationList[index]);
                }
                SaveSharedPreference.setGeoPointArr(mContext, geoPoint);
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
