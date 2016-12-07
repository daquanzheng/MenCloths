package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.HotSeller;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class HotSellerAdapter extends BaseAdapter{
    private Context context;
    private List<HotSeller> hotSellerList;
    private  LayoutInflater layoutInflater;
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
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_hotseller,null);
        }
        HotSeller hotSeller=hotSellerList.get(position);
        TextView textView1= (TextView) convertView.findViewById(R.id.hot_seller_content);
        textView1.setText(hotSeller.getHotContent());
        ImageView imageView= (ImageView) convertView.findViewById(R.id.hot_seller_img);
        imageView.setImageResource(hotSeller.getHotImg());
        TextView textView2= (TextView) convertView.findViewById(R.id.hot_seller_price);
        textView2.setText("￥ "+hotSeller.getHotPrice()+"0");
        return convertView;
    }
}
