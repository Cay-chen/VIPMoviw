package com.example.cay.vipmoviw.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.cay.vipmoviw.MainActivity;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.adapter.EveryDayAdapter;
import com.example.cay.vipmoviw.base.GlideImageLoader;
import com.example.cay.vipmoviw.base.adapter.BaseFragment;
import com.example.cay.vipmoviw.base.adapter.MultipleItem;
import com.example.cay.vipmoviw.bean.FirstRxDataBean;
import com.example.cay.vipmoviw.bean.MovieDataBean;
import com.example.cay.vipmoviw.data.Moni;
import com.example.cay.vipmoviw.databinding.FooterItemEverydayBinding;
import com.example.cay.vipmoviw.databinding.FragmentEverydayBinding;
import com.example.cay.vipmoviw.databinding.HeaderItemEverydayBinding;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 每日推荐
 * 更新逻辑：判断是否是第二天(相对于2016-11-26)
 * 是：判断是否是大于12：30
 * *****     |是：刷新当天数据
 * *****     |否：使用缓存：|无：请求前一天数据
 * **********             |有：使用缓存
 * 否：使用缓存 ： |无：请求今天数据
 * **********    |有：使用缓存
 */
public class EverydayFragment extends BaseFragment<FragmentEverydayBinding> {

    private static final String TAG = "EverydayFragment";
    private RecyclerView mRecyclerView;
    private ArrayList<String> mBannerImages;
    private HeaderItemEverydayBinding mHeaderBinding;
    private FooterItemEverydayBinding mFooterBinding;

    private View mHeaderView;
    private View mFooterView;
    private boolean mIsPrepared = false;
    private boolean mIsFirst = true;
    // 是否正在刷新（避免重复刷新）
    private boolean mIsLoading = false;
    // 是否是上一天的请求
    private boolean isOldDayRequest;
    private RotateAnimation animation;
    private EveryDayAdapter mEveryDayAdapter;
    private MainActivity activity;
    private Banner mBanner;
    private List<String> image_url;
    private List<String> titles;
    private List<FirstRxDataBean> mList;
    private List<MultipleItem> mmList;

    @Override
    public int setContent() {
        return R.layout.fragment_everyday;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        showLoading();
        showContentView();
        bindingView.llLoading.setVisibility(View.VISIBLE);
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());//不停顿
        animation.setRepeatCount(10);
        bindingView.ivLoading.setAnimation(animation);
        animation.startNow();
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_everyday, null, false);
        mFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.footer_item_everyday, null, false);
        mHeaderView = mHeaderBinding.getRoot();
        mFooterView = mFooterBinding.getRoot();
        initRecyulerView();
        initFirstData();

        initData();
        initBanner();

    }

    private void initRecyulerView() {
        mRecyclerView = bindingView.xrvEveryday;
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
// 需加，不然滑动不流畅
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
    }

    private void initFirstData() {
        OkHttpUtils.get().url("http://192.168.0.227:8080/VMovie/FirstRxDataServer").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                initFirstData();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG, "onResponse: " + response);
                mList = JSON.parseArray(response, FirstRxDataBean.class);
                mmList = new ArrayList<MultipleItem>();
                for (int i = 0; i < mList.size(); i++) {
                    FirstRxDataBean bean = mList.get(i);
                    mmList.add(new MultipleItem(bean.getType(), bean.getImg1(), bean.getMid1(), bean.getCon1(), bean.getImg2(), bean.getMid2(), bean.getCon2(), bean.getImg3(), bean.getMid3(), bean.getCon3(), bean.getG_type(), bean.getTitle(), bean.getIsTitle()));
                }
                mEveryDayAdapter = new EveryDayAdapter(getContext(), mmList);
                mEveryDayAdapter.addHeaderView(mHeaderView);
                mEveryDayAdapter.addFooterView(mFooterView);
                mRecyclerView.setAdapter(mEveryDayAdapter);
                mRecyclerView.setVisibility(View.VISIBLE);
                bindingView.llLoading.setVisibility(View.GONE);
            }
        });


    }

    private void initData() {
        image_url = new ArrayList<>();
        image_url.add("http://pic2.ooopic.com/12/32/19/90bOOOPIC39_1024.jpg");
        image_url.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486551464991&di=e9df5e9744a147c18a66118533e7be0a&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F120302%2F6444-12030215322171.jpg");
        image_url.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486551464990&di=9690558e0ca491a797d09882074158b6&imgtype=0&src=http%3A%2F%2Fwww.llmlj.com%2Fbook_cover%2F274231.jpg");
        image_url.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486551464988&di=ce3e093ffd678b1c70211a0cbe5c1649&imgtype=0&src=http%3A%2F%2Fuserimage7.360doc.com%2F16%2F0126%2F09%2F325810_201601260901140656803976.jpg");
        titles = new ArrayList<>();
        titles.add("迪克特·康伯巴奇");
        titles.add("打垮夏洛克迫使他跳楼");
        titles.add("《恶魔少爷别吻我》");
        titles.add("航海王们为了");
    }

    private void initBanner() {
        mBanner = mHeaderBinding.banner;
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(image_url);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), "aaaa" + position, Toast.LENGTH_LONG).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

    }


}
