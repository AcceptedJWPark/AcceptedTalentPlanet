package com.example.accepted.acceptedtalentplanet.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class HotTalent_ListView_Adapter extends BaseAdapter{

        Context context;
        ArrayList<HotTalent_ListItem> list_ArrayList;
    TextView listView_rank;
    TextView listView_talent;
    TextView listView_category;
    TextView listView_number;

    public HotTalent_ListView_Adapter(Context context, ArrayList<HotTalent_ListItem> list_ArrayList) {
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
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.home_hottalent_listviewbg, null);

            listView_rank = view.findViewById(R.id.listView_Rank);
            listView_talent = view.findViewById(R.id.listView_Talent);
            listView_category = view.findViewById(R.id.listView_Category);
            listView_number = view.findViewById(R.id.listView_Number);
        }

            listView_rank.setText(list_ArrayList.get(position).getRank());
            listView_talent.setText(list_ArrayList.get(position).getTalent());
            listView_category.setText(list_ArrayList.get(position).getCategory());
            listView_number.setText(list_ArrayList.get(position).getNumber());


    return view;
    }

}
