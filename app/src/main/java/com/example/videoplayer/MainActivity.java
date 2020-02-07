package com.example.videoplayer;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.example.videoplayer.adapter.HomePageAdapter;
import com.example.videoplayer.business.tab.HomeFragment;
import com.example.videoplayer.business.tab.LocalVideoFragment;
import com.example.videoplayer.business.tab.MineFragment;
import com.example.videoplayer.business.tab.OnLineVideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhuandian.base.BaseActivity;
import com.zhuandian.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_home)
    ViewPager vpHome;
    @BindView(R.id.tab_bottom)
    BottomNavigationView tabBottom;
    public static final int PAGE_HOME = 0;
    public static final int PAGE_LOCAL_VIDEO = 1;
    public static final int PAGE_ONLINE_VIDEO = 2;
    public static final int PAGE_MY = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LocalVideoFragment());
        fragmentList.add(new OnLineVideoFragment());
        fragmentList.add(new MineFragment());
        vpHome.setAdapter(new HomePageAdapter(getSupportFragmentManager(), fragmentList));
        vpHome.setOffscreenPageLimit(4);

        vpHome.setCurrentItem(PAGE_HOME);
        initBottomTab();
    }

    public  void setCurrentPage(int position){
        vpHome.setCurrentItem(position);
    }
    private void initBottomTab() {
        vpHome.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabBottom.getMenu().getItem(position).setChecked(true);
            }
        });

        tabBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               switch (item.getItemId()){
                   case R.id.tab_home:
                       vpHome.setCurrentItem(PAGE_HOME);
                       break;
                   case R.id.tab_local:
                       vpHome.setCurrentItem(PAGE_LOCAL_VIDEO);
                       break;
                   case R.id.tab_online:
                       vpHome.setCurrentItem(PAGE_ONLINE_VIDEO);
                       break;
                   case R.id.tab_my:
                       vpHome.setCurrentItem(PAGE_MY);
                       break;
               }

                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
    }
}
