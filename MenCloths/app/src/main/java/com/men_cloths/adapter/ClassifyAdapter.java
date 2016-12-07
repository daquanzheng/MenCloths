package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.Thread.HttpImgThread;
import com.men_cloths.model.Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ClassifyAdapter extends BaseAdapter{
    private Context context;
    private List<Classify> classifyList;
    private LayoutInflater layoutInflater;

    public ClassifyAdapter(Context context, List<Classify> classifyList){
        this.context=context;
        this.classifyList=classifyList;
        if(context!=null){
            layoutInflater=LayoutInflater.from(context);
        }
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
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_classify,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Classify classify=classifyList.get(position);
        viewHolder.text1.setText(classify.getText1());
        viewHolder.img1.setTag(classify.getImgUrl1());
        new HttpImgThread(viewHolder.img1,classify.getImgUrl1()).start();
        viewHolder.text2.setText(classify.getText2());
        viewHolder.img2.setTag(classify.getImgUrl2());
        new HttpImgThread(viewHolder.img2,classify.getImgUrl2()).start();
        return convertView;
    }


    private  class  ViewHolder{
        private TextView text1;
        private ImageView img1;
        private TextView text2;
        private ImageView img2;
        public ViewHolder(View view){
            text1= (TextView) view.findViewById(R.id.classify_item_text1);
            img1= (ImageView) view.findViewById(R.id.classify_item_img1);
            text2= (TextView) view.findViewById(R.id.classify_item_text2);
            img2= (ImageView) view.findViewById(R.id.classify_item_img2);
        }
    }
}
