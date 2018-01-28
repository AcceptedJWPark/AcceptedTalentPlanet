package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Adapter;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimActivity extends AppCompatActivity {

    Spinner CustomerService_Claim_Spinner;
    TextView CustomerService_Claim_SharingList;
    TextView CustomerService_Claim_SharingTalentTitle;
    LinearLayout CustomerService_Claim_PreBtn;
    EditText Claim_EditTxt;

    LinearLayout claim_toolbar;
    LinearLayout claim_LL1;
    LinearLayout claim_LL2;
    LinearLayout claim_LL3;
    LinearLayout claim_LL4;
    View claim_Devider1;
    TextView claim_Txt1;
    LinearLayout claim_Txt2;
    TextView CustomerService_onebyoneTextLimit;
    Context context;

    Button CustomerService_ClaimBtn;

    String name, keyword1, keyword2, keyword3, myTalentID, tarTalentID, talentFlag;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claimactivity);

        final boolean isSelect = getIntent().getBooleanExtra("isSelected", false);
        if(isSelect){
            name = getIntent().getStringExtra("name");
            keyword1 = getIntent().getStringExtra("keyword1");
            keyword2 = getIntent().getStringExtra("keyword2");
            keyword3 = getIntent().getStringExtra("keyword3");
            myTalentID = getIntent().getStringExtra("myTalentID");
            tarTalentID = getIntent().getStringExtra("tarTalentID");
            talentFlag = getIntent().getStringExtra("talentFlag");
            status = getIntent().getIntExtra("status", 0);
        }
        context = getApplicationContext();

        CustomerService_Claim_Spinner = (Spinner) findViewById(R.id.CustomerService_Claim_Spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.CustomerService_ClaimSpinnerList, R.layout.customerservice_claim_spinnertext);
        CustomerService_Claim_Spinner.setAdapter(adapter);

        CustomerService_Claim_SharingList = (TextView) findViewById(R.id.CustomerService_Claim_SharingList);
        CustomerService_Claim_SharingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SharingList_Activity.class);
                startActivity(intent);
            }
        });

        //TODO : ??
        if(isSelect){
            String str = (talentFlag.equals("Give"))?"재능드림":"관심재능";
            claim_Txt1 = (TextView) findViewById(R.id.claim_Txt1);
            claim_Txt1.setText("\"" + name + " " + str + " " +keyword1+", "+keyword2+", "+keyword3 + "의 건" + "\"");
        }


        CustomerService_Claim_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_Claim_PreBtn);
        CustomerService_Claim_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Claim_EditTxt = (EditText) findViewById(R.id.Claim_EditTxt);
        CustomerService_onebyoneTextLimit = (TextView) findViewById(R.id.CustomerService_onebyoneTextLimit);
        Claim_EditTxt.setPrivateImeOptions("defaultInputmode=korean;");
        Claim_EditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, context);
                }

            }
        });
        Claim_EditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomerService_onebyoneTextLimit.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        claim_toolbar = (LinearLayout) findViewById(R.id.claim_toolbar);
        claim_LL1 = (LinearLayout) findViewById(R.id.claim_LL1);
        claim_LL2 = (LinearLayout) findViewById(R.id.claim_LL2);
        claim_LL3 = (LinearLayout) findViewById(R.id.claim_LL3);
        claim_Devider1 = findViewById(R.id.claim_Devider1);
        claim_Txt1 = (TextView) findViewById(R.id.claim_Txt1);
        claim_Txt2 = (LinearLayout) findViewById(R.id.claim_Txt2);

        claim_LL4 = (LinearLayout) findViewById(R.id.claim_LL4);
        CustomerService_ClaimBtn = (Button) findViewById(R.id.CustomerService_ClaimBtn);
        CustomerService_ClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(CustomerService_ClaimActivity.this);
                if(Claim_EditTxt.getText().length() == 0)
                {
                    Toast.makeText(context, "신고 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(isSelect){
                    AlarmDeleteDialog.setMessage("신고하시겠습니까?")
                            .setPositiveButton("신고하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestClaim();
                                    dialog.cancel();
                                    finish();
                                }
                            })
                            .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = AlarmDeleteDialog.create();
                    alertDialog.show();
                }else{
                    AlarmDeleteDialog.setMessage("신고 대상이 없으면 조치가 어려울 수 있습니다.")
                            .setPositiveButton("신고하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestClaim();
                                    dialog.cancel();
                                    finish();
                                }
                            })
                            .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = AlarmDeleteDialog.create();
                    alertDialog.show();
                }
    }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int claim_Toolbar_height = (int) (metrics.heightPixels*0.055);
        int claim_LL1_height = (int) (metrics.heightPixels*0.17);
        int claim_LL2_height = (int) (metrics.heightPixels*0.21);
        int claim_LL3_height = (int) (metrics.heightPixels*0.042);
        int claim_LL4_height = (int) (metrics.heightPixels*0.042);
        int claim_Devider1_height = (int) (metrics.heightPixels*0.00625);
        int claim_Txt_height1 = (int) (metrics.heightPixels*0.075);
        int claim_Txt_height2 = (int) (metrics.heightPixels*0.09);

        ViewGroup.LayoutParams params1 = claim_toolbar.getLayoutParams();
        ViewGroup.LayoutParams params2 = claim_LL1.getLayoutParams();
        ViewGroup.LayoutParams params3 = claim_LL2.getLayoutParams();
        ViewGroup.LayoutParams params4 = claim_LL3.getLayoutParams();
        ViewGroup.LayoutParams params5 = claim_LL4.getLayoutParams();
        ViewGroup.LayoutParams params6 = claim_Devider1.getLayoutParams();
        ViewGroup.LayoutParams params9 = claim_Txt1.getLayoutParams();
        ViewGroup.LayoutParams params10 = claim_Txt2.getLayoutParams();

        params1.height = claim_Toolbar_height;

        params2.height = claim_LL1_height;
        params3.height = claim_LL2_height;
        params4.height = claim_LL3_height;
        params5.height = claim_LL4_height;

        params6.height = claim_Devider1_height;

        params9.height = claim_Txt_height1;
        params10.height = claim_Txt_height2;

        claim_toolbar.setLayoutParams(params1);
        claim_LL1.setLayoutParams(params2);
        claim_LL2.setLayoutParams(params3);
        claim_LL3.setLayoutParams(params4);
        claim_LL4.setLayoutParams(params5);
        claim_Devider1.setLayoutParams(params6);
        claim_Txt1.setLayoutParams(params9);
        claim_Txt2.setLayoutParams(params10);

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                CustomerService_Claim_SharingTalentTitle = (TextView) findViewById(R.id.claim_Txt1);
                CustomerService_Claim_SharingTalentTitle.setText(data.getStringExtra("data"));
            }
    }

    public void requestClaim() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/requestClaim.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);

                    if(obj.getString("result").equals("success")){
                        Toast.makeText(context, "신고가 정상적으로 접수되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, CustomerService_MainActivity.class);
                        startActivity(i);
                        finish();
                    }else
                        Toast.makeText(context, "신고가 실패하였습니다.", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);

                        JSONArray obj = new JSONArray(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                String strStatus;
                int claimType;

                Log.d("status = ", String.valueOf(status));
                switch (status){
                    case 3:
                        strStatus = "Y";
                        break;
                    case  4:
                        strStatus = "C";
                        break;
                    default:
                        strStatus = "X";
                }

                switch(CustomerService_Claim_Spinner.getSelectedItem().toString()){
                    case "금품 요구":
                        claimType = 1;
                        break;
                    case "폭언 및 욕설":
                        claimType = 2;
                        break;
                    case "No-Show":
                        claimType = 3;
                        break;
                    case "허위 광고":
                        claimType = 4;
                        break;
                    default:
                        claimType = 5;
                }

                params.put("userID", SaveSharedPreference.getUserId(context));
                params.put("myTalentID", myTalentID);
                params.put("tarTalentID", tarTalentID);
                params.put("claimType", String.valueOf(claimType));
                params.put("claimSummary", Claim_EditTxt.getText().toString());
                params.put("status", strStatus);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

}



