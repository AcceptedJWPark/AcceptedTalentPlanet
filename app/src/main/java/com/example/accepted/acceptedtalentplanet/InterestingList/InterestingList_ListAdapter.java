package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView listView_name;
    TextView listView_talent1;
    TextView listView_talent2;
    TextView listView_talent3;
    TextView listView_talentType;
    TextView Interesting_RegistDate;
    Button Interesting_List_ShowProfile;

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
            view = LayoutInflater.from(context).inflate(R.layout.interestinrlist_listviewbg, null);

            listView_picture = view.findViewById(R.id.Interesting_List_Picture);
            listView_name = view.findViewById(R.id.Interesting_List_Name);
            listView_talent1 = view.findViewById(R.id.Interesting_List_Keyword1);
            listView_talent2 = view.findViewById(R.id.Interesting_List_Keyword2);
            listView_talent3 = view.findViewById(R.id.Interesting_List_Keyword3);
            listView_talentType = view.findViewById(R.id.Interesting_TalentType);
            Interesting_RegistDate = view.findViewById(R.id.Interesting_RegistDate);
            Interesting_List_ShowProfile = view.findViewById(R.id.Interesting_List_ShowProfile);
            Interesting_List_ShowProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InterestingList_Popup.class);
                    context.startActivity(intent);

                }
            });

        }

        listView_picture.setImageResource(list_ArrayList.get(position).getPicture());
        listView_name.setText(list_ArrayList.get(position).getName());
        listView_talent1.setText(list_ArrayList.get(position).getTalent1());
        listView_talent2.setText(list_ArrayList.get(position).getTalent2());
        listView_talent3.setText(list_ArrayList.get(position).getTalent3());
        Interesting_RegistDate.setText(list_ArrayList.get(position).getregistDate());
        listView_talentType.setText(list_ArrayList.get(position).getTalentType());

        return view;
    }

}
