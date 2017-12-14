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

        //TODO: 신고할 때 공유내역이 있으면 신고 내역에 공유 내역이 제목으로, 없으면 신고 내용이 제목으로 되어야 함
        //TODO: 신고하기 버튼에 공유내역 없을 시 Dialog 달아야 함 ("공유 내역과 첨부 파일이 없으면 조치가 어려울 수 있습니다.")
        //TODO: 신고 대상(공유 내역), 신고 유형, 신고 일자, 신고 내용이 신고 내역으로 들어가야 함. 없으면 공유 내역 없으면 신고 대상 "-"
        GroupDataList.add(new CustomerService_ClaimListQuestionItem(
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "[조치 완료]",
                "2017.11.05 13:42 등록"));
        ChildDataList.add(new ArrayList<CustomerService_ClaimListAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_ClaimListAnswerItem(
                "박종우님과 재능 드림 기타, 기타 연주, 기타 독주 진행의 건",
                "금품 요구",
                "2017.12.11 22:20",
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "해당 신고에 대해 확인 중에 있습니다. 조속히 처리하도록 하겠습니다."
        ));

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


