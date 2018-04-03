package com.accepted.acceptedtalentplanet.CustomerService.Notice;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ELVAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<ListItem> arrayList_Parent;
    private HashMap<ListItem, ArrayList<String>> arrayList_Child;

    public ELVAdapter(Context mContext, ArrayList<ListItem> arrayList_Parent, HashMap<ListItem, ArrayList<String>> arrayList_Child)
    {
        super();
        this.mContext = mContext;
        this.arrayList_Parent = arrayList_Parent;
        this.arrayList_Child = arrayList_Child;
    }


    @Override
    public int getGroupCount() {
        return arrayList_Parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayList_Child.get(arrayList_Parent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayList_Parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayList_Child.get(arrayList_Parent.get(groupPosition)).get(childPosition);
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
        View v = convertView;

        String Title = arrayList_Parent.get(groupPosition).getTitle();
        String Summary = arrayList_Parent.get(groupPosition).getSummary();
        String Date = arrayList_Parent.get(groupPosition).getDate();

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_notice_parentbg, parent,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);
            ViewGroup.LayoutParams params1 = v.getLayoutParams();
            params1.height = Interesting_ListView_height;
            v.setLayoutParams(params1);

        }
        TextView tv_ParentTitle = (TextView) v.findViewById(R.id.tv_parentTitle_Notice);
        TextView tv_parentSummary = (TextView) v.findViewById(R.id.tv_parentSummary_Notice);
        TextView tv_parentDate = (TextView) v.findViewById(R.id.tv_parentDate_Notice);
        tv_ParentTitle.setText(Title);
        tv_parentSummary.setText(Summary);
        tv_parentDate.setText(Date);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childName = arrayList_Child.get(arrayList_Parent.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_notice_childbg, null);
        }
        TextView tv_Childe = (TextView) v.findViewById(R.id.tv_Childe_Notice);
        tv_Childe.setText(childName);
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

