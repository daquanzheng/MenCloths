package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.MallAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MallActivity extends Activity{
    ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mencloths_mall);
        listView= (ListView) findViewById(R.id.listview_mall);
        editText= (EditText) findViewById(R.id.edit_mall);
        MallAdapter mallAdapter=new MallAdapter(this,getData());
        listView.setAdapter(mallAdapter);
    }
    public List<Integer> getData(){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<12;i++){
            switch (i%4){
                case 0:
                    list.add(R.mipmap.mall01);
                    break;
                case 1:
                    list.add(R.mipmap.mall02);
                    break;
                case 2:
                    list.add(R.mipmap.mall03);
                    break;
                case 3:
                    list.add(R.mipmap.mall04);
                    break;
            }
        }
    return list;
    }
}
