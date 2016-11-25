package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.AdapterforAll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class ThreadInfo extends Activity{

    ListView listView;
    List list;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trend_write_zw);
        initView();


    }

    public void initView(){
        listView= (ListView) findViewById(R.id.list_view_for_trend);
        list=new ArrayList();
        inflater=LayoutInflater.from(this);
    }

    public void listSetAdapter(){
        listView.setAdapter(new AdapterforAll(this, list) {
            @Override
            public View setView(int position, View convertView) {



                return null;
            }
        });
    }
    private class Holder{
        ImageView imageView;
    }
}
