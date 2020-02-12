package com.example.videoplayer.business;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoplayer.R;
import com.example.videoplayer.adapter.OnlineVideoAdapter;
import com.example.videoplayer.db.VideoDao;
import com.example.videoplayer.entity.VideoEntity;
import com.zhuandian.base.BaseActivity;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * desc :
 * author：xiedong
 * date：2020/02/07
 */
public class VideoHistoryActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_history;
    }

    @Override
    protected void setUpView() {
        setTitle("历史记录");
        VideoDao dao = VideoDao.getInstance(this);
        List<VideoEntity> videoList = dao.getVideoList();
        if (videoList==null) return;
        Collections.reverse(videoList);
        recyclerView.setAdapter(new OnlineVideoAdapter(videoList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
