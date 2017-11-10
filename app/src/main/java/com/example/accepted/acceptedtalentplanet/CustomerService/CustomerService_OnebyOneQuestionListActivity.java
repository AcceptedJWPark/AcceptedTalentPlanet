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

public class CustomerService_OnebyOneQuestionListActivity extends AppCompatActivity {


    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_OnebyOneQuetiontItem> GroupDataList;
    private ArrayList<ArrayList<CustomerService_OnebyOneAnswerItem>> ChildDataList;

    LinearLayout CustomerService_OnebyOneQuesitionList_PreBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_onebyonequestionlistactivity);

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_OnebyOne_ELV);
        setArrayData();
        ExpandableListViewTest.setAdapter(new CustomerService_OnebyOneQuestionExpandableLVAdapter(this, GroupDataList, ChildDataList));

        CustomerService_OnebyOneQuesitionList_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_OnebyOneQuesitionList_PreBtn);
        CustomerService_OnebyOneQuesitionList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setArrayData()
    {

        GroupDataList = new ArrayList<CustomerService_OnebyOneQuetiontItem>();
        ChildDataList = new ArrayList<ArrayList<CustomerService_OnebyOneAnswerItem>>();

        int sizeList = 0;

        GroupDataList.add(new CustomerService_OnebyOneQuetiontItem(
                "T.Sharing 클릭시 오류가 발생합니다. 원인이 무엇인지 궁금하며 조속히 해결 바랍니다.",
                "답변 완료",
                "2017.11.05 13:42 등록"));
        ChildDataList.add(new ArrayList<CustomerService_OnebyOneAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_OnebyOneAnswerItem(
                "T.Sharing 클릭시 오류가 발생합니다. 원인이 무엇인지 궁금하며 조속히 해결 바랍니다.",
                "해당 문제에 대해 현재 원인 파악 중이며 빠른 시일 내에 해결 조치 하도록 하겠습니다. 불편을 끼쳐드려 죄송합니다."));


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


