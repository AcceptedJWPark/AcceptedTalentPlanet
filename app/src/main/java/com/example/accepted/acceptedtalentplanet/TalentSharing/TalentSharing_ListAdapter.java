package com.example.accepted.acceptedtalentplanet.TalentSharing;

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

public class TalentSharing_ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<TalentSharing_ListItem> list_ArrayList;
    ImageView listView_picture;
    TextView listView_name;
    TextView listView_talent1;
    TextView listView_talent2;
    TextView listView_talent3;
    TextView listView_percentage;
    Button listView_showProfile;

    public TalentSharing_ListAdapter(Context context, ArrayList<TalentSharing_ListItem> list_ArrayList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.talentsharing_listviewbg, null);

            listView_picture = view.findViewById(R.id.TalentSharing_picture);
            listView_name = view.findViewById(R.id.TalentSharing_name);
            listView_talent1 = view.findViewById(R.id.TalentSharing_talent1);
            listView_talent2 = view.findViewById(R.id.TalentSharing_talent2);
            listView_talent3 = view.findViewById(R.id.TalentSharing_talent3);
            listView_percentage = view.findViewById(R.id.TalentSharing_percentage);
            listView_showProfile = view.findViewById(R.id.TalentSharing_showProfile);

            final String text = String.valueOf(list_ArrayList.get(position));

            listView_showProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TalentSharing_Popup.class);
                    context.startActivity(intent);
                }
            });
        }

        listView_picture.setImageResource(list_ArrayList.get(position).getPicture());
        listView_name.setText(list_ArrayList.get(position).getName());
        listView_talent1.setText(list_ArrayList.get(position).getTalent1());
        listView_talent2.setText(list_ArrayList.get(position).getTalent2());
        listView_talent3.setText(list_ArrayList.get(position).getTalent3());
        listView_percentage.setText(list_ArrayList.get(position).getPercentage());
        listView_showProfile.setText(list_ArrayList.get(position).getShowProfile());

        return view;
    }

}
