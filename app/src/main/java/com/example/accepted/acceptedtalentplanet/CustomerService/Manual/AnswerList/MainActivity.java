package com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

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

    private Button btn_Pre;

    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_manual_answerlist);

        mContext = getApplicationContext();

        Intent intent = getIntent();
        String valueTypes = intent.getStringExtra("Value");
        Log.d(valueTypes,"전달 값");
        expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView_Manual);
        expandableListView.setAdapter(new ELVAdapter(this, arrayList_Parent, arrayList_Child));
        setArrayData(valueTypes);
        btn_Pre = (Button) findViewById(R.id.btn_Pre_Manual);
        btn_Pre.setOnClickListener(new View.OnClickListener() {
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

    private void setArrayData(String value)
    {
        if (value.equals("MyProfile"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("My Profile");
            arrayList1.add(" 회원님이 회원 가입 시 입력한 계정 정보를 확인할 수 있습니다. 성별, 생년월일, 직업 수정 및 비공개 처리가 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("사진 등록");
            arrayList2.add(" 사진 등록은 1장 가능합니다. 회원님 또는 회원님의 재능을 표현 할 수 있는 사진을 업로드하면 더 수월한 재능 공유가 될 것입니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("E-mail(계정)");
            arrayList3.add(" E-mail 주소는 계정으로 사용되며 비밀번호 변경 및 찾기에 사용되므로 잊어버리지 않게 유의하시지 바랍니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("성별");
            arrayList4.add(" 성별은 가입 시 등록한 성별 이외에 수정이 불가하지만 공개/비공개가 가능한 정보입니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

            ArrayList<String> arrayList5 = new ArrayList<String>();
            arrayList_Parent.add("생년월일");
            arrayList5.add(" 생년월일은 가입 시 등록한 생년월일 이외에 수정이 불가하지만 공개/비공개가 가능한 정보입니다.");
            arrayList_Child.put(arrayList_Parent.get(4),arrayList5);

            ArrayList<String> arrayList6 = new ArrayList<String>();
            arrayList_Parent.add("직업");
            arrayList6.add(" 직업은 언제든지 수정이 가능한 정보 입니다. 등록하신 재능과 연관이 있는 직업이라면 재능 매칭에 어필이 될 것입니다.");
            arrayList_Child.put(arrayList_Parent.get(5),arrayList6);

            ArrayList<String> arrayList7 = new ArrayList<String>();
            arrayList_Parent.add("재능 드림 / 관심 재능");
            arrayList7.add(" 재능 드림, 관심 재능은 가입 이후 필수 입력 사항입니다. 재능 입력이 안된 상태에서 TalentPlanet 사용이 불가합니다.\n  \"My Profile\" → \"미등록\"을 클릭 또는 \"좌측 상단의 슬라이드 메뉴\" → \"My Talent\"에서 재능 등록을 꼭 해주세요.");
            arrayList_Child.put(arrayList_Parent.get(6),arrayList7);
        }
        else if (value.equals("MyTalent"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("My Talent");
            arrayList1.add(" 회원님의 관심 재능 및 재능 드림을 등록할 수 있습니다. 재능 입력이 안된 상태에서는 TalentPlanet 사용이 불가하니 필히 입력해야 합니다.\n  \"My Profile\" → \"미등록\"을 클릭 또는 \"좌측 상단의 슬라이드 메뉴\" → \"My Talent\"에서 재능 등록을 꼭 해주세요.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("재능 등록");
            arrayList2.add(" 재능 등록 키워드는 3개 필수 입력 정보입니다. 세 개의 키워드는 겹치지 않는 키워드로 입력을 해야 합니다.\n 회원님의 관심 재능(재능 드림)과 상대방의 재능 드림(관심 재능)이 매칭 되어 T.Sharing에 나열 됩니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("장소 등록");
            arrayList3.add(" 장소 등록은 Google Map을 사용합니다. 회원님이 재능 공유하고 싶은 장소를 지도에서 선택하면 됩니다. T.Sharing에서는 재능 키워드가 매칭되는 회원들이 나타나고 최단거리 순으로 정렬됩니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("수준");
            arrayList4.add(" 관심 재능, 재능 드림 모두 회원님 본인의 수준을 입력 해야 합니다. 총 다섯 개 수준(시작 단계, 초급, 중급, 상급, 전문가)으로 나뉘어집니다.\n 회원님의 재능 수준은 다른 회원들의 재능 공유를 결정 할 요인이 될 겁니다. 재능 수준을 허위로 입력할 경우 차후 재능 공유 시 신고를 당할 수 있으니 유의 바랍니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

            ArrayList<String> arrayList5 = new ArrayList<String>();
            arrayList_Parent.add("포인트 등록");
            arrayList5.add(" 회원님의 수준에 따라 포인트를 입력해야 합니다. \n - 재능 드림 포인트 : 재능 공유 후 회원님이 얻는 포인트 \n - 관심 재능 포인트 : 재능 공유 후 상대방에게 제공하는 포인트 \n 재능 드림의 너무 높은 포인트 혹은 관심 재능의 너무 낮은 포인트는 상대방의 재능 공유를 망설이게 할 수 있습니다. 1회 최대 공유포인트는 300포인트로 제한됩니다.");
            arrayList_Child.put(arrayList_Parent.get(4),arrayList5);
        }
        else if(value.equals("TSharing"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("T.Sharing");
            arrayList1.add(" 회원님과 재능 매칭이 되는 사용자들이 거리 순으로 정렬됩니다. T . Sharing을 통해 매칭 확률이 높은 상대와 재능 공유를 시작하세요.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("재능 드림 / 관심 재능");
            arrayList2.add(" Talented에는 재능 드림과 관심 재능 두 가지 재능이 있습니다. \n 1) 재능 드림 : 가르쳐 줄 수 있는, 공유 할 수 있는 재능 \n 2) 관심 재능 : 배우고 싶은, 관심을 가지고 있는 재능");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("회원 리스트");
            arrayList3.add(" T.Sharing에 나타나는 회원 리스트는 회원님이 재능 등록한 세 개의 키워드 중 하나 이상 공통 재능을 입력한 회원들이 나열 됩니다. 이때 회원님과의 최단거리 순으로 나열되니 회원님과 재능 공유가 편한 상대에게 관심을 보내 재능 공유를 시작하세요.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);
        }
        else if(value.equals("TCondition"))
        {
            arrayList_Parent.add("나의 재능현황");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(" 회원님의 현재 재능 상태를 확인할 수 있습니다. \"대기 중, 진행 중, 완료\" 세 가지 상태로 구분됩니다.");
            arrayList_Child.put(this.arrayList_Parent.get(0),arrayList);

            this.arrayList_Parent.add("대기 중");
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList1.add(" 회원님이 원하는 다른 회원들과 관심을 주고 받을 수 있는 상태입니다. 회원님이 관심을 보낸 상대방이 진행하기를 누르면 진행하기 상태로 변합니다. 반대로 다른 회원들이 회원님께 관심을 보내 회원님이 진행하기를 누를 수도 있습니다. 관심을 주고 받은 상대가 없다면 T.Sharing을 통해 회원님과 재능 공유를 할 대상에게 관심을 보내보세요.\n \"대기 중\" → \"진행 중\"으로 상태가 변할 때에는 주고 받은 관심들은 전부 취소 상태가 됩니다. 재능 공유에 가능성이 있는 회원들은 친구 추가를 할 수 있습니다.");
            arrayList_Child.put(this.arrayList_Parent.get(1),arrayList1);

            this.arrayList_Parent.add("진행 중");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList2.add(" 회원님의 재능이 현재 진행 중에 있는 상태입니다. 진행 중의 상태에서는 실제 재능 공유가 이루어 지는 시점입니다. 재능 공유가 이루어진 후 완료하기를 누르면 완료 상태로 변하며 포인트 교환이 이루어집니다. 반대로 진행 취소를 누르면 다시 대기 중의 상태로 변합니다.\n 재능 공유가 완료되는 과정은 1) 재능 드림을 한 회원이 먼저 완료하기를 누르고 2) 관심 재능을 한 회원이 완료하기를 누르면 공유가 완료됩니다.");
            arrayList_Child.put(this.arrayList_Parent.get(2),arrayList2);

            this.arrayList_Parent.add("완료");
            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList3.add(" 완료 상태에서는 회원님의 재능은 미등록 상태 입니다. 완료 후 재능 재등록 또는 재능 수정하기를 해야 회원님의 재능이 다시 활성화가 됩니다. 이 점 꼭 유의하시기 바랍니다.");
            arrayList_Child.put(this.arrayList_Parent.get(3),arrayList3);
        }
        else if(value.equals("TSearching"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("재능 검색");
            arrayList1.add(" T.Sharing에서 재능 공유 대상을 찾을 수 없다면 재능 검색을 활용해 볼 수 있습니다. 검색조건 변경을 통해 회원님이 원하는 재능을 검색 할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("키워드");
            arrayList2.add(" 키워드는 최대 세 개 입력 할 수 있으며 T.Sharing과 마찬가지로 입력한 키워드를 공통으로 등록한 회원들의 리스트가 나열됩니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("장소");
            arrayList3.add(" 장소는 전체로 검색 할 수 있고 특정 지역의 재능 리스트를 검색 할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("수준");
            arrayList4.add(" 수준에 따른 재능 검색이 가능합니다. 전체를 검색하려면 \"시작 단계\" ~ \"전문가 이하\"로 검색 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

            ArrayList<String> arrayList5 = new ArrayList<String>();
            arrayList_Parent.add("포인트");
            arrayList5.add(" 포인트에 따른 재능 검색이 가능합니다. \"공란\"으로 비워 놓으면 포인트 전체 검색이 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(4),arrayList5);
        }
        else if(value.equals("CustomerService"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("고객센터");
            arrayList1.add(" Talented App 버전, 업데이트 내역과 회원님이 사용 시 문제점, 궁금한 점을 확인하고 문의할 수 있습니다. 또한 개선할 점, 오류 등을 건의할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("Talented 소개");
            arrayList2.add(" Talented에 대한 간략한 App 소개를 확인할 수 있습니다. 시작 전 읽어 보시는 것을 권유 드립니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("버전 정보");
            arrayList3.add(" 회원님의 버전과 최신 버전을 확인할 수 있습니다. 버전이 다를 경우 App 사용에 문제가 발생할 수 있으니, 최신 버전으로 업데이트 바랍니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("업데이트 내역");
            arrayList4.add(" 신규 업데이트 된 기능 및 버전을 확인할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

            ArrayList<String> arrayList5 = new ArrayList<String>();
            arrayList_Parent.add("공지사항");
            arrayList5.add(" App 운영에 관한 공지사항을 확인할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(4),arrayList5);

            ArrayList<String> arrayList6 = new ArrayList<String>();
            arrayList_Parent.add("Talented 사용하기");
            arrayList6.add(" Talented App에 대한 Manual을 제공합니다. 사용 중 문제가 발생하거나 궁금하신 부분이 있을 때 사용할 수 있습니다. 원하는 내용이 없을 시 1:1 문의하기를 이용하시면 빠른 시일내에 답변을 받을 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(5),arrayList6);

            ArrayList<String> arrayList7 = new ArrayList<String>();
            arrayList_Parent.add("자주하는 질문");
            arrayList7.add(" 자주 하는 질문에 대한 내용과 답변이 있습니다. 원하는 내용이 없을 시 1:1 문의하기를 이용하시면 빠른 시일내에 답변을 받을 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(6),arrayList7);

            ArrayList<String> arrayList8 = new ArrayList<String>();
            arrayList_Parent.add("1:1 질문하기 / 나의 문의내역");
            arrayList8.add(" App 사용 중 발생하는 문제 혹은 궁금하신 부분이 있을 때 1:1 문의하기를 이용하시면 빠른 시일내에 답변을 받을 수 있습니다. 문의 내용은 1,000자로 제한됩니다.\n 회원님께서 문의하신 내용은 나의 문의내역에서 확인이 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(7),arrayList8);

            ArrayList<String> arrayList9 = new ArrayList<String>();
            arrayList_Parent.add("상대방 신고하기 / 신고내역");
            arrayList9.add(" Talented 사용 중 비합리적인 행위를 한 회원을 신고할 수 있습니다. 공유 내역을 선택, 신고 유형을 선택하여 신고가 가능합니다. 신고 내용은 1,000자로 제한됩니다. 신고에 대한 증거로서 사진을 첨부할 수 있으며 증거 사진이 있을 경우 빠른 조치가 가능합니다. 공유 내역과 첨부 사진이 없는 경우 조치가 어려울 수 있습니다.\n 회원님의 신고 내용에 대한 조치 사항은 신고내역에서 확인할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(8),arrayList9);
        }
        else if(value.equals("System"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("설정");
            arrayList1.add(" 알람 설정 및 계정 설정이 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("메시지 알람 설정");
            arrayList2.add(" 받은 메시지에 대한 알람을 켜고 끌 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("재능상태 알람 설정");
            arrayList3.add(" 받은 관심, 재능 상태 변경(취소, 진행, 완료)에 대한 알람을 켜고 끌 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("답변여부 알람 설정");
            arrayList4.add(" 1:1문의하기, 신고하기에 대한 답변여부 알람을 켜고 끌 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

            ArrayList<String> arrayList5 = new ArrayList<String>();
            arrayList_Parent.add("비밀번호 변경");
            arrayList5.add(" 비밀번호 변경을 할 수 있습니다. 비밀번호 변경은 등록하신 계정(E-mail)에 보낸 인증 코드를 입력하여 변경이 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(4),arrayList5);

            ArrayList<String> arrayList6 = new ArrayList<String>();
            arrayList_Parent.add("휴면 계정 설정");
            arrayList6.add(" 휴면 계정으로 설정이 가능합니다. 휴면 계정으로 설정 시 회원님이 등록하신 재능은 미등록 상태가 되고 모든 알람은 중지 됩니다. 휴면 계정을 다시 활성화 시키려면 \"휴면 계정 설정\" → \"계정 활성화\"로 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(5),arrayList6);
        }
        else if(value.equals("Alarm"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("알림");
            arrayList1.add(" 회원님 재능의 관심, 재능 상태, 메시지, 답변 여부 등을 확인할 수 있습니다. \"우측 상단 종\" 모양의 이미지를 클릭하여 확인 할 수 있습니다. \n 알람에 대한 설정은 \"좌측 상단의 슬라이드 메뉴\" → \"설정\" → \"알람 설정\"에서 변경이 가능합니다.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("관심 알림");
            arrayList2.add(" 누군가 회원님의 재능에 관심을 보냈을 때 Talented에서 푸시 메시지를 보냅니다. 관심에 대한 푸시 메시지를 클릭하면 관심 목록 화면으로 이동합니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("재능 상태 알림");
            arrayList3.add(" 회원님의 재능 상태가 변할 때 Talented에서 푸시 메시지를 보냅니다. 재능 상태에 대한 푸시 메시지를 클릭하면 나의 재능 현황 화면으로 이동합니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("관심 취소 / 진행 취소 알림");
            arrayList4.add(" 회원님이 주고 받은 관심 취소 또는 진행 중이던 재능이 취소 되었을 때 Talented에서 푸시 메시지를 보냅니다. 취소에 대한 푸시 메시지를 클릭하면 해당 회원의 프로필이 팝업 됩니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

            ArrayList<String> arrayList5 = new ArrayList<String>();
            arrayList_Parent.add("1:1 문의하기 답변 완료 알림");
            arrayList5.add(" 회원님이 1:1 문의하신 내용에 대한 답변이 완료 되면 Talented에서 푸시 메시지를 보냅니다. 답변 완료에 대한 푸시 메시지를 클릭하면 문의내역 화면으로 이동합니다.");
            arrayList_Child.put(arrayList_Parent.get(4),arrayList5);

            ArrayList<String> arrayList6 = new ArrayList<String>();
            arrayList_Parent.add("신고하기 답변 완료 알림");
            arrayList6.add(" 회원님이 신고하신 내역에 대한 조치가 완료되면 Talented에서 푸시 메시지를 보냅니다. 조치 완료에 대한 푸시 메시지를 클릭하면 신고내역 화면으로 이동합니다.");
            arrayList_Child.put(arrayList_Parent.get(5),arrayList6);

            ArrayList<String> arrayList7 = new ArrayList<String>();
            arrayList_Parent.add("메시지 알림");
            arrayList7.add(" 누군가 회원님에게 메시지를 보냈을 때 푸시 메시지가 전송 됩니다. 메시지에 대한 푸시 메시지를 클릭하면 해당 회원과의 Message Box 화면으로 이동합니다.");
            arrayList_Child.put(arrayList_Parent.get(6),arrayList7);
        }

        else if(value.equals("Message"))
        {
            arrayList_Parent.add("Message");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("Message TextView");
            arrayList_Child.put(this.arrayList_Parent.get(0),arrayList);
        }

        else if(value.equals("FriendList"))
        {
            arrayList_Parent.add("친구 목록");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(" 지속적으로 재능을 공유하고 싶은 회원들을 친구로 등록할 수 있습니다. 재능 공유 가능성이 있는 회원들을 친구로 등록하여 관리하세요.");
            arrayList_Child.put(this.arrayList_Parent.get(0),arrayList);

            this.arrayList_Parent.add("친구 등록 / 삭제");
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList1.add(" 친구 등록은 회원들의 \"프로필 좌측 상단의 별\"을 활성화 시켜 가능합니다. 친구를 삭제하려면 프로필에서 활성화된 별을 눌러 비활성화 시키면 됩니다.\n 친구는 재능 드림, 관심 재능 친구로 나뉘며 친구 목록에서는 친구로 등록한 대상의 현재 재능 상태를 확인 할 수 있습니다.\n 회원님의 재능 상태가 \"대기 중\" → \"진행 중\"으로 변할 때 주고 받았던 관심들은 모두 관심 취소 처리가 되니, 재능 공유 가능성이 있는 회원들을 친구로 등록 하면 유용합니다.");
            arrayList_Child.put(this.arrayList_Parent.get(1),arrayList1);
        }

        else if(value.equals("SharingList"))
        {
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList_Parent.add("재능공유·관심 내역");
            arrayList1.add(" 회원님이 진행했던 재능 공유 내역을 확인 할 수 있습니다. 진행 중인 재능, 진행 취소, 공유 완료된 내역을 확인할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList_Parent.add("Profile 보기");
            arrayList2.add(" 과거에 재능 공유를 진행했던 상대, 완료했던 상대의 프로필을 확인하여 관심을 보내고 메시지를 보낼 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

            ArrayList<String> arrayList3 = new ArrayList<String>();
            arrayList_Parent.add("신고 하기");
            arrayList3.add(" 공유 내역을 통해 회원님께 비합리적인 행동을 한 상대를 신고할 수 있습니다. 신고하기에 대한 자세한 사항은 \"Talented 사용하기\" → \"고객센터\" → \"신고하기\"에서 확인할 수 있습니다.");
            arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

            ArrayList<String> arrayList4 = new ArrayList<String>();
            arrayList_Parent.add("내역 삭제");
            arrayList4.add(" 지난 공유 내역은 삭제할 수 있습니다. 지난 공유 내역은 차후 사용 될 가능성(신고 하기, 재능 공유 등)이 있기 때문에 내역 삭제는 하지 않는 것을 권유합니다.");
            arrayList_Child.put(arrayList_Parent.get(3),arrayList4);

        }

        else if(value.equals("InterestingList"))
        {
            arrayList_Parent.add("관심 목록");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(" 회원님이 주고 받은 관심을 확인할 수 있습니다. 재능 공유의 첫 번째 단계입니다.");
            arrayList_Child.put(this.arrayList_Parent.get(0),arrayList);

            this.arrayList_Parent.add("관심 목록 보기");
            ArrayList<String> arrayList1 = new ArrayList<String>();
            arrayList1.add(" 재능 상태가 대기 중인 경우에 \"좌측 상단의 슬라이드 메뉴\" → \"나의 재능 현황\" → \"관심 목록 보기\"에서 확인 할 수 있습니다. 회원님의 재능 상태가 \"대기 중\" → \"진행 중\"으로 변할 때 주고 받았던 관심들은 모두 관심 취소가 되니, 재능 공유 가능성이 있는 회원들을 친구로 등록하세요.");
            arrayList_Child.put(this.arrayList_Parent.get(1),arrayList1);

            this.arrayList_Parent.add("Profile 보기");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList2.add(" 관심을 주고 받은 상대의 리스트를 클릭하면 상대의 프로필이 팝업됩니다. 재능 공유를 진행하기 위해서는 관심 목록에서 \"진행 또는 취소\" → \"진행 하기\"를 통해 재능 공유가 진행 될 수 있습니다. 반대로 회원님께 관심을 보낸 대상이 마음에 들지 않는다면 \"진행 또는 취소\" → \"취소 하기\"를 통해 관심 취소가 가능합니다.");
            arrayList_Child.put(this.arrayList_Parent.get(2),arrayList2);
        }
    }

}



