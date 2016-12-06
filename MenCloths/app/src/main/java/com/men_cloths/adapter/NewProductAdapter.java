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

import com.men_cloths.Thread.HttpImgThread;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.NewProduct;


import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class NewProductAdapter extends BaseAdapter{
    Context context;
    List<NewProduct> newProductList;
    LayoutInflater layoutInflater;
    public NewProductAdapter(Context context, List<NewProduct> newProductList){
        this.context=context;
        this.newProductList=newProductList;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return newProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return newProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_new,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        NewProduct newProduct=newProductList.get(position);
        viewHolder.content1.setText(newProduct.getContent1());
        viewHolder.price1.setText("￥"+newProduct.getPrice1().toString()+"0");
        viewHolder.img1.setTag(newProduct.getImg1());
        new HttpImgThread(viewHolder.img1,newProduct.getImg1()).start();
        viewHolder.content2.setText(newProduct.getContent2());
        viewHolder.price2.setText("￥"+newProduct.getPrice2().toString()+"0");
        viewHolder.img2.setTag(newProduct.getImg2());
        new HttpImgThread(viewHolder.img2,newProduct.getImg2()).start();
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
    private class ViewHolder{
        private TextView content1;
        private TextView price1;
        private ImageView img1;
        private TextView content2;
        private TextView price2;
        private ImageView img2;
        public ViewHolder(View view){
           content1= (TextView) view.findViewById(R.id.new_text_title1);
            price1= (TextView) view.findViewById(R.id.new_text_price1);
            img1= (ImageView) view.findViewById(R.id.new_img1);
            content2= (TextView) view.findViewById(R.id.new_text_title2);
            price2= (TextView) view.findViewById(R.id.new_text_price2);
            img2= (ImageView) view.findViewById(R.id.new_img2);
        }

    }


}
