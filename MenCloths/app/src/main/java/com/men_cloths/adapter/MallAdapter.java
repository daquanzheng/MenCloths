package com.men_cloths.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.men_cloths.R;
import com.men_cloths.model.ActivityManager;
import com.men_cloths.model.CachetToFile;
import com.men_cloths.model.LoadImage;
import com.men_cloths.model.ProductClassify;

import java.io.InputStream;
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
        Bitmap bitmap=ActivityManager.memoryCache.get(list.get(position).getUrl());
        String url=list.get(position).getUrl();
        InputStream is;

        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }else if((is= CachetToFile.getImage(url,context))!=null){
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        }else {
            LoadImage.load(imageView,url,context);
        }
        //imageView.setImageBitmap(productClassify.getImgname());
        return convertView;
    }
}
