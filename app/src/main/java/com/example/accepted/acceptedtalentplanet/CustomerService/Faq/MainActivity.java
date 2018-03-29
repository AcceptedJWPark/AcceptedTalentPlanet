package com.example.accepted.acceptedtalentplanet.CustomerService.Faq;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private ArrayList<String> arrayList_Parent = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayList_Child = new HashMap<String, ArrayList<String>>();

    private LinearLayout ll_PreContainer;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_faqactivity);

        mContext = getApplicationContext();

        expandableListView = (ExpandableListView) this.findViewById(R.id.elv_Faq);
        setArrayData();
        expandableListView.setAdapter(new ELVAdapter(this, arrayList_Parent, arrayList_Child));


        ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_Faq);
        ll_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            int lastClickedPosition = 0;
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean isExpand = (!expandableListView.isGroupExpanded(groupPosition));
                expandableListView.collapseGroup(lastClickedPosition);
                if(isExpand)
                {
                    expandableListView.expandGroup(groupPosition);
                }lastClickedPosition = groupPosition;
                return true;
            }
        });

    }

    private void setArrayData()
    {


        arrayList_Parent.add("알람 설정은 어떻게 하나요?");
        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("좌측 상단의 슬라이드 메뉴 → 설정 → 알람 설정에서 가능합니다.");

        arrayList_Parent.add("장소 입력에 주소가 나타나지 않네요");
        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("좌측 상단의 슬라이드 메뉴 → 고객센터 → 1:1 질문하기에 회원님의 장소를 입력하여 주세요. 빠른 시일 내에 추가하도록 하겠습니다");

        arrayList_Parent.add("상대방이 완료 버튼을 누르지 않아요.");
        ArrayList<String> arrayList3 = new ArrayList<String>();
        arrayList3.add("상대방 신고하기가 가능합니다. 공유내역에서 신고를 하거나, 좌측 상단의 슬라이드 메뉴 → 고객센터 → 신고하기를 통해 상대방을 신고 할 수 있습니다. 신고내용 확인 후 조속히 조치하도록 하겠습니다.");


        arrayList_Parent.add("상대방이 비합리적인 행동을 했습니다.");
        ArrayList<String> arrayList4 = new ArrayList<String>();
        arrayList4.add("상대방 신고하기가 가능합니다. 공유내역에서 신고를 하거나, 좌측 상단의 슬라이드 메뉴 → 고객센터 → 신고하기를 통해 상대방을 신고 할 수 있습니다. 신고내용 확인 후 조속히 조치하도록 하겠습니다.");


        arrayList_Parent.add("관심 재능과 재능 드림이 무엇인가요?");
        ArrayList<String> arrayList5 = new ArrayList<String>();
        arrayList5.add("Talented에는 두 가지 재능이 존재합니다. 관심 재능은 회원님이 배우고 싶은, 관심이 있는 분야입니다. 반대로 재능 드림은 회원님이 가르쳐 줄 수 있는, 공유할 수 있는 재능입니다. 회원님의 관심 재능(재능 드림)과 상대방의 재능 드림(관심 재능)이 매칭이 되어 재능 공유가 진행 됩니다.");


        arrayList_Parent.add("그룹으로 재능공유를 할 수는 없나요?");
        ArrayList<String> arrayList6 = new ArrayList<String>();
        arrayList6.add("현재 해당 기능에 대해 개발 중에 있습니다. 조속히 개발하여 업데이트 하도록 하겠습니다.");


        arrayList_Parent.add("프로필 변경은 어떻게 하나요?");
        ArrayList<String> arrayList7 = new ArrayList<String>();
        arrayList7.add("좌측 상단의 슬라이드 메뉴 → My Profile에서 변경이 가능합니다. 변경이 가능한 프로필은 사진, 성별 공개/비공개, 생년월일 공개/비공개, 직업, 직업 공개/비공개 입니다.");


        arrayList_Parent.add("제게 맞는 관심 재능, 재능 드림이 없어요.");
        ArrayList<String> arrayList8 = new ArrayList<String>();
        arrayList8.add("좌측 상단의 슬라이드 메뉴 → 고객센터 → 1:1 질문하기에 회원님의 관심 재능, 재능 드림을 설명해주세요. 저희가 홍보하여 회원님의 재능 공유에 도움이 되겠습니다.");


        arrayList_Parent.add("재능 드림 없이 포인트를 얻을 수 있는 방법이 있나요?");
        ArrayList<String> arrayList9 = new ArrayList<String>();
        arrayList9.add("현재로서는 재능 드림 없이 포인트를 얻을 수 있는 방법은 없습니다.");

        arrayList_Parent.add("휴면 계정 설정은 어떻게 하나요?");
        ArrayList<String> arrayList10 = new ArrayList<String>();
        arrayList10.add("좌측 상단의 슬라이드 메뉴 → 설정 → 휴면 계정 설정을 통해 가능합니다.");

        arrayList_Parent.add("특정 회원과 지속적으로 재능공유를 하고 싶어요.");
        ArrayList<String> arrayList11 = new ArrayList<String>();
        arrayList11.add("해당 회원의 프로필 → 좌측 상단에 별 그림을 클릭하면 해당 회원을 친구로 등록이 가능합니다.\n");


        arrayList_Parent.add("특정 지역의 재능 리스트를 볼 수 있나요?");
        ArrayList<String> arrayList12 = new ArrayList<String>();
        arrayList12.add("좌측 상단의 슬라이드 메뉴 → 재능 검색 → 검색 조건 변경에서 지역을 선택하여 검색이 가능합니다.");





        arrayList_Child.put(arrayList_Parent.get(0),arrayList1);
        arrayList_Child.put(arrayList_Parent.get(1),arrayList2);
        arrayList_Child.put(arrayList_Parent.get(2),arrayList3);
        arrayList_Child.put(arrayList_Parent.get(3),arrayList4);
        arrayList_Child.put(arrayList_Parent.get(4),arrayList5);
        arrayList_Child.put(arrayList_Parent.get(5),arrayList6);
        arrayList_Child.put(arrayList_Parent.get(6),arrayList7);
        arrayList_Child.put(arrayList_Parent.get(7),arrayList8);
        arrayList_Child.put(arrayList_Parent.get(8),arrayList9);
        arrayList_Child.put(arrayList_Parent.get(9),arrayList10);
        arrayList_Child.put(arrayList_Parent.get(10),arrayList11);
        arrayList_Child.put(arrayList_Parent.get(11),arrayList12);
    }

}
