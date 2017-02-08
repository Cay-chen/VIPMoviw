package com.example.cay.vipmoviw.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.base.adapter.BaseFragment;
import com.example.cay.vipmoviw.databinding.FragmentWelfareBinding;

/**
 * 福利
 */
public class WelfareFragment extends BaseFragment<FragmentWelfareBinding> {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public int setContent() {
        return R.layout.fragment_welfare;
    }


}
