package com.men_cloths.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.men_cloths.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
public class MyGridViewAdapter extends BaseAdapter{
    Context context;
    List<String> stringList;
    public MyGridViewAdapter(Context context,List<String> stringList){
        this.context=context;
        this.stringList=stringList;
    }
    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView==null){
            textView=new TextView(context);
            ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(20,5,20,5);
            textView.setTextColor(context.getResources().getColor(R.color.black_model));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20f);
            textView.setBackgroundResource(R.drawable.text_bg);
        }else {
            textView= (TextView) convertView;
        }
        textView.setText(stringList.get(position));
        return textView;
    }
}
