package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-30.
 */

public class DialogActivity extends Dialog{

    private TextView tv_DialogTxt;
    private TextView tv_DialogNext;
    private TextView tv_DialogCancel;

    private String mContentTxt;



    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.interestinglist_dialog);

        tv_DialogTxt = findViewById(R.id.tv_DialogTxt_InterestingList);
        tv_DialogNext = findViewById(R.id.tv_DialogNext_InterestingList);
        tv_DialogCancel = findViewById(R.id.tv_DialogCancel_InterestingList);
        tv_DialogTxt.setText(mContentTxt);

        if (mLeftClickListener != null && mRightClickListener != null) {
            tv_DialogNext.setOnClickListener(mLeftClickListener);
            tv_DialogCancel.setOnClickListener(mRightClickListener);

        } else if (mLeftClickListener != null
                && mRightClickListener == null) {

            tv_DialogNext.setOnClickListener(mLeftClickListener);

        } else {


        }

    }

    public DialogActivity(Context context, String content,
                          View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContentTxt = content;
        this.mLeftClickListener = singleListener;
    }



    public DialogActivity(Context context,
                          String content, View.OnClickListener leftListener,
                          View.OnClickListener rightListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContentTxt = content;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;

    }


}

