package com.example.videoplayer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhuandian.base.BaseFragment;

import java.util.List;

/**
 * desc :
 * author：xiedong
 * date：2020/02/05
 */
public class HomePageAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments;

    public HomePageAdapter(@NonNull FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}