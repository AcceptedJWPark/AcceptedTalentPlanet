package com.example.accepted.acceptedtalentplanet.Messanger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

public class Messanger_List extends AppCompatActivity {

    Context mContext;
    ArrayList<Messanger_List_Item> messanger_Arraylist;
    Messanger_List_Adapter messanger_ArrayAdapter;
    ListView messanger_Listview;

    LinearLayout MessangerList_Prebtn;
    LinearLayout MessangerList_Deletebtn;

    boolean deleteBtn_Clicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messanger_list);

        mContext = getApplicationContext();
        messanger_Arraylist = new ArrayList<>();
        messanger_ArrayAdapter = new Messanger_List_Adapter(messanger_Arraylist, Messanger_List.this);

        messanger_Listview = (ListView) findViewById(R.id.Messanger_List_ListView);
        messanger_Listview.setAdapter(messanger_ArrayAdapter);
        MessangerList_Deletebtn = (LinearLayout) findViewById(R.id.MessangerList_DeleteBtn);
        MessangerList_Prebtn = (LinearLayout) findViewById(R.id.MessangerList_PreBtn);
        MessangerList_Prebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn_Clicked = false;
        MessangerList_Deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!deleteBtn_Clicked)
                {
                    deleteBtn_Clicked = true;
                    messanger_Listview.setAdapter(messanger_ArrayAdapter);
                    messanger_ArrayAdapter.switchFlag_deleteBtn(true);
                    messanger_ArrayAdapter.notifyDataSetChanged();
                }
                else
                {
                    deleteBtn_Clicked = false;
                    messanger_Listview.setAdapter(messanger_ArrayAdapter);
                    messanger_ArrayAdapter.switchFlag_deleteBtn(false);
                    messanger_ArrayAdapter.notifyDataSetChanged();
                }
            }
        });

        messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture,"박종우", "안녕하세요 박종우 입니다.","PM 16:12", 3,false));
        messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture,"민권홍", "안녕하세요 민권홍 입니다.","18/Mar/05", 2,false));
        messanger_Arraylist.add(new Messanger_List_Item(R.drawable.testpicture,"김용인", "안녕하세요 김용인 입니다.","18/Mar/06", 0,false));


    }




}
