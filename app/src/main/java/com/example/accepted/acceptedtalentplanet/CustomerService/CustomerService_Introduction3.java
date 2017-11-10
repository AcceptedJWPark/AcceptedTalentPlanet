package com.example.accepted.acceptedtalentplanet.CustomerService;

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

public class CustomerService_Introduction3 extends Fragment {
    public CustomerService_Introduction3()
    {
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout customerservice_introduction3 = (LinearLayout) inflater.inflate(R.layout.customerservice_introduction3, container, false);


        return customerservice_introduction3;
    }
}
