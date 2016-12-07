package com.men_cloths.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.mainContent.ExtraAppraiseActivity;
import com.men_cloths.model.Waitappraise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitappraiseAdapter extends BaseAdapter{
    private   List<Waitappraise> waitappraiseList = new ArrayList<>();
    private  Context context;
    private   LayoutInflater inflater;
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
    TextView extraAppraise,appraise;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.order_waitappraise_item,null);
            extraAppraise = (TextView) convertView.findViewById(R.id.extra_appraise);
            appraise = (TextView) convertView.findViewById(R.id.appraise);
            extraAppraise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExtraAppraiseActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
}
