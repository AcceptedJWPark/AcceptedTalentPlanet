package com.example.accepted.acceptedtalentplanet.TalentSearching;

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
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_ListItem;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Popup_Activity;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSearching_ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<TalentSearching_ListItem> list_ArrayList;
    ImageView listView_picture;
    TextView listView_name;
    TextView listView_talent1;
    TextView listView_talent2;
    TextView listView_talent3;
    Button listView_showProfile;

    public TalentSearching_ListAdapter(Context context, ArrayList<TalentSearching_ListItem> list_ArrayList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.talentsearching_listviewbg, null);

            listView_picture = view.findViewById(R.id.TalentSearching_Picture);
            listView_name = view.findViewById(R.id.TalentSearching_Name);
            listView_talent1 = view.findViewById(R.id.TalentSearching_Keyword1);
            listView_talent2 = view.findViewById(R.id.TalentSearching_Keyword2);
            listView_talent3 = view.findViewById(R.id.TalentSearching_Keyword3);
            listView_showProfile = view.findViewById(R.id.TalentSearching_ShowProfile);

            final String text = String.valueOf(list_ArrayList.get(position));


        }

        listView_picture.setImageResource(list_ArrayList.get(position).getPicture());
        listView_name.setText(list_ArrayList.get(position).getName());
        listView_talent1.setText(list_ArrayList.get(position).getTalent1());
        listView_talent2.setText(list_ArrayList.get(position).getTalent2());
        listView_talent3.setText(list_ArrayList.get(position).getTalent3());

        return view;
    }

}
