package com.men_cloths.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */

public abstract class AdapterforAll extends BaseAdapter{
    private Context context;
    private List<Object> list;
    public AdapterforAll(Context context, List<Object> list){
        this.context=context;
        this.list=list;

    }




    public interface item{
        public View getView(int position, View convertView);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return setView(position,convertView);
    }
    public abstract View setView(int position, View convertView);


}
