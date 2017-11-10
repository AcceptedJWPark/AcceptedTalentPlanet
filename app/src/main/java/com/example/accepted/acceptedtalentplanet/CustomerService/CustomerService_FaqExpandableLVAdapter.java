package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-11-02.
 */

public class CustomerService_FaqExpandableLVAdapter extends BaseExpandableListAdapter{


        private Context context;
        private ArrayList<String> arrayGroup;
        private HashMap<String, ArrayList<String>> arrayChild;

        public CustomerService_FaqExpandableLVAdapter(Context context, ArrayList<String> arrayGroup, HashMap<String,ArrayList<String>> arrayChild)
        {
            super();
            this.context = context;
            this.arrayGroup = arrayGroup;
            this.arrayChild = arrayChild;
        }


        @Override
        public int getGroupCount() {
            return arrayGroup.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return arrayChild.get(arrayGroup.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return arrayGroup.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
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
            String groupName = arrayGroup.get(groupPosition);
            View v = convertView;

            if(v==null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v=(LinearLayout) inflater.inflate(R.layout.customerservice_faq_parentbg, null);

            }
            TextView textGroup = (TextView) v.findViewById(R.id.CustomerService_FAQparent_text);
            textGroup.setText(groupName);
            return v;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String childName = arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
            View v = convertView;

            if(v==null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v=(LinearLayout) inflater.inflate(R.layout.customerservice_faq_childbg, null);
            }
            TextView textChild = (TextView) v.findViewById(R.id.CustomerService_FAQchild_text);
            textChild.setText(childName);
            return v;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }


