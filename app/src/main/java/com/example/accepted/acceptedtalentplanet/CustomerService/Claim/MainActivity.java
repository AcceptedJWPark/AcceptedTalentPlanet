package com.example.accepted.acceptedtalentplanet.CustomerService.Claim;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MainActivity extends AppCompatActivity {

    private Spinner spn_ClaimType;
    private TextView tv_SharingList;
    private TextView tv_Txt;
    private LinearLayout ll_preContainer;
    private EditText et_Claim;

    private LinearLayout ll_Toolbar;
    private LinearLayout ll_Container1;
    private LinearLayout ll_Container2;
    private LinearLayout ll_Container3;
    private LinearLayout ll_Container4;
    private LinearLayout ll_Container5;

    private View v_Divider;
    private TextView tv_TxtCount;
    private Context mContext;

    private Button btn_SaveClaim;

    private String name, keyword1, keyword2, keyword3, myTalentID, tarTalentID, talentFlag;
    private int status;

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
        mContext = getApplicationContext();

        spn_ClaimType = (Spinner) findViewById(R.id.spn_ClaimType_Claim);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.CustomerService_ClaimSpinnerList, R.layout.customerservice_claim_spinnertext);
        spn_ClaimType.setAdapter(adapter);

        tv_SharingList = (TextView) findViewById(R.id.tv_SharingList_Claim);
        tv_SharingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.SharingList.MainActivity.class);
                startActivity(intent);
            }
        });

        //TODO : ??
        if(isSelect){
            String str = (talentFlag.equals("Give"))?"재능드림":"관심재능";
            tv_Txt = (TextView) findViewById(R.id.tv_txt_Claim);
            tv_Txt.setText("\"" + name + " " + str + " " +keyword1+", "+keyword2+", "+keyword3 + "의 건" + "\"");
        }


        ll_preContainer = (LinearLayout) findViewById(R.id.ll_preContainer_Claim);
        ll_preContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_Claim = (EditText) findViewById(R.id.et_Content_Claim);
        tv_TxtCount = (TextView) findViewById(R.id.tv_TxtCount_Claim);
        et_Claim.setPrivateImeOptions("defaultInputmode=korean;");
        et_Claim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }

            }
        });
        et_Claim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_TxtCount.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ll_Toolbar = (LinearLayout) findViewById(R.id.ll_Toolbar_Claim);
        ll_Container1 = (LinearLayout) findViewById(R.id.ll_Container1_claim);
        ll_Container2 = (LinearLayout) findViewById(R.id.ll_Container2_claim);
        ll_Container3 = (LinearLayout) findViewById(R.id.ll_Container3_claim);
        ll_Container4 = (LinearLayout) findViewById(R.id.ll_Container4_claim);
        ll_Container5 = (LinearLayout) findViewById(R.id.ll_Container5_claim);

        v_Divider = findViewById(R.id.v_Divider_Claim);
        tv_Txt = (TextView) findViewById(R.id.tv_txt_Claim);

        btn_SaveClaim = (Button) findViewById(R.id.btn_SaveClaim_Claim);
        btn_SaveClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(MainActivity.this);
                if(et_Claim.getText().length() == 0)
                {
                    Toast.makeText(mContext, "신고 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
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
        int claim_LL3_height = (int) (metrics.heightPixels*0.04);
        int claim_LL4_height = (int) (metrics.heightPixels*0.04);
        int claim_Devider1_height = (int) (metrics.heightPixels*0.00625);
        int claim_Txt_height1 = (int) (metrics.heightPixels*0.075);
        int claim_Txt_height2 = (int) (metrics.heightPixels*0.09);

        ViewGroup.LayoutParams params1 = ll_Toolbar.getLayoutParams();
        ViewGroup.LayoutParams params2 = ll_Container1.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_Container2.getLayoutParams();
        ViewGroup.LayoutParams params4 = ll_Container3.getLayoutParams();
        ViewGroup.LayoutParams params5 = ll_Container4.getLayoutParams();
        ViewGroup.LayoutParams params6 = v_Divider.getLayoutParams();
        ViewGroup.LayoutParams params9 = tv_Txt.getLayoutParams();
        ViewGroup.LayoutParams params10 = ll_Container5.getLayoutParams();

        params1.height = claim_Toolbar_height;

        params2.height = claim_LL1_height;
        params3.height = claim_LL2_height;
        params4.height = claim_LL3_height;
        params5.height = claim_LL4_height;

        params6.height = claim_Devider1_height;

        params9.height = claim_Txt_height1;
        params10.height = claim_Txt_height2;

        ll_Toolbar.setLayoutParams(params1);
        ll_Container1.setLayoutParams(params2);
        ll_Container2.setLayoutParams(params3);
        ll_Container3.setLayoutParams(params4);
        ll_Container4.setLayoutParams(params5);
        ll_Container5.setLayoutParams(params10);

        v_Divider.setLayoutParams(params6);
        tv_Txt.setLayoutParams(params9);

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                tv_Txt = (TextView) findViewById(R.id.tv_txt_Claim);
                tv_Txt.setText(data.getStringExtra("data"));
            }
    }

    //TODO 신고대상 없어도 내역 추가 되는 걸로 수정//
    public void requestClaim() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/requestClaim.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);
                        Toast.makeText(mContext, "신고가 정상적으로 접수되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.CustomerService.MainActivity.class);
                        startActivity(i);
                        finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
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

                switch(spn_ClaimType.getSelectedItem().toString()){
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

                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("myTalentID", myTalentID);
                params.put("tarTalentID", tarTalentID);
                params.put("claimType", String.valueOf(claimType));
                params.put("claimSummary", et_Claim.getText().toString());
                params.put("status", strStatus);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

}



