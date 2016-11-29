package com.men_cloths.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.NewProduct;


import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class NewProductAdapter extends BaseAdapter{
    Context context;
    List<NewProduct> newProducts;
    LayoutInflater layoutInflater;
    public NewProductAdapter(Context context, List<NewProduct> newProducts){
        this.context=context;
        this.newProducts=newProducts;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return newProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return newProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_new,null);
        }
        NewProduct newProduct=newProducts.get(position);
        ImageView imageView1= (ImageView) convertView.findViewById(R.id.new_img1);
        imageView1.setImageResource(newProduct.getImg1());
        TextView textView1= (TextView) convertView.findViewById(R.id.new_text_title1);
        textView1.setText(newProduct.getContent1());
        TextView textPrice1= (TextView) convertView.findViewById(R.id.new_text_price1);
        textPrice1.setText("￥"+newProduct.getPrice1()+"0");
        ImageView imageView2= (ImageView) convertView.findViewById(R.id.new_img2);
        imageView2.setImageResource(newProduct.getImg2());
        TextView textView2= (TextView) convertView.findViewById(R.id.new_text_title2);
        textView2.setText(newProduct.getContent2());
        TextView textPrice2= (TextView) convertView.findViewById(R.id.new_text_price2);
        textPrice2.setText("￥"+newProduct.getPrice2()+"0");
        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        return convertView;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.new_img1:
                    Intent intent=new Intent(context, ShopInfo.class);
                    context.startActivity(intent);
                case R.id.new_img2:
                    intent=new Intent(context, ShopInfo.class);
                    context.startActivity(intent);
            }
        }
    };
}
