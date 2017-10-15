package com.example.accepted.acceptedtalentplanet;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class NewTalent_ListView_Adapter extends RecyclerView.Adapter<NewTalent_ListView_Adapter.ViewHolder> {

    private ArrayList<NewTalent_ListItem> mItems;

    public NewTalent_ListView_Adapter(ArrayList<NewTalent_ListItem> items) {
        mItems = items;
    }


    @Override
    public NewTalent_ListView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtalent_listviewbg, parent, false);

        ViewHolder holder = new ViewHolder(v);
        Log.d("ViewHolder Called", String.valueOf(getItemCount()));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(mItems.get(position).image);
        holder.textName.setText(mItems.get(position).name);
        holder.textTalent.setText(mItems.get(position).talent);


  /*      GridLayoutManager.LayoutParams layoutParams =(GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        int viewWidth = layoutParams.width;*/
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView textName;
        public TextView textTalent;

        public ViewHolder(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.NewTalent_Picture);
            textName = view.findViewById(R.id.NewTalent_Name);
            textTalent = view.findViewById(R.id.NewTalent_Talent);
        }
    }

}