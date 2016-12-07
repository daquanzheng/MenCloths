package com.men_cloths.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.Collocate;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class CollocateAdapter extends BaseAdapter{
    private List<Collocate> collocateList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CollocateAdapter(Context context,List<Collocate> collocateList){
        this.collocateList=collocateList;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
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
        Collocate collocate=collocateList.get(position);
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item_collocate,null);
        }
        ImageView wholeImg1= (ImageView) convertView.findViewById(R.id.collocate_item_img1);
        wholeImg1.setImageResource(collocate.getWholeImg1());

        Drawable drawable;
        TextView textView1= (TextView) convertView.findViewById(R.id.collocate_img01_text01);
        drawable=convertView.getResources().getDrawable(collocate.getPartImg1());
        drawable.setBounds(0,0,172,172);
        textView1.setCompoundDrawables(null,drawable,null,null);
        textView1.setText(collocate.getPartImg1Title());

        TextView textView2= (TextView) convertView.findViewById(R.id.collocate_img01_text02);
        drawable=convertView.getResources().getDrawable(collocate.getPartImg2());
        drawable.setBounds(0,0,172,172);
        textView2.setCompoundDrawables(null,drawable,null,null);
        textView2.setText(collocate.getPartImg2Title());

        TextView textView3= (TextView) convertView.findViewById(R.id.collocate_img01_text03);
        drawable=convertView.getResources().getDrawable(collocate.getPartImg3());
        drawable.setBounds(0,0,172,172);
        textView3.setCompoundDrawables(null,drawable,null,null);
        textView3.setText(collocate.getPartImg3Title());

        TextView textView4= (TextView) convertView.findViewById(R.id.collocate_img01_text04);
        drawable=convertView.getResources().getDrawable(collocate.getPartImg4());
        drawable.setBounds(0,0,172,172);
        textView4.setCompoundDrawables(null,drawable,null,null);
        textView4.setText(collocate.getPartImg4Title());
        ImageView wholeImg2= (ImageView) convertView.findViewById(R.id.collocate_item_img2);
        wholeImg2.setImageResource(collocate.getWholeImg2());

        if(position%2==0){
            wholeImg1.setVisibility(View.VISIBLE);
            wholeImg2.setVisibility(View.GONE);
        }else {
            wholeImg1.setVisibility(View.GONE);
            wholeImg2.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
