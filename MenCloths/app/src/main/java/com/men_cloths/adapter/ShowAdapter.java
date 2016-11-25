package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.Show;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class ShowAdapter extends BaseAdapter{
    Context context;
    List<Show> showList;
    LayoutInflater layoutInflater;
    public ShowAdapter(Context context, List<Show> showList){
        this.context=context;
        this.showList=showList;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return showList.size();
    }

    @Override
    public Object getItem(int position) {
        return showList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_show,null);
        }
        Show show=showList.get(position);
        ImageView imageViewTitle= (ImageView) convertView.findViewById(R.id.home_show_title_img1);
        imageViewTitle.setImageResource(show.getTitleImgId());
        TextView textView= (TextView) convertView.findViewById(R.id.home_show_title);
        textView.setText(show.getTitle());
        TextView textViewContent= (TextView) convertView.findViewById(R.id.home_show_content);
        textViewContent.setText(show.getContent());
        ImageView imageViewPart1= (ImageView) convertView.findViewById(R.id.home_show_part_img1);
        imageViewPart1.setImageResource(show.getPartImgId1());
        ImageView imageViewPart2= (ImageView) convertView.findViewById(R.id.home_show_part_img2);
        imageViewPart2.setImageResource(show.getPartImgId2());

        return convertView;
    }

}
