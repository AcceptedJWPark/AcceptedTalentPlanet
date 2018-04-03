package com.accepted.acceptedtalentplanet.CustomerService.Introduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-10-31.
 */

public class ViewPager1 extends Fragment {
    public ViewPager1()
    {
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout ll_Intro1Container = (LinearLayout) inflater.inflate(R.layout.customerservice_introduction1, container, false);
        return ll_Intro1Container;
    }
}
