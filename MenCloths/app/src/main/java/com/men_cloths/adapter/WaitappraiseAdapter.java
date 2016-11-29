package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.men_cloths.R;
import com.men_cloths.model.Waitappraise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitappraiseAdapter extends BaseAdapter{
    List<Waitappraise> waitappraiseList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public WaitappraiseAdapter(Context context, List<Waitappraise> waitappraiseList){
        this.context = context;
        this.waitappraiseList = waitappraiseList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return waitappraiseList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitappraiseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.order_waitappraise_item,null);
        }
        return convertView;
    }
}
