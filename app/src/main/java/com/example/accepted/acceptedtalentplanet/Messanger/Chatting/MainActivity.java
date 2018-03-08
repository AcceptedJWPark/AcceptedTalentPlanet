package com.example.accepted.acceptedtalentplanet.Messanger.Chatting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-03-06.
 */

public class MainActivity extends AppCompatActivity {

    Context mContext;
    ListView messanger_Chatting_LV;
    Adapter messanger_chatting_adapter;
    ArrayList<ListItem> messanger_chatting_ArrayList;
    LinearLayout Messanger_Chatting_SendBtn;
    EditText Messanger_Chatting_EditTxt;

    LinearLayout Messanger_Chatting_EditTxtLL;

    boolean time_Changed;
    boolean date_Changed;
    int message_Type; // Send : 1, Get : 2
    boolean picture_Type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messanger_chatting);

        mContext = getApplicationContext();

        messanger_chatting_ArrayList = new ArrayList<>();
        messanger_chatting_adapter = new Adapter(messanger_chatting_ArrayList, mContext);

        messanger_Chatting_LV = (ListView) findViewById(R.id.Messanger_Chatting_ListView);

        Messanger_Chatting_SendBtn = (LinearLayout) findViewById(R.id.Messanger_Chatting_SendBtn);
        Messanger_Chatting_EditTxt = (EditText) findViewById(R.id.Messanger_Chatting_EditTxt);
        Messanger_Chatting_EditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });



        Messanger_Chatting_EditTxtLL = (LinearLayout) findViewById(R.id.Messanger_Chatting_EditTxtLL);



        Messanger_Chatting_SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                if (Messanger_Chatting_TimeChanged.isChecked()) {
//                    time_Changed = true;
//                } else {
//                    time_Changed = false;
//                }
//
//                if (Messanger_Chatting_DateChanged.isChecked()) {
//                    date_Changed = true;
//                } else {
//                    date_Changed = false;
//                }

                final String chattingEditTxt = Messanger_Chatting_EditTxt.getText().toString();
                Messanger_Chatting_EditTxt.setText("");

                if(!chattingEditTxt.equals(""))
                {
                int prevPosition = 0;
                if (messanger_chatting_ArrayList.size() == 0) {
                    messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", 1, false, true, false));
                    messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
                    messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount()-1);
                    messanger_chatting_adapter.notifyDataSetChanged();
                    return;
                } else if (messanger_chatting_ArrayList.size() > 0) {
                    prevPosition = messanger_chatting_ArrayList.size() - 1;
                    ListItem temp = messanger_chatting_ArrayList.get(prevPosition);
                    picture_Type = false;
                    message_Type = 1;

                    if(date_Changed)
                    {
                        time_Changed = true;
                    }else if (!time_Changed) {
                            messanger_chatting_ArrayList.set(prevPosition, temp);
                            if (messanger_chatting_ArrayList.get(prevPosition).getMessage_Type() == 2)
                            {
                                time_Changed = true;
                            }
                            else{
                                temp.setTime_Changed(false);
                                time_Changed = true;
                            }
                        }
                    }
                    messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", message_Type, picture_Type, time_Changed, date_Changed));
                    messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
                    messanger_chatting_adapter.notifyDataSetChanged();
                messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount()-1);
                Messanger_Chatting_EditTxt.setText("");
                }
                else
                {
                    return;
                }
                }
        });



//        Messanger_Chatting_GetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final String chattingEditTxt = Messanger_Chatting_EditTxt.getText().toString();
//                Messanger_Chatting_EditTxt.setText("");
//
//                message_Type = 2;
//                picture_Type = true;
//
//                if (Messanger_Chatting_TimeChanged.isChecked()) {
//                    time_Changed = true;
//                } else {
//                    time_Changed = false;
//                }
//
//
//                if (Messanger_Chatting_DateChanged.isChecked()) {
//                    date_Changed = true;
//                } else {
//                    date_Changed = false;
//                }
//
//                if(!chattingEditTxt.equals("")) {
//                    int prevPosition = 0;
//                    if (messanger_chatting_ArrayList.size() == 0) {
//                        messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", 2, true, true, false));
//                        messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
//                        messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount() - 1);
//                        return;
//                    } else if (messanger_chatting_ArrayList.size() > 0) {
//                        prevPosition = messanger_chatting_ArrayList.size() - 1;
//                        ListItem temp = messanger_chatting_ArrayList.get(prevPosition);
//                        picture_Type = true;
//                        message_Type = 2;
//                        Log.d("time_Changed1 = ", String.valueOf(time_Changed));
//
//                        if (date_Changed) {
//                            picture_Type = true;
//                            time_Changed = true;
//                        } else if (!time_Changed) {
//                            messanger_chatting_ArrayList.set(prevPosition, temp);
//                            if (messanger_chatting_ArrayList.get(prevPosition).getMessage_Type() == 1) {
//                                picture_Type = true;
//                                time_Changed = true;
//                            } else {
//                                time_Changed = true;
//                                picture_Type = false;
//                                temp.setTime_Changed(false);
//                            }
//                            Log.d("time_Changed2 = ", String.valueOf(time_Changed));
//                        } else {
//                            temp.setTime_Changed(true);
//                            messanger_chatting_ArrayList.set(prevPosition, temp);
//                            System.out.println(time_Changed);
//                            Log.d("time_Changed3 = ", String.valueOf(time_Changed));
//                        }
//                    }
//                    messanger_chatting_ArrayList.add(new ListItem(R.drawable.testpicture, chattingEditTxt, "1", message_Type, picture_Type, time_Changed, date_Changed));
//                    messanger_Chatting_LV.setAdapter(messanger_chatting_adapter);
//                    messanger_chatting_adapter.notifyDataSetChanged();
//                    messanger_Chatting_LV.setSelection(messanger_chatting_adapter.getCount() - 1);
//                }
//                else
//                {
//                    return;
//                }
//            }
//        });

    }


}
