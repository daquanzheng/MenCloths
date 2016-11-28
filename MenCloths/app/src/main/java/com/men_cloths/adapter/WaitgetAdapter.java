package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.men_cloths.R;
import com.men_cloths.model.Waitget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitgetAdapter extends BaseAdapter{
    List<Waitget> waitgetList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public WaitgetAdapter(Context context, List<Waitget> waitgetList){
        this.context = context;
        this.waitgetList = waitgetList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return waitgetList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitgetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.order_waitget_item,null);
        }
        return convertView;
    }
}
