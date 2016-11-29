package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.men_cloths.R;
import com.men_cloths.model.WaitGet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitgetAdapter extends BaseAdapter{
    List<WaitGet> waitGetList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public WaitgetAdapter(Context context, List<WaitGet> waitGetList){
        this.context = context;
        this.waitGetList = waitGetList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return waitGetList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitGetList.get(position);
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
