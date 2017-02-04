package com.example.cay.vipmoviw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.base.BaseHeaderActivity;
import com.example.cay.vipmoviw.bean.SubjectsBean;
import com.example.cay.vipmoviw.databinding.ActivityOneMovieDetailBinding;
import com.example.cay.vipmoviw.databinding.HeaderSlideShapeBinding;
import com.example.cay.vipmoviw.utils.CommonUtils;
import com.example.cay.vipmoviw.webview.WebViewActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 继承基类而写的电影详情页 2016-12-13
 */
public class OneMovieDetailActivity extends BaseHeaderActivity<HeaderSlideShapeBinding, ActivityOneMovieDetailBinding> {
   private static final String TAG = "Cay";
    private SubjectsBean subjectsBean;
    private String mMoreUrl;
    private String mMovieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_movie_detail);
        if (getIntent() != null) {
            subjectsBean = (SubjectsBean) getIntent().getSerializableExtra("bean");
        }
       Log.i(TAG, "subjectsBean: "+subjectsBean.getmName());
        showContentView();
        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView());

        setTitle(subjectsBean.getmName());
        setSubTitle(String.format("主演：%s",subjectsBean.getAct()));
        bindingHeaderView.executePendingBindings();
       Glide.with(this).load(subjectsBean.getUrl()).bitmapTransform(new BlurTransformation(this,23,4)).into(bindingHeaderView.imgItemBg);
        Glide.with(this).load(subjectsBean.getUrl()).into(bindingHeaderView.ivOnePhoto);
        bindingHeaderView.tvOneRatingRate.setText(this.getResources().getString(R.string.string_rating)+subjectsBean.getScore());
        bindingHeaderView.tvOneCasts.setText(subjectsBean.getAct());
        bindingHeaderView.tvOneDirectors.setText(subjectsBean.getDirector());
        bindingHeaderView.tvOneDay.setText(this.getResources().getString(R.string.movie_year)+subjectsBean.getYear());
        bindingHeaderView.tvOneCity.setText(this.getResources().getString(R.string.movie_city)+subjectsBean.getCity());
        bindingHeaderView.tvOneGenres.setText(this.getResources().getString(R.string.string_type)+subjectsBean.getType());
        bindingContentView.tvOneContiont.setText(subjectsBean.getSynopsis());
    }

    @Override
    protected void setTitleClickMore() {
        WebViewActivity.loadUrl(OneMovieDetailActivity.this, mMoreUrl, mMovieName);
    }

    @Override
    protected int setHeaderLayout() {
        return R.layout.header_slide_shape;
    }

    @Override
    protected String setHeaderImgUrl() {
        if (subjectsBean == null) {
            return "";
        }
        Log.i(TAG, "setHeaderImgUrl: "+subjectsBean.getUrl());
        return subjectsBean.getUrl();
    }

    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }






    public static void start(Activity context, SubjectsBean positionData, ImageView imageView) {
        Intent intent = new Intent(context, OneMovieDetailActivity.class);
        intent.putExtra("bean",positionData);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, CommonUtils.getString(R.string.transition_movie_img));//与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

}
