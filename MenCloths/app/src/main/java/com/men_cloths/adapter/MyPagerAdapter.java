package com.men_cloths.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MyPagerAdapter extends PagerAdapter {
    private  ArrayList<View> arrayList;
    @Override
    public int getCount() {//返回数据源的总条数
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(arrayList.get(position));
    }
//用于显示每个item的视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(arrayList.get(position));
        return arrayList.get(position);
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    public MyPagerAdapter(ArrayList<View> arrayList) {
        this.arrayList = arrayList;
    }
}
