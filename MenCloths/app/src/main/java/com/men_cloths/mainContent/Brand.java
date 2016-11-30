package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.men_cloths.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Brand extends Activity {

    private List<SS> list;
    private ImageView back;
    private ListView listView;
    private LayoutInflater inflater;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand);
        init();
        listView.setAdapter(baseAdapter);
        setOnclicForListView();

    }

    public void init(){
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(listener);
        listView= (ListView) findViewById(R.id.list_view);
        inflater=LayoutInflater.from(this);
        create();
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=inflater.inflate(R.layout.brand_item,null);
                holder=new ViewHolder();
                holder.imageView= (ImageView) convertView.findViewById(R.id.imageView3);
                holder.textView= (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    };
/**
 * imageView3
 商家名 textView
 取消关注 off
 */

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    class SS{
        String image;
        String name;
        public SS(String image,String name){
            this.image=image;
            this.name=name;
        }
    }

   public void create(){
       list=new ArrayList();
       SS ss=new SS("","商家名字");
       list.add(ss);
       list.add(ss);
   }

    public void setOnclicForListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                TextView textView= (TextView) view.findViewById(R.id.off);
                Brand.this.position=position;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(Brand.this.position);
                        baseAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
