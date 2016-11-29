package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.men_cloths.R;
import com.men_cloths.adapter.MyGridViewAdapter;
import com.men_cloths.model.MyGridView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
public class SearchActivity extends Activity{
    MyGridView myGridViewTop;
    MyGridView myGridViewBottom;
    EditText editText;
    ImageView imageView;
    List<String> stringList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_gridview);
        myGridViewTop= (MyGridView) findViewById(R.id.search_top_gridView);
        myGridViewBottom= (MyGridView) findViewById(R.id.search_bottom_gridView);
        editText= (EditText) findViewById(R.id.search_edit);
        imageView=(ImageView) findViewById(R.id.search_img);
        stringList.add("包");
        stringList.add("Adidas");
        stringList.add("内裤");
        stringList.add("手表");
        stringList.add("H&M");
        final MyGridViewAdapter myGridViewAdapterBottom=new MyGridViewAdapter(this,stringList);
        myGridViewBottom.setAdapter(myGridViewAdapterBottom);
        final List<String> list=new ArrayList<>();
        final MyGridViewAdapter myGridViewAdapterTop=new MyGridViewAdapter(this,list);
        myGridViewTop.setAdapter(myGridViewAdapterTop);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                if(editText.getText()!=null&&editText.getText().length()>0){
                    stringList.add(editText.getText().toString());
                    myGridViewAdapterBottom.notifyDataSetInvalidated();
                    for(int i=0;i<stringList.size();i++){
                        if(editText.getText().length()>0&&editText.getText()!=null&&editText.getText().toString().equals(stringList.get(i))){
                            text=editText.getText().toString();
                            list.add(text);
                        }
                        myGridViewAdapterTop.notifyDataSetInvalidated();
                    }

                }
            }
        });


    }


}