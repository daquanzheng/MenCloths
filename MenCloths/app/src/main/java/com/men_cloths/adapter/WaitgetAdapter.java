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
import com.men_cloths.mainContent.MyWuliuActivity;
import com.men_cloths.model.Waitget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitgetAdapter extends BaseAdapter{
    List<Waitget> waitgetList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    EditOrderClick editOrderClick;
    public WaitgetAdapter(Context context, List<Waitget> waitgetList){
        this.context = context;
        this.waitgetList = waitgetList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return waitgetList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitgetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    TextView deleteOrder,checkWuliu;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.order_waitget_item,null);
            viewHolder.goodsPicture = (ImageView) convertView.findViewById(R.id.goods_picture);
            viewHolder.goodsName = (TextView) convertView.findViewById(R.id.goods_name);
            viewHolder.goodsColor = (TextView) convertView.findViewById(R.id.goods_color);
            viewHolder.goodsSize = (TextView) convertView.findViewById(R.id.goods_size);
            viewHolder.goodsPrice = (TextView) convertView.findViewById(R.id.goods_price);
            convertView.setTag(viewHolder);
            deleteOrder = (TextView) convertView.findViewById(R.id.delete_order);
            checkWuliu = (TextView) convertView.findViewById(R.id.check_wuliu);
            final ViewHolder viewHolder1 = viewHolder;
            deleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str1 = viewHolder1.goodsName.getText().toString();
//                    waitgetList.remove(position);
//                    WaitgetAdapter.this.notifyDataSetChanged();
                    editOrderClick.deleteOrder(position,str1);
                }
            });
            checkWuliu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str2 = viewHolder1.goodsName.getText().toString();
//                    Intent intent = new Intent();
//                    intent.setClass(context, MyWuliuActivity.class);
//                    context.startActivity(intent);
                    editOrderClick.checkWuliu(position,str2);
                }
            });
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Waitget waitget = waitgetList.get(position);
        viewHolder.goodsPicture.setImageResource(waitget.getPicture());
        viewHolder.goodsName.setText(waitget.getName());
        viewHolder.goodsColor.setText(waitget.getColor());
        viewHolder.goodsSize.setText(waitget.getSize());
        viewHolder.goodsPrice.setText(waitget.getPrice());
        return convertView;
    }
    public class ViewHolder{
        ImageView goodsPicture;
        TextView goodsName,goodsColor,goodsSize,goodsPrice;
    }
    //自定义一个接口，将item上的点击事件放到Activity中的适配器中执行
    public interface EditOrderClick{
        public void deleteOrder(int position,String name);
        public void checkWuliu(int position,String name);
    }
    public void setEditOrderClick(EditOrderClick editOrderClick){
        this.editOrderClick = editOrderClick;
    }
}
