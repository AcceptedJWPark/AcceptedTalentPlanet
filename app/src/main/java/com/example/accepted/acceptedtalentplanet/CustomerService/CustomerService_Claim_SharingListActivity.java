package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class CustomerService_Claim_SharingListActivity extends AppCompatActivity implements CustomerService_Claim_Sharing_Adapter.ListBtnClickListener {

    Context mContext;
    ListView ClaimListArrayTakeListView;
    ListView ClaimListArrayGiveListView;
    ArrayList<CustomerService_Claim_Sharing_Item> ClaimListArrayList_Take;
    ArrayList<CustomerService_Claim_Sharing_Item> ClaimListArrayList_Give;
    CustomerService_Claim_Sharing_Adapter ClaimListArrayListAdapter;
    Button CustomerService_Claim_SharingTakeBtn;
    Button CustomerService_Claim_SharingGiveBtn;
    ListView CustomerService_Claim_SharingTakeLV;
    ListView CustomerService_Claim_SharingGiveLV;
    String sendingMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claim_sharinglist);

        mContext = getApplicationContext();

        ClaimListArrayTakeListView = (ListView) findViewById(R.id.CustomerService_Claim_SharingTakeLV);
        ClaimListArrayList_Take = new ArrayList<>();
        ClaimListArrayList_Take.add(new CustomerService_Claim_Sharing_Item("박종우", "2017.11.06 15:31 등록", "Guitar", "기타 연주", "기타","진행"));
        ClaimListArrayList_Take.add(new CustomerService_Claim_Sharing_Item("민권홍", "2017.11.06 15:34 등록", "Piano", "피아노 연주", "피아노","완료"));

        ClaimListArrayListAdapter = new CustomerService_Claim_Sharing_Adapter(this, R.layout.customerservice_claim_sharinglist_childview, ClaimListArrayList_Take, this);
        ClaimListArrayTakeListView.setAdapter(ClaimListArrayListAdapter);

        ClaimListArrayGiveListView = (ListView) findViewById(R.id.CustomerService_Claim_SharingGiveLV);
        ClaimListArrayList_Give = new ArrayList<>();
        ClaimListArrayList_Give.add(new CustomerService_Claim_Sharing_Item("김용인", "2017.12.16 15:31 등록", "Guitar", "기타 연주", "기타","진행"));
        ClaimListArrayList_Give.add(new CustomerService_Claim_Sharing_Item("우승제", "2017.12.26 12:34 등록", "태권도", "돌려차기", "격파","취소"));
        ClaimListArrayList_Give.add(new CustomerService_Claim_Sharing_Item("배대명", "2018.01.06 18:34 등록", "빗박빗박빗박", "Beat", "비트비트비트","완료"));
        ClaimListArrayList_Give.add(new CustomerService_Claim_Sharing_Item("배대명", "2018.01.06 18:34 등록", "비트박스", "BeatBox", "북치기박치기","완료"));
        ClaimListArrayList_Give.add(new CustomerService_Claim_Sharing_Item("배대명", "2018.01.06 18:34 등록", "비트박스", "BeatBox", "북치기박네네네네네치기","완료"));

        ClaimListArrayListAdapter = new CustomerService_Claim_Sharing_Adapter(this, R.layout.customerservice_claim_sharinglist_childview, ClaimListArrayList_Give, this);
        ClaimListArrayGiveListView.setAdapter(ClaimListArrayListAdapter);


        CustomerService_Claim_SharingTakeBtn = (Button) findViewById(R.id.CustomerService_Claim_SharingTakeBtn);
        CustomerService_Claim_SharingTakeLV = (ListView) findViewById(R.id.CustomerService_Claim_SharingTakeLV);
        CustomerService_Claim_SharingTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingTakeBtnClicked();
            }
        });

        CustomerService_Claim_SharingGiveBtn = (Button) findViewById(R.id.CustomerService_Claim_SharingGiveBtn);
        CustomerService_Claim_SharingGiveLV = (ListView) findViewById(R.id.CustomerService_Claim_SharingGiveLV);
        CustomerService_Claim_SharingGiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingGiveBtnClicked();
            }
        });

    }

    @Override
    public void onListBtnClick(int position) {
        if (CustomerService_Claim_SharingGiveLV.getVisibility()==View.GONE)
        {
                sendingMessage =
                        ClaimListArrayList_Take.get(position).getname()+"님과  \""
                        + ClaimListArrayList_Take.get(position).getkeyword1()+", "
                        + ClaimListArrayList_Take.get(position).getkeyword2()+", "
                        + ClaimListArrayList_Take.get(position).getkeyword3()+"\" "
                        + ClaimListArrayList_Take.get(position).gettalentCondition()+"의 건";

                Intent intent = new Intent();
                intent.putExtra("data",sendingMessage);
                setResult(Activity.RESULT_OK,intent);
                finish();
        }
        else{
            sendingMessage =
                    ClaimListArrayList_Give.get(position).getname()+"님과  \""
                    + ClaimListArrayList_Give.get(position).getkeyword1()+", "
                    + ClaimListArrayList_Give.get(position).getkeyword2()+", "
                    + ClaimListArrayList_Give.get(position).getkeyword3()+"\" "
                    + ClaimListArrayList_Give.get(position).gettalentCondition()+"의 건";

            Intent intent = new Intent();
            intent.putExtra("data",sendingMessage);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }

    }

    public void SharingGiveBtnClicked()
    {
        CustomerService_Claim_SharingGiveLV.setVisibility(View.VISIBLE);
        CustomerService_Claim_SharingTakeLV.setVisibility(View.GONE);
        CustomerService_Claim_SharingGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_graybackground));
        CustomerService_Claim_SharingTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_whitebackground));
        CustomerService_Claim_SharingGiveBtn.setTextColor(Color.parseColor("#505050"));
        CustomerService_Claim_SharingTakeBtn.setTextColor(Color.parseColor("#d2d2d2"));
    }
    public void SharingTakeBtnClicked()
    {
        CustomerService_Claim_SharingTakeLV.setVisibility(View.VISIBLE);
        CustomerService_Claim_SharingGiveLV.setVisibility(View.GONE);
        CustomerService_Claim_SharingTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_graybackground));
        CustomerService_Claim_SharingGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_whitebackground));
        CustomerService_Claim_SharingTakeBtn.setTextColor(Color.parseColor("#505050"));
        CustomerService_Claim_SharingGiveBtn.setTextColor(Color.parseColor("#d2d2d2"));
    }

}



