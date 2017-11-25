package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
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
 * Created by Accepted on 2017-11-23.
 */

public class TalentResister_Location_Adapter extends BaseAdapter {

    Context context;
    private ArrayList<String> arrayList;

    public TalentResister_Location_Adapter(Context context, ArrayList<String> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.talentresister_location_listviewbg, null);
        }
        TextView locationTxt = (TextView) convertView.findViewById(R.id.LocationList_Text);
        locationTxt.setText(arrayList.get(position));

        ImageView listView_deleteImg = convertView.findViewById(R.id.LocationList_Delete);
        listView_deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
