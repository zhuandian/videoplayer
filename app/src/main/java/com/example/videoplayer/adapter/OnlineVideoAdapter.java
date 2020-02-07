package com.example.videoplayer.adapter;

import android.content.Context;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.example.videoplayer.R;
import com.example.videoplayer.entity.VideoEntity;
import com.zhuandian.base.BaseAdapter;
import com.zhuandian.base.BaseViewHolder;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * desc :
 * author：xiedong
 * date：2020/02/06
 */
public class OnlineVideoAdapter extends BaseAdapter<VideoEntity, BaseViewHolder> {
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard jzVideoPlayerStandard;

    public OnlineVideoAdapter(List<VideoEntity> mDatas, Context context) {
        super(mDatas, context);
    }

    public void setNewData(List<VideoEntity> mDatas) {
        super.mDatas.clear();
        super.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    @Override
    protected void converData(BaseViewHolder myViewHolder, VideoEntity videoEntity, int position) {
        ButterKnife.bind(this, myViewHolder.itemView);
        jzVideoPlayerStandard.setUp(videoEntity.getPath(),
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                videoEntity.getName());
        Glide.with(mContext).load(videoEntity.getThumbPath())
                .into(jzVideoPlayerStandard.thumbImageView);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_online_video;
    }
}
