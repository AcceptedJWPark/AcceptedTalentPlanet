package com.example.accepted.acceptedtalentplanet.TalentResister.Location;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.PermissionUtil;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
                                                                    GoogleApiClient.ConnectionCallbacks,
                                                                    GoogleApiClient.OnConnectionFailedListener,
                                                                    com.google.android.gms.location.LocationListener,
                                                                    GoogleMap.OnMarkerClickListener {
    private String keyword1, keyword2, keyword3;
    private String location;
    private boolean isRegisted;
    private boolean isHavingData;
    private MyTalent data;

    private Context mContext;

    private LinearLayout ll_TxtContainer;
    private Button btn_Next;


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    private GoogleMap gMap;

    private Location mCurrentLocation;
    private boolean mLocationPermissionGranted = false;
    private LocationRequest mLocationRequest;

    private GoogleApiClient mGoogleApiClient = null;

    private Activity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_location);

        mContext = getApplicationContext();
        mActivity = this;

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

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

        createLocationRequest();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.frg_AutoComplete_TalentRegister);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d("Place : ", String.valueOf(place.getName()));
                Location location = new Location("");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);

                mCurrentLocation = location;

                setCurrentLocation(location, place.getName().toString(), place.getAddress().toString());
            }

            @Override
            public void onError(Status status) {
                Log.d("Error : ", String.valueOf(status));
            }
        });


        Intent i = getIntent();
        keyword1 = i.getStringExtra("talent1");
        keyword2 = i.getStringExtra("talent2");
        keyword3 = i.getStringExtra("talent3");


        isRegisted = i.getBooleanExtra("talentFlag", true);
        isHavingData = i.getBooleanExtra("isHavingData", false);

        if(isHavingData) {
            data = (MyTalent)i.getSerializableExtra("data");
            location = data.getLocation();

        }

    }

    @Override
    public void onLocationChanged(Location location){
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

        Log.d("location changed", currentPosition.toString());

        mCurrentLocation = location;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> list = null;
        try{
            list = geocoder.getFromLocation(currentPosition.latitude, currentPosition.longitude, 1);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(list == null){
            Log.d("주소찾기", "실패");
        }else if(list.size() > 0){
            Address addr = list.get(0);

            Log.d("MyLocation", "location: " + mCurrentLocation.getLatitude() + ", " + mCurrentLocation.getLongitude() + ", " + addr.getAddressLine(0) + "," + addr.toString());
            setCurrentLocation(mCurrentLocation,"현재위치" , addr.getAddressLine(0));

        }



    }

    @Override
    protected void onStart(){
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected() == false){
            mGoogleApiClient.connect();
        }

        super.onStart();
    }

    @Override
    protected void onStop(){
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }

        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2002);
            }

            mLocationPermissionGranted = true;
        }
        if(!isHavingData)
            getCurrentLocation();
    }

    @Override
    public  void onConnectionFailed(ConnectionResult connectionResult){
        Log.d("failed connection", "failed");
    }

    @Override
    public void onConnectionSuspended(int cause){
        if(cause == CAUSE_NETWORK_LOST)
            Log.e("suspended", "connection lost");
        else if (cause == CAUSE_SERVICE_DISCONNECTED)
            Log.e("suspended", "service disconnected");
    }


    @Override
    public void onMapReady(final GoogleMap map){
        gMap = map;
        gMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                getCurrentLocation();
                return true;
            }
        });
        gMap.setOnMarkerClickListener(this);
        Log.d("isHavingData", isHavingData+"");
        if(isHavingData){

            GeoPoint geoPoint = data.getArrGeoPoint();

            if (geoPoint != null && geoPoint.getLng() != 0 && location != null) {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                mCurrentLocation = new Location("");
                mCurrentLocation.setLatitude(geoPoint.getLat());
                mCurrentLocation.setLongitude(geoPoint.getLng());
                List<Address> list = null;
                try{
                    list = geocoder.getFromLocation(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude() , 1);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(list == null){
                    Log.d("주소찾기", "실패");
                }else if(list.size() > 0){
                    Address addr = list.get(0);

                    Log.d("MyLocation", "location: " + mCurrentLocation.getLatitude() + ", " + mCurrentLocation.getLongitude() + ", " + addr.getAddressLine(0) + "," + addr.toString());
                    setCurrentLocation(mCurrentLocation,"현재위치" , addr.getAddressLine(0));

                }

            }
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker){
        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(MainActivity.this);
        Log.d("geo point", mCurrentLocation.getLatitude() +", "+ mCurrentLocation.getLongitude());

        ProgressorCancelPopup.setMessage("선택한 위치가 \"" + marker.getSnippet() + "\"가 맞습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "확인 선택", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        SaveSharedPreference.setGeoPointArr(mContext, new GeoPoint(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
                        location = marker.getSnippet();
                        Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.Level.MainActivity.class);
                        i.putExtra("talentFlag", isRegisted);
                        i.putExtra("talent1", keyword1);
                        i.putExtra("talent2", keyword2);
                        i.putExtra("talent3", keyword3);
                        i.putExtra("loc", location);
                        i.putExtra("isHavingData", isHavingData);

                        if(isHavingData){
                            i.putExtra("data", data);
                        }
                        startActivity(i);

                    }
                })
                .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "취소 하기 클릭", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = ProgressorCancelPopup.create();
        alertDialog.show();

        return false;
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
        gMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }



    //TODO: 장소 3개 입력이 안되면 오류 발생, 1개 이상 입력되면 넘어가야함
    public void goNext(View v){

        Intent i = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentResister.Level.MainActivity.class);
        i.putExtra("talentFlag", isRegisted);
        i.putExtra("talent1", keyword1);
        i.putExtra("talent2", keyword2);
        i.putExtra("talent3", keyword3);
        i.putExtra("loc", location);
        i.putExtra("isHavingData", isHavingData);

        if(isHavingData){
            i.putExtra("data", data);
        }
        startActivity(i);

    }


    private void updateLocationUI(){
        try {
            Log.d("UpdateLocationUI", "gMap is null: " + (gMap == null) + ", permission = " + mLocationPermissionGranted);
            if (gMap == null) return;
            if (mLocationPermissionGranted) {
                gMap.setMyLocationEnabled(true);
                gMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                gMap.setMyLocationEnabled(false);
                gMap.getUiSettings().setMyLocationButtonEnabled(false);
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        mLocationPermissionGranted = false;
        if(requestCode == 2002){
            if(PermissionUtil.verifyPermission(grantResults)){
                mLocationPermissionGranted = true;
            }
        }
        updateLocationUI();
    }


    private void getCurrentLocation() {
        try {
            Log.d("getCurLoc", "i am in");
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            updateLocationUI();
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }


}
