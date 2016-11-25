package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ClassifyAdapter extends BaseAdapter{
    Context context;
    List<Classify> classifyList;
    LayoutInflater layoutInflater;

    public ClassifyAdapter(Context context, List<Classify> classifyList){
            this.context=context;
        this.classifyList=classifyList;
        layoutInflater=LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return classifyList.size();
    }

    @Override
    public Object getItem(int position) {
        return classifyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_classify,null);

        }
        Classify classify=classifyList.get(position);
        ImageView imageView1= (ImageView) convertView.findViewById(R.id.classify_item_img1);
        imageView1.setImageResource(classify.getImgId1());
        TextView textView1= (TextView) convertView.findViewById(R.id.classify_item_text1);
        textView1.setText(classify.getText1());
        ImageView imageView2= (ImageView) convertView.findViewById(R.id.classify_item_img2);
        imageView2.setImageResource(classify.getImgId2());
        TextView textView2= (TextView) convertView.findViewById(R.id.classify_item_text2);
        textView2.setText(classify.getText2());
        return convertView;
    }
}
