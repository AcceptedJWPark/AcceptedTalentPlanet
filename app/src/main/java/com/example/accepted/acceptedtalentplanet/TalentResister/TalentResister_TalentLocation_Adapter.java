package com.example.accepted.acceptedtalentplanet.TalentResister;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-11-26.
 */

public class TalentResister_TalentLocation_Adapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<String> items;
    private ArrayList<String> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();

    public TalentResister_TalentLocation_Adapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount(){
        return suggestions.size();
    }

    @Override
    public String getItem(int position){
        return suggestions.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.talentresister_location, parent, false);
            holder = new ViewHolder();
            holder.autoText = (TextView)convertView.findViewById(R.id.TalentResister_Location);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.autoText.setText(suggestions.get(position));

        return convertView;
    }


    @Override
    public Filter getFilter(){
        return filter;
    }

    private static class ViewHolder{
        TextView autoText;
    }


    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            suggestions.clear();
            if(items != null && constraint != null){
                for(int i = 0; i < items.size(); i++){
                    if(items.get(i).contains(constraint)){
                        suggestions.add(items.get(i));
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            if(results.count > 0){
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }
    }
}
