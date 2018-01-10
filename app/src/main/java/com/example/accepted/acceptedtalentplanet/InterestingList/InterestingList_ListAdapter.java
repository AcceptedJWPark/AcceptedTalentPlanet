package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class InterestingList_ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<InterestingList_ListItem> list_ArrayList;
    ImageView listView_picture;
    ImageView Interesting_List_GiveTakeIcon;
    TextView listView_name;
    TextView listView_talent1;
    TextView listView_talent2;
    TextView listView_talent3;
    TextView Interesting_List_GiveTake;

    public InterestingList_ListAdapter(Context context, ArrayList<InterestingList_ListItem> list_ArrayList) {
        this.context = context;
        this.list_ArrayList = list_ArrayList;
    }


    @Override
    public int getCount() {
        return this.list_ArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_ArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int index = position;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.interestinglist_listviewbg, viewGroup,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            listView_picture = view.findViewById(R.id.Interesting_List_Picture);
            listView_name = view.findViewById(R.id.Interesting_List_Name);
            listView_talent1 = view.findViewById(R.id.Interesting_List_Keyword1);
            listView_talent2 = view.findViewById(R.id.Interesting_List_Keyword2);
            listView_talent3 = view.findViewById(R.id.Interesting_List_Keyword3);
            Interesting_List_GiveTakeIcon = view.findViewById(R.id.Interesting_List_GiveTakeIcon);

            Interesting_List_GiveTake = view.findViewById(R.id.Interesting_List_GiveTake);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InterestingList_Popup.class);
                    intent.putExtra("TalentID", ((InterestingList_ListItem)getItem(index)).getTalentID());
                    intent.putExtra("codeGiveTake", list_ArrayList.get(index).getGiveTake_CODE());
                    context.startActivity(intent);
                }
            });

        }


        listView_picture.setBackgroundResource(list_ArrayList.get(position).getPicture());
        listView_name.setText(list_ArrayList.get(position).getName());
        listView_talent1.setText(list_ArrayList.get(position).getTalent1());
        listView_talent2.setText(list_ArrayList.get(position).getTalent2());
        listView_talent3.setText(list_ArrayList.get(position).getTalent3());

        if (list_ArrayList.get(position).getGiveTake_CODE()==2)
        {Interesting_List_GiveTake.setText("보낸 관심");
            Interesting_List_GiveTakeIcon.setImageResource(R.drawable.icon_giveinter);
        }
        else  {Interesting_List_GiveTake.setText("받은 관심");
            Interesting_List_GiveTakeIcon.setImageResource(R.drawable.icon_takeinter);
        }

        return view;
    }

}
