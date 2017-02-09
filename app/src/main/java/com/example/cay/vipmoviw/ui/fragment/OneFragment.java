package com.example.cay.vipmoviw.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.cay.vipmoviw.MainActivity;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.adapter.OneAdapter;
import com.example.cay.vipmoviw.base.adapter.BaseFragment;
import com.example.cay.vipmoviw.bean.MovieDataBean;
import com.example.cay.vipmoviw.databinding.FragmentOneBinding;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class OneFragment extends BaseFragment<FragmentOneBinding> implements BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener {
private String getDataUri = "http://192.168.0.227:8080/VMovie/Data";
    // 初始化完成后加载数据
    private boolean isPrepared = false;
    // 第一次显示时加载数据，第二次不显示
    private boolean isFirst = true;
    // 是否正在刷新（用于刷新数据时返回页面不再刷新）
    private boolean mIsLoading = false;
   // private ACache aCache;
    private MainActivity activity;
    //  private HotMovieBean mHotMovieBean;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OneAdapter oneAdapter;
    private RecyclerView mRecyclerView;
    private List<MovieDataBean> mList;

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
        mSwipeRefreshLayout = bindingView.swipeLayout;
        mSwipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initLoadData();
    }

    /**
     * 第一次加载数据
     */
    public void initLoadData() {
        OkHttpUtils.get().url(getDataUri).addParams("position","0").addParams("num","10").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                initLoadData();
            }

            @Override
            public void onResponse(String response, int id) {
                mList = JSON.parseArray(response, MovieDataBean.class);
                initAdapter(mList);
                showContentView();
            }
        });
    }
    private void initAdapter(List<MovieDataBean> data) {
        oneAdapter = new OneAdapter(R.layout.item_one1,data,activity);
        oneAdapter.setOnLoadMoreListener(this);
      //  oneAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(oneAdapter);
        addHeadView();
        if (data.size() < 10) {
            oneAdapter.loadMoreEnd(true);
        }

    }
    private void addHeadView() {
        View headView = activity.getLayoutInflater().inflate(R.layout.header_item_one, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.header_title)).setText("所有电影");

        oneAdapter.addHeaderView(headView);
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.get().url(getDataUri).addParams("position", String.valueOf(oneAdapter.getData().size())).addParams("num","10").build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        oneAdapter.loadMoreFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        List<MovieDataBean> list = JSON.parseArray(response, MovieDataBean.class);
                        oneAdapter.addData(list);
                        oneAdapter.loadMoreComplete();
                        if (list.size() < 5) {oneAdapter.loadMoreEnd(false);
                        }
                    }
                });
            }
        },1000);

    }

    @Override
    public void onRefresh() {
        oneAdapter.setEnableLoadMore(false);
        OkHttpUtils.get().url(getDataUri).addParams("position","0").addParams("num","10").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(activity,"刷新失败",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResponse(String response, int id) {
                mList = JSON.parseArray(response, MovieDataBean.class);
                oneAdapter.setNewData(mList);
                mSwipeRefreshLayout.setRefreshing(false);
                oneAdapter.setEnableLoadMore(true);
            }
        });

    }
}
