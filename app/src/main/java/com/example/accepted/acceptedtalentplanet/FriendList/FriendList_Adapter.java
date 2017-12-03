package com.example.accepted.acceptedtalentplanet.FriendList;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class FriendList_Adapter extends BaseAdapter{

    Context context;

    ArrayList<FriendList_Item> list_ArrayList;

    ImageView FriendList_Picture;
    TextView FriendList_ConditionType;
    TextView FriendList_Name;
    TextView FriendList_Keyword1;
    TextView FriendList_Keyword2;
    TextView FriendList_Keyword3;


    public FriendList_Adapter(Context context, ArrayList<FriendList_Item> list_ArrayList) {
        this.context = context;
        this.list_ArrayList = list_ArrayList;
    }



    @Override
    public int getCount() {
        return list_ArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.friendlist_listviewbg, viewGroup, false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels * 0.10);
            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

        FriendList_Picture = view.findViewById(R.id.FriendList_Picture);
        FriendList_Name = view.findViewById(R.id.Friendlist_Name);
        FriendList_Keyword1 = view.findViewById(R.id.FriendList_Keyword1);
        FriendList_Keyword2 = view.findViewById(R.id.FriendList_Keyword2);
        FriendList_Keyword3 = view.findViewById(R.id.FriendList_Keyword3);
        FriendList_ConditionType = view.findViewById(R.id.Friendlist_TalentCondition);


    }
            FriendList_Picture.setImageResource(list_ArrayList.get(position).getpicture());
            FriendList_Name.setText(list_ArrayList.get(position).getname());
            FriendList_Keyword1.setText(list_ArrayList.get(position).getKeyword1());
            FriendList_Keyword2.setText(list_ArrayList.get(position).getKeyword2());
            FriendList_Keyword3.setText(list_ArrayList.get(position).getKeyword3());

            if(list_ArrayList.get(position).getTalentType_CODE()==1) {
                switch (list_ArrayList.get(position).getTalentConditionType_CODE()) {
                    case 1: {
                        FriendList_ConditionType.setText("님의 관심 재능은 대기 중입니다.");
                        break;
                    }
                    case 2: {
                        FriendList_ConditionType.setText("님의 관심 재능은 진행 중입니다.");
                        break;
                    }

                }
            }
            else
            {
                switch (list_ArrayList.get(position).getTalentConditionType_CODE()) {
                    case 1: {
                        FriendList_ConditionType.setText("님의 재능 드림은 대기 중입니다.");
                        break;
                    }
                    case 2: {
                        FriendList_ConditionType.setText("님의 재능 드림은 진행 중입니다.");
                        break;
                    }

                }
            }
            return view;
        }

    }