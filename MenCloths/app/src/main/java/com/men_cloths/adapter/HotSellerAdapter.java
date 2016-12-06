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
import com.men_cloths.model.HotSeller;
import com.men_cloths.model.Trend;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class HotSellerAdapter extends BaseAdapter{
    Context context;
    List<HotSeller> hotSellerList;
    LayoutInflater layoutInflater;
    public HotSellerAdapter(Context context, List<HotSeller> hotSellerList){
        this.context=context;
        this.hotSellerList=hotSellerList;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return hotSellerList.size();
    }

    @Override
    public Object getItem(int position) {
        return hotSellerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_hotseller,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        HotSeller hotSeller=hotSellerList.get(position);
        viewHolder.contentTV.setText(hotSeller.getHotContent());
        viewHolder.priceTV.setText("￥"+hotSeller.getHotPrice()+"0");
        viewHolder.hotImg.setTag(hotSeller.getHotImg());
        new HttpImgThread(viewHolder.hotImg,hotSeller.getHotImg()).start();
        return convertView;
    }
    private class ViewHolder{
        private TextView contentTV;
        private TextView priceTV;
        private ImageView hotImg;
        public ViewHolder(View view){
            contentTV= (TextView) view.findViewById(R.id.hot_seller_content);
            priceTV= (TextView) view.findViewById(R.id.hot_seller_price);
            hotImg= (ImageView) view.findViewById(R.id.hot_seller_img);
        }
    }
}
