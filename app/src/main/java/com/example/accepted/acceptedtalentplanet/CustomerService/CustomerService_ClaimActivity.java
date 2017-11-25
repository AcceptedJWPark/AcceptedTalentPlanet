package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimActivity extends AppCompatActivity {

    Spinner CustomerService_Claim_Spinner;
    TextView CustomerService_Claim_SharingList;
    TextView CustomerService_Claim_SharingTalentTitle;
    LinearLayout CustomerService_Claim_PreBtn;
    EditText Claim_EditTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claimactivity);



        CustomerService_Claim_Spinner = (Spinner) findViewById(R.id.CustomerService_Claim_Spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.CustomerService_ClaimSpinnerList, R.layout.customerservice_claim_spinnertext);
        CustomerService_Claim_Spinner.setAdapter(adapter);

        CustomerService_Claim_SharingList = (TextView) findViewById(R.id.CustomerService_Claim_SharingList);
        CustomerService_Claim_SharingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_Claim_SharingListActivity.class);
                startActivityForResult(intent, 1);
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
        Claim_EditTxt.setPrivateImeOptions("defaultInputmode=korean;");


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                CustomerService_Claim_SharingTalentTitle = (TextView) findViewById(R.id.CustomerService_Claim_SharingTalentTitle);
                CustomerService_Claim_SharingTalentTitle.setText(data.getStringExtra("data"));
            }
    }
    }



