package com.men_cloths.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.Thread.HttpImgThread;
import com.men_cloths.model.ProductDetails;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ProductDetailsAdapter extends BaseAdapter {

    Context context;
    List<ProductDetails> list;
    LayoutInflater layoutInflater;
    public ProductDetailsAdapter(Context context,List<ProductDetails> list){
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
       if(convertView==null) {
           convertView=layoutInflater.inflate(R.layout.listview_item_product_details,null);
       }
        ProductDetails productDetails=list.get(position);
        ImageView product=(ImageView)convertView.findViewById(R.id.img_product);
        //product.setImageResource(productDetails.getProduct());
//        viewHolder.img1.setTag(classify.getImgUrl1());
//        new HttpImgThread(viewHolder.img1,classify.getImgUrl1()).start();

        product.setImageBitmap(productDetails.getProduct());
        TextView description=(TextView)convertView.findViewById(R.id.textview_product_description);
        description.setText(productDetails.getDescription());
        TextView type=(TextView)convertView.findViewById(R.id.textview_product_type);
        type.setText(productDetails.getType());
        TextView price=(TextView)convertView.findViewById(R.id.textview_price);
        price.setText("￥"+productDetails.getPrice());
        TextView original=(TextView)convertView.findViewById(R.id.textview_original_price);
        original.setText("￥"+productDetails.getOriginal());

        return convertView;
    }
}
