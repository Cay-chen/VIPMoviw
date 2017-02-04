package com.example.cay.vipmoviw.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cay.vipmoviw.MainActivity;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.adapter.OneAdapter;
import com.example.cay.vipmoviw.base.adapter.BaseFragment;
import com.example.cay.vipmoviw.data.Moni;
import com.example.cay.vipmoviw.databinding.FragmentOneBinding;

public class OneFragment extends BaseFragment<FragmentOneBinding> {

    // 初始化完成后加载数据
    private boolean isPrepared = false;
    // 第一次显示时加载数据，第二次不显示
    private boolean isFirst = true;
    // 是否正在刷新（用于刷新数据时返回页面不再刷新）
    private boolean mIsLoading = false;
   // private ACache aCache;
    private MainActivity activity;
  //  private HotMovieBean mHotMovieBean;
    private View mHeaderView = null;
    private OneAdapter oneAdapter;
    private RecyclerView mRecyclerView;


    @Override
    public int setContent() {
        return R.layout.fragment_one;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        mRecyclerView = bindingView.listOne;
    //    aCache = ACache.get(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
       oneAdapter = new OneAdapter(R.layout.item_one1,Moni.hotMovieData(),activity);
     //   mHotMovieBean = (HotMovieBean) aCache.getAsObject(Constants.ONE_HOT_MOVIE);
        mRecyclerView.setAdapter(oneAdapter);
        isPrepared = true;
   //     DebugUtil.error("---OneFragment   --onActivityCreated");
    }


}
