package com.example.videoplayer.business.tab;

import com.example.videoplayer.R;
import com.zhuandian.base.BaseFragment;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * desc :
 * author：xiedong
 * date：2020/02/05
 */
public class OnLineVideoFragment extends BaseFragment {
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard jzVideoPlayerStandard;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_video;
    }

    @Override
    protected void initView() {
        jzVideoPlayerStandard.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                "dsffdsgfd");
//        jzVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser){
            JZVideoPlayer.releaseAllVideos();
        }
    }
}
