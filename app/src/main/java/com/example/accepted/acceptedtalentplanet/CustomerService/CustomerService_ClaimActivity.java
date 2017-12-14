package com.example.accepted.acceptedtalentplanet.CustomerService;

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

import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;

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
    View claim_Devider2;
    View claim_Devider3;
    TextView claim_Txt1;
    TextView claim_Txt2;
    TextView CustomerService_onebyoneTextLimit;
    Context context;

    Button CustomerService_ClaimBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claimactivity);

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
                    hideKeyboard(v);
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
        claim_Devider2 = findViewById(R.id.claim_Devider2);
        claim_Devider3 = findViewById(R.id.claim_Devider3);
        claim_Txt1 = (TextView) findViewById(R.id.claim_Txt1);
        claim_Txt2 = (TextView) findViewById(R.id.claim_Txt2);

        claim_LL4 = (LinearLayout) findViewById(R.id.claim_LL4);
        CustomerService_ClaimBtn = (Button) findViewById(R.id.CustomerService_ClaimBtn);
        CustomerService_ClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(CustomerService_ClaimActivity.this);
                AlarmDeleteDialog.setMessage("신고하시겠습니까?")
                        .setPositiveButton("신고하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context,"신고하기 클릭 됨",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context,"취소하기 클릭 됨",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = AlarmDeleteDialog.create();
                alertDialog.show();
    }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int claim_Toolbar_height = (int) (metrics.heightPixels*0.075);
        int claim_LL1_height = (int) (metrics.heightPixels*0.17);
        int claim_LL2_height = (int) (metrics.heightPixels*0.21);
        int claim_LL3_height = (int) (metrics.heightPixels*0.042);
        int claim_LL4_height = (int) (metrics.heightPixels*0.042);
        int claim_Devider1_height = (int) (metrics.heightPixels*0.00625);
        int claim_Devider2_height = (int) (metrics.heightPixels*0.0208);
        int claim_Txt_height = (int) (metrics.heightPixels*0.07);

        ViewGroup.LayoutParams params1 = claim_toolbar.getLayoutParams();
        ViewGroup.LayoutParams params2 = claim_LL1.getLayoutParams();
        ViewGroup.LayoutParams params3 = claim_LL2.getLayoutParams();
        ViewGroup.LayoutParams params4 = claim_LL3.getLayoutParams();
        ViewGroup.LayoutParams params5 = claim_LL4.getLayoutParams();
        ViewGroup.LayoutParams params6 = claim_Devider1.getLayoutParams();
        ViewGroup.LayoutParams params7 = claim_Devider2.getLayoutParams();
        ViewGroup.LayoutParams params8 = claim_Devider3.getLayoutParams();
        ViewGroup.LayoutParams params9 = claim_Txt1.getLayoutParams();
        ViewGroup.LayoutParams params10 = claim_Txt2.getLayoutParams();

        params1.height = claim_Toolbar_height;

        params2.height = claim_LL1_height;
        params3.height = claim_LL2_height;
        params4.height = claim_LL3_height;
        params5.height = claim_LL4_height;

        params6.height = claim_Devider1_height;
        params7.height = claim_Devider2_height;
        params8.height = claim_Devider2_height;

        params9.height = claim_Txt_height;
        params10.height = claim_Txt_height;

        claim_toolbar.setLayoutParams(params1);
        claim_LL1.setLayoutParams(params2);
        claim_LL2.setLayoutParams(params3);
        claim_LL3.setLayoutParams(params4);
        claim_LL4.setLayoutParams(params5);
        claim_Devider1.setLayoutParams(params6);
        claim_Devider2.setLayoutParams(params7);
        claim_Devider3.setLayoutParams(params8);
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

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    }



