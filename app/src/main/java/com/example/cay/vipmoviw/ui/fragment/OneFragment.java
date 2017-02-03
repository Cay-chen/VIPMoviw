package com.example.cay.vipmoviw.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.base.adapter.BaseFragment;
import com.example.cay.vipmoviw.databinding.FragmentOneBinding;

/**
 * Created by Cay on 2017/2/3.
 */

public class OneFragment extends BaseFragment<FragmentOneBinding> {

    @Override
    public int setContent() {
        return  R.layout.fragment_one;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
    }

}
