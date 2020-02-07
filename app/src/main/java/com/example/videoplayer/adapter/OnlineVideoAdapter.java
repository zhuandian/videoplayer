package com.example.videoplayer.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.videoplayer.R;
import com.example.videoplayer.db.VideoDao;
import com.example.videoplayer.entity.VideoEntity;
import com.example.videoplayer.view.MyViedeoPlayer;
import com.zhuandian.base.BaseAdapter;
import com.zhuandian.base.BaseViewHolder;

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
    MyViedeoPlayer myViedeoPlayer;

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
        myViedeoPlayer.setUp(videoEntity.getPath(),
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                videoEntity.getName());
        Glide.with(mContext).load(videoEntity.getThumbPath())
                .into(myViedeoPlayer.thumbImageView);

        myViedeoPlayer.setOnVideoPlayingListener(new MyViedeoPlayer.OnVideoPlayingListener() {
            @Override
            public void onVideoPlaying() {
                VideoDao dao = VideoDao.getInstance(mContext);
                dao.addVideo(videoEntity);
            }
        });


    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_online_video;
    }
}
