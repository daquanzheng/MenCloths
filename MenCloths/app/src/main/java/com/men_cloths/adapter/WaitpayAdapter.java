package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.men_cloths.R;
import com.men_cloths.model.Waitpay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitpayAdapter extends BaseAdapter {
    List<Waitpay> waitpayList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public WaitpayAdapter(Context context,List<Waitpay> waitpayList){
        this.context = context;
        this.waitpayList = waitpayList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return waitpayList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitpayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.order_waitpay_item,null);
        }
        return convertView;
    }
}
