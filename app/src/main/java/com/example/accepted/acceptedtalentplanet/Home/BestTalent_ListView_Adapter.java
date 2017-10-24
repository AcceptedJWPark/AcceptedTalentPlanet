package com.example.accepted.acceptedtalentplanet.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class BestTalent_ListView_Adapter extends BaseAdapter{

    Context context;
    ArrayList<BestTalent_ListItem> list_ArrayList;
    ImageView listView_picture;
    TextView listView_rank;
    TextView listView_talent;
    TextView listView_name;
    TextView listView_bar;
    TextView listView_profile;
    TextView listView_category;
    TextView listView_number;

    public BestTalent_ListView_Adapter(Context context, ArrayList<BestTalent_ListItem> list_ArrayList) {
        this.context = context;
        this.list_ArrayList = list_ArrayList;
    }

    public void setItem(ArrayList<BestTalent_ListItem> list_ArrayList){
        this.list_ArrayList = list_ArrayList;
    }

    @Override
    public int getCount() {
        return list_ArrayList.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.home_besttalent_listviewbg, null);
        }
            listView_picture = view.findViewById(R.id.BestTalent_homeImage);
            listView_rank = view.findViewById(R.id.BestTalent_homeRank);
            listView_talent = view.findViewById(R.id.BestTalent_homeTalent);
            listView_name = view.findViewById(R.id.BestTalent_homeName);
            listView_category = view.findViewById(R.id.BestTalent_homeCategory);
            listView_number = view.findViewById(R.id.BestTalent_homeNumber);
            listView_profile = view.findViewById(R.id.BestTalent_homeProfile);
            listView_bar = view.findViewById(R.id.BestTalent_homeBar);

            listView_picture.setImageResource(list_ArrayList.get(position).getPicture());
            listView_rank.setText(String.valueOf(list_ArrayList.get(position).getRank()));
            listView_bar.setText(list_ArrayList.get(position).getBar());
            listView_profile.setText(list_ArrayList.get(position).getProfile());
            listView_name.setText(list_ArrayList.get(position).getName());
            listView_talent.setText(list_ArrayList.get(position).getTalent());
            listView_category.setText(list_ArrayList.get(position).getCategory());
            listView_number.setText(list_ArrayList.get(position).getNumber());

    return view;
    }

}
