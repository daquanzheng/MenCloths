package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.model.Footprint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class FootprintAdapter extends BaseAdapter{
    List<Footprint> footprintList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public FootprintAdapter(Context context,List<Footprint> footprintList){
        this.context = context;
        this.footprintList = footprintList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return footprintList.size();
    }

    @Override
    public Object getItem(int position) {
        return footprintList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.footprint_item,null);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time_footprint);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.footprint_item_picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.footprint_goods_name);
            viewHolder.color = (TextView) convertView.findViewById(R.id.footprint_goods_color);
            viewHolder.size = (TextView) convertView.findViewById(R.id.footprint_goods_size);
            viewHolder.price = (TextView) convertView.findViewById(R.id.footprint_goods_price);
            viewHolder.delete = (Button) convertView.findViewById(R.id.footprint_goods_clear);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    footprintList.remove(position);
                    FootprintAdapter.this.notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);

        }
        viewHolder = (ViewHolder) convertView.getTag();
//        Footprint footprint = new Footprint();
//        viewHolder.time.setText(footprint.getTime());
//        viewHolder.picture.setImageResource(footprint.getPicture());
//        viewHolder.name.setText(footprint.getName());
//        viewHolder.color.setText(footprint.getColor());
//        viewHolder.size.setText(footprint.getSize());
//        viewHolder.price.setText(footprint.getPrice());
        return convertView;
    }
    public class ViewHolder{
        TextView time;
        ImageView picture;
        TextView name;
        TextView color;
        TextView size;
        TextView price;
        Button delete;
    }
}
