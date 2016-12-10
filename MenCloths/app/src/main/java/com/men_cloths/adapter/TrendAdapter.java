package com.men_cloths.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.Thread.HttpImgThread;
import com.men_cloths.mainContent.ThreadInfo;
import com.men_cloths.model.Trend;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class TrendAdapter extends BaseAdapter{
    Context context;
    List<Trend> trendList;
    LayoutInflater layoutInflater;
    Boolean state=true;
    HttpImgThread httpImgThread;
    public TrendAdapter(Context context,List<Trend> trendList){
        this.context=context;
        this.trendList=trendList;
        layoutInflater=LayoutInflater.from(context);
        httpImgThread=new HttpImgThread();
    }
    @Override
    public int getCount() {
        return trendList.size();
    }

    @Override
    public Object getItem(int position) {
        return trendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_trend,null);
        }
        Trend trend=trendList.get(position);
        TextView textView= (TextView) convertView.findViewById(R.id.trend_title);
        textView.setText(trend.getTitle());
        ImageView imageView= (ImageView) convertView.findViewById(R.id.img_trend_sport);
        imageView.setTag(trend.getImg());
        httpImgThread.showImageByAsyncTask(imageView,trend.getImg());
        final TextView collectionTV= (TextView) convertView.findViewById(R.id.trend_item_collection);
        collectionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.rating_fation_full);
                    drawable.setBounds(0,0,36,36);
                    collectionTV.setCompoundDrawables(drawable, null, null, null);
                    state = false;
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.rating_fashion);
                    drawable.setBounds(0,0,36,36);
                    collectionTV.setCompoundDrawables(drawable, null, null, null);
                    state = true;
                }
            }
        });
        TextView detailTV= (TextView) convertView.findViewById(R.id.trend_item_detail);
        detailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ThreadInfo.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}
