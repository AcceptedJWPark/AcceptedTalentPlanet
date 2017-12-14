package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-11-01.
 */

public class CustomerService_NoticeActivity extends AppCompatActivity {

    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_NoticeListItem> arrayGroup = new ArrayList<CustomerService_NoticeListItem>();
    private HashMap<CustomerService_NoticeListItem, ArrayList<String>> arrayChild = new HashMap<>();

    LinearLayout CustomerService_Notice_PreBtn;

    //TODO : 공지사항 서버에 입력하고 확인 하는 방법
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_noticeactivity);


        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_Notice_ELV);
        setArrayData();
        ExpandableListViewTest.setAdapter(new CustomerService_NoticeExpandableLVAdapter(this, arrayGroup, arrayChild));

        CustomerService_Notice_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_Notice_PreBtn);
        CustomerService_Notice_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setArrayData()
    {

        arrayGroup.add(new CustomerService_NoticeListItem
                ("Application Service Open",
                        "2018 3월 부로 Talent Planet 서비스가 오픈하였습니다. \n Talent Planet 사용 전, 고객센터의 Talent Plant 소개와 사용법을 읽어보시는 것을 권장드립니다.\n많은 관심 부탁드립니다. 감사합니다.",
                        "Mar.2018"));

       arrayGroup.add(new CustomerService_NoticeListItem
                ("Application Test Start",
                "2018 2월 부로 Talent Planet Test가 시작되었습니다. \n2018년 3월 부로 Service 오픈 예정이오니, 많은 관심 부탁드립니다.",
                "Feb.2018"));


        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("2018 3월 부로 Talent Planet 서비스가 오픈하였습니다. \n Talent Planet 사용 전, 고객센터의 Talent Plant 소개와 사용법을 읽어보시는 것을 권장드립니다.\n많은 관심 부탁드립니다. 감사합니다.");

        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("2018 2월 부로 Talent Planet Test가 시작되었습니다. \n2018년 3월 부로 Service 오픈 예정이오니, 많은 관심 부탁드립니다.");


        arrayChild.put(arrayGroup.get(0),arrayList1);
        arrayChild.put(arrayGroup.get(1),arrayList2);

    }
    
}
