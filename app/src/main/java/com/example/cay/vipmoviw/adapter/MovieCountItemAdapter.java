package com.example.cay.vipmoviw.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cay.vipmoviw.R;
import com.example.cay.vipmoviw.bean.MovieBean;
import com.example.cay.vipmoviw.bean.SubjectsBean;
import com.example.cay.vipmoviw.player.PlayActivity;

import java.util.List;

/**
 * Created by Cay on 2017/2/5.
 */

public class MovieCountItemAdapter extends BaseQuickAdapter<MovieBean,BaseViewHolder> {
    private Context context;

    public MovieCountItemAdapter(int layoutResId, List<MovieBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final MovieBean item) {
        helper.setText(R.id.movie_count_btn, item.getItemName());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String abc = String.valueOf(helper.getPosition()+1)+".mp4";
                PlayActivity.actionStart(context,item.getMovieUrl()+abc,item.getMovieName());
            }
        });
    }
}
