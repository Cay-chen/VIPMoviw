package com.example.cay.vipmoviw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.adapter.MovieCountItemAdapter;
import com.example.cay.vipmoviw.base.BaseHeaderActivity;
import com.example.cay.vipmoviw.bean.MovieBean;
import com.example.cay.vipmoviw.bean.MovieDataBean;

import com.example.cay.vipmoviw.data.Utils;
import com.example.cay.vipmoviw.databinding.ActivityOneMovieDetailBinding;
import com.example.cay.vipmoviw.databinding.HeaderSlideShapeBinding;
import com.example.cay.vipmoviw.utils.CommonUtils;
import com.example.cay.vipmoviw.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 继承基类而写的电影详情页 2016-12-13
 */
public class OneMovieDetailActivity extends BaseHeaderActivity<HeaderSlideShapeBinding, ActivityOneMovieDetailBinding> {
    private static final String TAG = "Cay";
    private MovieDataBean subjectsBean;
    private String mMoreUrl;
    private String mMovieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_movie_detail);
        if (getIntent() != null) {
            subjectsBean = (MovieDataBean) getIntent().getSerializableExtra("bean");
        }
        Log.i(TAG, "subjectsBean: " + subjectsBean.getName());
        showContentView();
        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView());

        setTitle(subjectsBean.getName());
        setSubTitle(String.format("主演：%s", subjectsBean.getAct()));
        bindingHeaderView.executePendingBindings();
        Glide.with(this).load(subjectsBean.getImg_url()).bitmapTransform(new BlurTransformation(this, 23, 4)).into(bindingHeaderView.imgItemBg);
        Glide.with(this).load(subjectsBean.getImg_url()).into(bindingHeaderView.ivOnePhoto);
        bindingHeaderView.tvOneRatingRate.setText(this.getResources().getString(R.string.string_rating) + subjectsBean.getCode());
        bindingHeaderView.tvOneCasts.setText(subjectsBean.getAct());
        bindingHeaderView.tvOneDirectors.setText(subjectsBean.getDirector());
        bindingHeaderView.tvOneDay.setText(this.getResources().getString(R.string.movie_year) + subjectsBean.getYear());
        bindingHeaderView.tvOneCity.setText(this.getResources().getString(R.string.movie_city) + Utils.country(Integer.parseInt(subjectsBean.getCity()),this) );
        bindingHeaderView.tvOneGenres.setText(this.getResources().getString(R.string.string_type) + subjectsBean.getMovie_type());
        bindingContentView.tvOneContiont.setText(subjectsBean.getLog());
        bindingContentView.tvOneTitle.setText(subjectsBean.getOther_name());
        setMovieCount();
    }

    private void setMovieCount() {
        List<MovieBean> mList = new ArrayList<>();
        if (subjectsBean.getType().equals("1")) {
            for (int i = 1; i <= Integer.parseInt(subjectsBean.getNum() ); i++) {
                MovieBean bean = new MovieBean();
                bean.setItemName("第" + String.valueOf(i) + "集");
                bean.setMovieUrl("http://192.168.0.229:8081/movie/"+subjectsBean.getMovie_url()+"/"+i+".mp4");
                bean.setMovieName(subjectsBean.getName() + " 第" + String.valueOf(i) + "集");
                mList.add(bean);
            }
        } else {
            MovieBean bean = new MovieBean();
            bean.setItemName("高清中字");
            bean.setMovieUrl("http://192.168.0.229:8081/movie/"+subjectsBean.getMovie_url()+".mp4");
            bean.setMovieName(subjectsBean.getName());
            mList.add(bean);
        }
        bindingContentView.rvCast.setLayoutManager(new GridLayoutManager(this, 4));
        bindingContentView.rvCast.setAdapter(new MovieCountItemAdapter(R.layout.movie_count_item, mList, this));
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
        return subjectsBean.getImg_url();
    }

    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }


    public static void start(Activity context, MovieDataBean positionData, ImageView imageView) {
        Intent intent = new Intent(context, OneMovieDetailActivity.class);
        intent.putExtra("bean", positionData);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, CommonUtils.getString(R.string.transition_movie_img));//与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

}
