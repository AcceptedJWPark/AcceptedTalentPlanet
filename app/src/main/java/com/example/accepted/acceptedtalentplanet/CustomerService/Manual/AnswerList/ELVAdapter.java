package com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ELVAdapter extends BaseExpandableListAdapter {


    private Context context;
    private ArrayList<String> arrayParent;
    private HashMap <String, ArrayList<String>> arrayChild;

    public ELVAdapter(Context context, ArrayList<String> arrayParent, HashMap<String,ArrayList<String>> arrayChild)
    {
        super();
        this.context = context;
        this.arrayParent = arrayParent;
        this.arrayChild = arrayChild;
    }


    @Override
    public int getGroupCount() {
        return arrayParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayChild.get(arrayParent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayParent.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupName = arrayParent.get(groupPosition);
        View v = convertView;

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_manual_parentbg, parent,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);
            ViewGroup.LayoutParams params1 = v.getLayoutParams();
            params1.height = Interesting_ListView_height;
            v.setLayoutParams(params1);


        }
        TextView tv_Parent = (TextView) v.findViewById(R.id.tv_Parent_Manual);
        tv_Parent.setText(groupName);
        return v;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childName = arrayChild.get(arrayParent.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_manual_childbg, null);
        }
        TextView tv_Child = (TextView) v.findViewById(R.id.tv_Child_Manual);
        tv_Child.setText(childName);
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}




