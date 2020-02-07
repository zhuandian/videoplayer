package com.example.videoplayer.business.tab;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.videoplayer.R;
import com.example.videoplayer.adapter.OnlineVideoAdapter;
import com.example.videoplayer.entity.VideoEntity;
import com.example.videoplayer.utils.MyJsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuandian.base.BaseFragment;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * desc :
 * author：xiedong
 * date：2020/02/05
 */
public class OnLineVideoFragment extends BaseFragment {
    String onlineVideoUrl = "http://152.136.189.40/entity/onlineVideo.json";
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.et_keyword)
    EditText etKeyWord;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    List<VideoEntity> entityList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_video;
    }

    @Override
    protected void initView() {
        initDataList("");

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = etKeyWord.getText().toString();
                if (keyWord.length() > 0) {
                   initDataList(keyWord);
                } else {
                    initDataList("");
                }
            }
        });
    }

    private void initDataList(String keyWord) {
        RequestQueue requestQueue = Volley.newRequestQueue(actitity);
        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(onlineVideoUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                entityList.clear();
                entityList.addAll(new Gson().fromJson(jsonArray.toString(), new TypeToken<List<VideoEntity>>() {
                }.getType()));
                OnlineVideoAdapter onlineVideoAdapter = new OnlineVideoAdapter(entityList, actitity);
                recyclerView.setAdapter(onlineVideoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(actitity));
                if (keyWord.length() > 0) {
                    List<VideoEntity> result = new ArrayList<>();
                    for (VideoEntity videoEntity : entityList) {
                        if (videoEntity.getName().contains(keyWord)) {
                            result.add(videoEntity);
                        }
                    }
                    onlineVideoAdapter.setNewData(result);
                }

                }
            },new Response.ErrorListener()

            {
                @Override
                public void onErrorResponse (VolleyError volleyError){

            }
            }
        );
        requestQueue.add(myJsonArrayRequest);
        }

        @Override
        public void setUserVisibleHint ( boolean isVisibleToUser){
            super.setUserVisibleHint(isVisibleToUser);
            if (!isVisibleToUser) {
                JZVideoPlayer.releaseAllVideos();
            }
        }
    }
