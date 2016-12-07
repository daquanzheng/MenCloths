package com.men_cloths.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.mainContent.MyWuliuActivity;
import com.men_cloths.model.WaitGet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitgetAdapter extends BaseAdapter{
    List<WaitGet> waitgetList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public WaitgetAdapter(Context context, List<WaitGet> waitgetList){
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
    TextView deleteOrder,checkWuliu;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.order_waitget_item,null);
            deleteOrder = (TextView) convertView.findViewById(R.id.delete_order);
            checkWuliu = (TextView) convertView.findViewById(R.id.check_wuliu);
            deleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waitgetList.remove(position);
                    WaitgetAdapter.this.notifyDataSetChanged();
                }
            });
            checkWuliu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, MyWuliuActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
    public class ViewHolder{

    }
}
