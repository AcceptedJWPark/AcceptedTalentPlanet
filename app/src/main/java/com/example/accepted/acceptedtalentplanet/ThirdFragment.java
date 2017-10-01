package com.example.accepted.acceptedtalentplanet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
/**
 * Created by Accepted on 2017-09-17.
 */

public class ThirdFragment extends Fragment {


    public ThirdFragment()
    {}

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_thirdpage,container, false);
        return layout;

    }





    }


