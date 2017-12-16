package com.example.accepted.acceptedtalentplanet.SharingList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.List;

/**
 * Created by Accepted on 2017-12-15.
 */

public class SharingList_SpinnerAdapter extends ArrayAdapter<SpinnerItem>{

    public SharingList_SpinnerAdapter(@NonNull Context context, int resource, List<SpinnerItem> spinnerItem) {
        super(context, resource, spinnerItem);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            return initialSelection(true);
        }
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            return initialSelection(false);
        }
        return getCustomView(position, convertView, parent);
    }


    @Override
    public int getCount() {
        return super.getCount() + 1; //
    }

    private View initialSelection(boolean dropdown) {
        TextView view = new TextView(getContext());
        view.setPadding(0, 0, 0, 0);

        if (dropdown) {
            view.setHeight(0);
        }

        return view;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = convertView != null && !(convertView instanceof TextView)
                ? convertView :
                LayoutInflater.from(getContext()).inflate(R.layout.sharinglist_spinnertext, parent, false);

        position = position - 1;
        SpinnerItem spinnerItem = getItem(position);

        return row;
    }


}
