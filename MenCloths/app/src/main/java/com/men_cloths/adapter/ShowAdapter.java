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
import com.men_cloths.model.Show;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class ShowAdapter extends BaseAdapter{
    private   Context context;
    private   List<Show> showList;
    private  LayoutInflater layoutInflater;
    private HttpImgThread httpImgThread;
    public ShowAdapter(Context context, List<Show> showList){
        this.context=context;
        this.showList=showList;
        layoutInflater=LayoutInflater.from(context);
        httpImgThread=new HttpImgThread();
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
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_show,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Show show=showList.get(position);
        viewHolder.titleImg.setTag(show.getTitleImg());
        httpImgThread.showImageByAsyncTask(viewHolder.titleImg,show.getTitleImg());

        viewHolder.partImg1.setTag(show.getPartImg1());
        httpImgThread.showImageByAsyncTask(viewHolder.partImg1,show.getPartImg1());

        viewHolder.partImg2.setTag(show.getPartImg2());
        httpImgThread.showImageByAsyncTask(viewHolder.partImg2,show.getPartImg2());

        viewHolder.title.setText(show.getNickName());

        viewHolder.mood.setText(show.getMood());

        return convertView;
    }
    private class ViewHolder{
        private ImageView titleImg;
        private ImageView partImg1;
        private ImageView partImg2;
        private TextView title;
        private TextView mood;
        public ViewHolder(View view){
            titleImg= (ImageView) view.findViewById(R.id.home_show_title_img1);
            partImg1= (ImageView) view.findViewById(R.id.home_show_part_img1);
            partImg2= (ImageView) view.findViewById(R.id.home_show_part_img2);
            title= (TextView) view.findViewById(R.id.home_show_title);
            mood= (TextView) view.findViewById(R.id.home_show_content);
        }
    }

}
