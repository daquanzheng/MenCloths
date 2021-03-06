package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.men_cloths.R;
import com.men_cloths.model.ProductClassify;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MallAdapter extends BaseAdapter{
    Context context;
    List<ProductClassify> list;
    LayoutInflater layoutInflater;

    public MallAdapter(Context context,List<ProductClassify> list){
        this.context=context;
        this.list=list;
        layoutInflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_mall,null);
        }
        ProductClassify productClassify=list.get(position);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.mall_img);
        imageView.setImageBitmap(productClassify.getImgname());
        return convertView;
    }
}
