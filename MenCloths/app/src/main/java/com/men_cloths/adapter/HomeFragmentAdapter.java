package com.men_cloths.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class HomeFragmentAdapter extends FragmentStatePagerAdapter{
    List<Fragment> fragmentList;
    public HomeFragmentAdapter(FragmentManager fm,List<Fragment> fragmentLis) {
        super(fm);
        this.fragmentList=fragmentLis;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
