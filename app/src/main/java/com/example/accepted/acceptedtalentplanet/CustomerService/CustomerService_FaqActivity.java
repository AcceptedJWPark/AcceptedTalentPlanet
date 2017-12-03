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
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_FaqActivity extends AppCompatActivity {

    ExpandableListView ExpandableListViewTest;

    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

    LinearLayout CustomerService_FAQ_PreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_faqactivity);

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_FAQ_ELV);
        setArrayData();
        ExpandableListViewTest.setAdapter(new CustomerService_FaqExpandableLVAdapter(this, arrayGroup, arrayChild));


        CustomerService_FAQ_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_FAQ_PreBtn);
        CustomerService_FAQ_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setArrayData()
    {
        arrayGroup.add("재능공유는 어떻게 이루어 지나요?");
        arrayGroup.add("알람 설정은 어떻게 하나요?");
        arrayGroup.add("상대방이 완료 버튼을 누르지 않아요.");
        arrayGroup.add("상대방이 비합리적인 행동을 했습니다.");
        arrayGroup.add("관심 재능과 재능 드림이 무엇인가요?");
        arrayGroup.add("그룹으로 재능공유를 할 수는 없나요?");
        arrayGroup.add("프로필 변경은 어떻게 하나요?");
        arrayGroup.add("제게 맞는 관심 재능, 재능 드림이 없어요.");
        arrayGroup.add("재능 드림 없이 포인트를 얻을 수 있는 방법이 있나요?");
        arrayGroup.add("휴면 계정 설정은 어떻게 하나요?");
        arrayGroup.add("특정 회원과 지속적으로 재능공유를 하고 싶어요.");
        arrayGroup.add("특정 지역의 재능 리스트를 볼 수 있나요?");
        arrayGroup.add("특정 성별의 재능 리스트만을 볼 수 있나요?");
        arrayGroup.add("장소 입력에 주소가 나타나지 않네요");

        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("리스트 1의 TextView");

        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("리스트 2의 TextView");

        ArrayList<String> arrayList3 = new ArrayList<String>();
        arrayList3.add("리스트 3의 TextView");

        ArrayList<String> arrayList4 = new ArrayList<String>();
        arrayList4.add("리스트 3의 TextView");

        ArrayList<String> arrayList5 = new ArrayList<String>();
        arrayList5.add("리스트 3의 TextView");

        ArrayList<String> arrayList6 = new ArrayList<String>();
        arrayList6.add("리스트 3의 TextView");

        ArrayList<String> arrayList7 = new ArrayList<String>();
        arrayList7.add("리스트 3의 TextView");

        ArrayList<String> arrayList8 = new ArrayList<String>();
        arrayList8.add("리스트 3의 TextView");

        ArrayList<String> arrayList9 = new ArrayList<String>();
        arrayList9.add("리스트 3의 TextView");

        ArrayList<String> arrayList10 = new ArrayList<String>();
        arrayList10.add("리스트 3의 TextView");


        ArrayList<String> arrayList11 = new ArrayList<String>();
        arrayList11.add("친구 등록을 할 수 있습니다. ");


        ArrayList<String> arrayList12 = new ArrayList<String>();
        arrayList12.add("리스트 3의 TextView");

        ArrayList<String> arrayList13 = new ArrayList<String>();
        arrayList13.add("리스트 3의 TextView");

        ArrayList<String> arrayList14 = new ArrayList<String>();
        arrayList14.add("리스트 3의 TextView");


        arrayChild.put(arrayGroup.get(0),arrayList1);
        arrayChild.put(arrayGroup.get(1),arrayList2);
        arrayChild.put(arrayGroup.get(2),arrayList3);
        arrayChild.put(arrayGroup.get(3),arrayList4);
        arrayChild.put(arrayGroup.get(4),arrayList5);
        arrayChild.put(arrayGroup.get(5),arrayList6);
        arrayChild.put(arrayGroup.get(6),arrayList7);
        arrayChild.put(arrayGroup.get(7),arrayList8);
        arrayChild.put(arrayGroup.get(8),arrayList9);
        arrayChild.put(arrayGroup.get(9),arrayList10);
        arrayChild.put(arrayGroup.get(10),arrayList11);
        arrayChild.put(arrayGroup.get(11),arrayList12);
        arrayChild.put(arrayGroup.get(12),arrayList13);
        arrayChild.put(arrayGroup.get(13),arrayList13);
    }



    }
