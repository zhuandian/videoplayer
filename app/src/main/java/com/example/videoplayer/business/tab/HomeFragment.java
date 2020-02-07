package com.example.videoplayer.business.tab;

import android.view.View;

import com.example.videoplayer.MainActivity;
import com.example.videoplayer.R;
import com.example.videoplayer.business.login.LoginActivity;
import com.zhuandian.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc :
 * author：xiedong
 * date：2020/02/05
 */
public class HomeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.tv_local_video_video,R.id.tv_online_video,R.id.tv_person})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_local_video_video:
                ((MainActivity) actitity).setCurrentPage(MainActivity.PAGE_LOCAL_VIDEO);
                break;
            case R.id.tv_online_video:
                ((MainActivity) actitity).setCurrentPage(MainActivity.PAGE_ONLINE_VIDEO);
                break;
            case R.id.tv_person:
                ((MainActivity) actitity).setCurrentPage(MainActivity.PAGE_MY);
                break;
        }
    }
}
