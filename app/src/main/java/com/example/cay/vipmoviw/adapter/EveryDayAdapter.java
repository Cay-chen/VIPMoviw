package com.example.cay.vipmoviw.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.activity.OneMovieDetailActivity;
import com.example.cay.vipmoviw.base.adapter.MultipleItem;

import java.util.List;

/**
 * Created by Cay on 2017/2/8.
 */

public class EveryDayAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private Context context;
    private ImageView mImageView;
    private View mView;
    private TextView textView;
    private ImageView mImageViewTitle;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public EveryDayAdapter(Context context, List<MultipleItem> data) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.TRE, R.layout.item_everyday_three);
        addItemType(MultipleItem.TWO, R.layout.item_everyday_two);
        addItemType(MultipleItem.ONE, R.layout.item_everyday_one);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        Log.i("TAG", "convert: " + helper.getItemViewType());
        switch (helper.getItemViewType()) {
            case MultipleItem.ONE:
                helper.setVisible(R.id.head_one, item.getIsTitle());
                mView = helper.getView(R.id.head_one);
                headerSetting(item);
                mImageView = helper.getView(R.id.iv_one_photo);
                Glide.with(context).load(item.getImg1()).into(mImageView);
                itemOnclick(mImageView,item.getMid1(),item.getImg1(),item.getG_type());
                helper.setText(R.id.tv_one_photo_title, item.getCon1());
                break;
            case MultipleItem.TWO:
                helper.setVisible(R.id.head_two, item.getIsTitle());
                mView = helper.getView(R.id.head_two);
                headerSetting(item);
                mImageView = helper.getView(R.id.iv_two_one_one);
                Glide.with(context).load(item.getImg1()).into(mImageView);
                itemOnclick(mImageView,item.getMid1(),item.getImg1(),item.getG_type());
                helper.setText(R.id.tv_two_one_one_title, item.getCon1());
                mImageView = helper.getView(R.id.iv_two_one_two);
                Glide.with(context).load(item.getImg2()).into(mImageView);
                itemOnclick(mImageView,item.getMid2(),item.getImg2(),item.getG_type());
                helper.setText(R.id.tv_two_one_two_title, item.getCon2());
                break;
            case MultipleItem.TRE:
                helper.setVisible(R.id.head_three, item.getIsTitle());
                mView = helper.getView(R.id.head_three);
                headerSetting(item);
                mImageView = helper.getView(R.id.iv_three_one_one);
                Glide.with(context).load(item.getImg1()).into(mImageView);
                itemOnclick(mImageView,item.getMid1(),item.getImg1(),item.getG_type());
                helper.setText(R.id.tv_three_one_one_title, item.getCon1());
                mImageView = helper.getView(R.id.iv_three_one_two);
                Glide.with(context).load(item.getImg2()).into(mImageView);
                itemOnclick(mImageView,item.getMid2(),item.getImg2(),item.getG_type());
                helper.setText(R.id.tv_three_one_two_title, item.getCon2());
                mImageView = helper.getView(R.id.iv_three_one_three);
                Glide.with(context).load(item.getImg3()).into(mImageView);
                itemOnclick(mImageView,item.getMid3(),item.getImg3(),item.getG_type());
                helper.setText(R.id.tv_three_one_three_title, item.getCon3());
                break;
        }

    }
    public void headerSetting(MultipleItem item){
        textView = (TextView) mView.findViewById(R.id.tv_title_type);
        textView.setText(item.getTitle());
        mImageViewTitle = (ImageView) mView.findViewById(R.id.iv_title_type);
        if (item.getTitle().equals("动漫")) {
            mImageViewTitle.setImageResource(R.drawable.everydady_manga);
        } else if (item.getTitle().equals("电视剧")) {
            mImageViewTitle.setImageResource(R.drawable.everydady_tv);
        }else if (item.getTitle().equals("电影")) {
            mImageViewTitle.setImageResource(R.drawable.everydady_movie);
        }else if (item.getTitle().equals("福利")) {
            mView.findViewById(R.id.ll_title_more).setVisibility(View.GONE);
            mImageViewTitle.setImageResource(R.drawable.everydady_welfare);
        }else if (item.getTitle().equals("广告")) {
            mView.findViewById(R.id.ll_title_more).setVisibility(View.GONE);
            mImageViewTitle.setImageResource(R.drawable.everydady_ad);
        }
    }

    public void itemOnclick(final ImageView view, final String id, final String img_url, final String type) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")) {
                    OneMovieDetailActivity.startE((Activity) context, id, img_url, view);
                } else {
                    Toast.makeText(context,"这是广告",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
