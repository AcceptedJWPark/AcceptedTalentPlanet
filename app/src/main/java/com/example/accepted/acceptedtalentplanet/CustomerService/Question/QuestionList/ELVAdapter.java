package com.example.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList;

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

/**
 * Created by Accepted on 2017-10-31.
 */

public class ELVAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<ListItem_Question> arrayList_Parent;
    private ArrayList<ArrayList<ListItem_Answer>> arrayList_Child;

    public ELVAdapter(Context mContext, ArrayList<ListItem_Question> arrayList_Parent, ArrayList<ArrayList<ListItem_Answer>> arrayList_Child)
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
        return arrayList_Child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayList_Parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayList_Child.get(groupPosition).get(childPosition);
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

        String title = arrayList_Parent.get(groupPosition).getTitle();
        String date = arrayList_Parent.get(groupPosition).getDate();
        String isAnswer = arrayList_Parent.get(groupPosition).getIsAnswer();

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_question_parentbg, parent,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);
            ViewGroup.LayoutParams params1 = v.getLayoutParams();
            params1.height = Interesting_ListView_height;
            v.setLayoutParams(params1);

        }
        TextView tv_ParentTitle = (TextView) v.findViewById(R.id.tv_ParentTitle_QuestionList);
        TextView tv_ParentDate = (TextView) v.findViewById(R.id.tv_ParentDate_QuestionList);
        TextView tv_ParentisAnswer = (TextView) v.findViewById(R.id.tv_ParentisAnswer_QuestionList);
        tv_ParentTitle.setText(title);
        tv_ParentDate.setText(date);
        tv_ParentisAnswer.setText(isAnswer);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        String question = arrayList_Child.get(groupPosition).get(childPosition).getQuestion();
        String answer = arrayList_Child.get(groupPosition).get(childPosition).getAnswer();

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_question_childbg, null);
        }
        TextView tv_ChildQuestion = (TextView) v.findViewById(R.id.tv_ChildQuestion_QuestionList);
        TextView tv_ChildAnswer = (TextView) v.findViewById(R.id.tv_ChildAnswer_QuestionList);
        tv_ChildQuestion.setText(question);
        tv_ChildAnswer.setText(answer);
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

