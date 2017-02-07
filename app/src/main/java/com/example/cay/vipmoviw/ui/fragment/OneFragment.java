package com.example.cay.vipmoviw.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.cay.vipmoviw.MainActivity;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.adapter.OneAdapter;
import com.example.cay.vipmoviw.base.adapter.BaseFragment;
import com.example.cay.vipmoviw.bean.MovieDataBean;
import com.example.cay.vipmoviw.data.Moni;
import com.example.cay.vipmoviw.databinding.FragmentOneBinding;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

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
        mRecyclerView = bindingView.listOne;
    //    aCache = ACache.get(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        OkHttpUtils.get().url("http://60.205.183.88:8080/VMovie/Data").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                List<MovieDataBean> movieDataBeen = JSON.parseArray(response, MovieDataBean.class);
                setAdapter(movieDataBeen);
                showContentView();
            }
        });

    }

    public void setAdapter(List<MovieDataBean> data) {
        oneAdapter = new OneAdapter(R.layout.item_one1,data,activity);
        //   mHotMovieBean = (HotMovieBean) aCache.getAsObject(Constants.ONE_HOT_MOVIE);
        mRecyclerView.setAdapter(oneAdapter);
        isPrepared = true;
        oneAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },10000);
            }
        });
        //     DebugUtil.error("---OneFragment   --onActivityCreated");
    }

}
