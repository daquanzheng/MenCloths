package com.men_cloths.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.Thread.HttpImgFromThread;
import com.men_cloths.Thread.HttpImgThread;
import com.men_cloths.model.Collocate;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class CollocateAdapter extends BaseAdapter{
    List<Collocate> collocateList;
    Context context;
    LayoutInflater layoutInflater;
    HttpImgThread httpImgThread;

    public CollocateAdapter(Context context,List<Collocate> collocateList){
        this.collocateList=collocateList;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
      httpImgThread=new HttpImgThread();
    }
    @Override
    public int getCount() {
        return collocateList.size();
    }

    @Override
    public Object getItem(int position) {
        return collocateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_new_collocate,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Collocate collocate=collocateList.get(position);
        if(position%2==0) {
            viewHolder.wholeImg1.setTag(collocate.getWholeImg());
            httpImgThread.showImageByAsyncTask(viewHolder.wholeImg1,collocate.getWholeImg());
            viewHolder.wholeImg1.setVisibility(View.VISIBLE);
            viewHolder.wholeImg2.setVisibility(View.GONE);
        }else {
            viewHolder.wholeImg2.setTag(collocate.getWholeImg());
            httpImgThread.showImageByAsyncTask(viewHolder.wholeImg2,collocate.getWholeImg());
            viewHolder.wholeImg1.setVisibility(View.GONE);
            viewHolder.wholeImg2.setVisibility(View.VISIBLE);
        }
        viewHolder.partImg1.setTag(collocate.getPartImg1());
        httpImgThread.showImageByAsyncTask(viewHolder.partImg1,collocate.getPartImg1());
        viewHolder.partText1.setText(collocate.getPartImg1Title());

        viewHolder.partImg2.setTag(collocate.getPartImg2());
        httpImgThread.showImageByAsyncTask(viewHolder.partImg2,collocate.getPartImg2());
        viewHolder.partText2.setText(collocate.getPartImg2Title());

        viewHolder.partImg3.setTag(collocate.getPartImg3());
        httpImgThread.showImageByAsyncTask(viewHolder.partImg3,collocate.getPartImg3());
        viewHolder.partText3.setText(collocate.getPartImg3Title());

        viewHolder.partImg4.setTag(collocate.getPartImg4());
        httpImgThread.showImageByAsyncTask(viewHolder.partImg4,collocate.getPartImg4());
        viewHolder.partText4.setText(collocate.getPartImg4Title());

        return convertView;
    }
    private class ViewHolder{
        private ImageView wholeImg1;
        private ImageView partImg1;
        private TextView partText1;
        private ImageView partImg2;
        private TextView partText2;
        private ImageView partImg3;
        private TextView partText3;
        private ImageView partImg4;
        private TextView partText4;
        private ImageView wholeImg2;
    public ViewHolder(View view){
        wholeImg1 = (ImageView) view.findViewById(R.id.collocate_item_img1);
        wholeImg2=(ImageView) view.findViewById(R.id.collocate_item_img2);
        partImg1= (ImageView) view.findViewById(R.id.collocate_img01);
        partText1= (TextView) view.findViewById(R.id.collocate_text01);
        partImg2= (ImageView) view.findViewById(R.id.collocate_img02);
        partText2= (TextView) view.findViewById(R.id.collocate_text02);
        partImg3= (ImageView) view.findViewById(R.id.collocate_img03);
        partText3= (TextView) view.findViewById(R.id.collocate_text03);
        partImg4= (ImageView) view.findViewById(R.id.collocate_img04);
        partText4= (TextView) view.findViewById(R.id.collocate_text04);
    }
    }
}
