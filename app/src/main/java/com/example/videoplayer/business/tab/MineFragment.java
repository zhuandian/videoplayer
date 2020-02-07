package com.example.videoplayer.business.tab;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.videoplayer.R;
import com.example.videoplayer.business.VideoHistoryActivity;
import com.zhuandian.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc :
 * author：xiedong
 * date：2020/02/05
 */
public class MineFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tv_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_history:
                startActivity(new Intent(actitity, VideoHistoryActivity.class));
                break;
        }
    }
}
