package com.example.accepted.acceptedtalentplanet.CustomerService.Introduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ViewPager2 extends Fragment {
    public ViewPager2()
    {
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout ll_Intro2Container = (LinearLayout) inflater.inflate(R.layout.customerservice_introduction2, container, false);
        return ll_Intro2Container;
    }
}
