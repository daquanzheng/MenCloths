package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.adapter.AdapterforAll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ThreadInfo extends Activity{

   private ListView listView;
   private List list;
   private LayoutInflater inflater;
   private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trend_zw);
        initView();
        View view=inflater.inflate(R.layout.trend_write_zw,null);

        listView.addHeaderView(view);
        createData();
        listSetAdapter();


    }

    public void initView(){
        listView= (ListView) findViewById(R.id.list_view_for_trend);
        list=new ArrayList();
        inflater=LayoutInflater.from(this);
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void listSetAdapter(){
        listView.setAdapter(new AdapterforAll(this, list) {
            @Override
            public View setView(int position, View convertView) {
                Holder holder=null;
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.trend_write_zhangwen_item,null);
                    holder=new Holder();
                    holder.imageView= (ImageView) convertView.findViewById(R.id.zhangwen_suai);
                    holder.title= (TextView) convertView.findViewById(R.id.title);
                    holder.contents= (TextView) convertView.findViewById(R.id.content);
                    holder.logo= (TextView) convertView.findViewById(R.id.logo);
                    holder.goumai= (TextView) convertView.findViewById(R.id.goumai);
                    convertView.setTag(holder);
                }else {
                    holder= (Holder) convertView.getTag();
                }


                return convertView;
            }
        });
    }
    /**
     * title        TextView
     content        TextView
     zhangwen_suai ImageView
     logo      TextView
     goumai    TextView
     * goumai
     * */
    private class Holder{
        ImageView imageView;
        TextView title,contents,logo,goumai;
    }


    public void createData(){
        list.add(1);
    }

}
