package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_ClaimListActivity extends AppCompatActivity {


    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_ClaimListQuestionItem> GroupDataList;
    private ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>> ChildDataList;

    LinearLayout CustomerService_ClaimList_PreBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claimlist_activity);

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_ClaimList_ELV);
        setArrayData();
        ExpandableListViewTest.setAdapter(new CustomerService_ClaimListExpandableLVAdapter(this, GroupDataList, ChildDataList));

        CustomerService_ClaimList_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_ClaimList_PreBtn);
        CustomerService_ClaimList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setArrayData()
    {

        GroupDataList = new ArrayList<CustomerService_ClaimListQuestionItem>();
        ChildDataList = new ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>>();

        int sizeList = 0;

        //TODO: 신고하기에서 신고내용이 신고내역의 ChildView 제목으로 들어가게 해야함.
        //TODO: 신고하기, 신고내역 다시 작성해야 할 듯.
        GroupDataList.add(new CustomerService_ClaimListQuestionItem(
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "[조치 완료]",
                "2017.11.05 13:42 등록"));
        ChildDataList.add(new ArrayList<CustomerService_ClaimListAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_ClaimListAnswerItem(
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "해당 사용자에게 경고 메세지를 보냈습니다. 회원님의 포인트는 정상적으로 추가 되었습니다."));


       /*
       GroupDataList.add(new CustomerService_OnebyOneQuetiontItem(
                "문의 내용 (서버에서 받아오기)",
                "답변 대기/완료",
                "등록 시간"));
                sizeList ++;
        ChildDataList.add(new ArrayList<CustomerService_OnebyOneAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_OnebyOneAnswerItem(
                "문의 내용 (서버에서 받아오기)",
                "답변 내용"));
                */

    }

}


