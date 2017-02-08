package com.example.cay.vipmoviw.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.base.adapter.MultipleItem;

import java.util.List;

/**
 * Created by Cay on 2017/2/8.
 */

public class EveryDayAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private Context context;
    private ImageView mImageView;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public EveryDayAdapter(Context context, List<MultipleItem> data) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.ONE, R.layout.item_everyday_one);
        addItemType(MultipleItem.TWO, R.layout.item_everyday_two);
        addItemType(MultipleItem.TRE, R.layout.item_everyday_three);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        Log.i("TAG", "convert: "+helper.getItemViewType());
        switch (helper.getItemViewType()) {
            case MultipleItem.ONE:
                mImageView = helper.getView(R.id.iv_one_photo);
                Glide.with(context).load(item.getIma_url_one()).into(mImageView);
                helper.setText(R.id.tv_one_photo_title, item.getContent_one());
                break;
            case MultipleItem.TWO:
                mImageView = helper.getView(R.id.iv_two_one_one);
                Glide.with(context).load(item.getIma_url_one()).into(mImageView);
                helper.setText(R.id.tv_two_one_one_title, item.getContent_one());

                mImageView = helper.getView(R.id.iv_two_one_two);
                Glide.with(context).load(item.getIma_url_two()).into(mImageView);
                helper.setText(R.id.tv_two_one_two_title, item.getContent_two());
                break;
            case MultipleItem.TRE:
                mImageView = helper.getView(R.id.iv_three_one_one);
                Glide.with(context).load(item.getIma_url_one()).into(mImageView);
                helper.setText(R.id.tv_three_one_one_title, item.getContent_one());

                mImageView = helper.getView(R.id.iv_three_one_two);
                Glide.with(context).load(item.getIma_url_two()).into(mImageView);
                helper.setText(R.id.tv_three_one_two_title, item.getContent_two());

                mImageView = helper.getView(R.id.iv_three_one_three);
                Glide.with(context).load(item.getIma_url_tre()).into(mImageView);
                helper.setText(R.id.tv_three_one_three_title, item.getContent_tre());
                break;
        }

    }
}
