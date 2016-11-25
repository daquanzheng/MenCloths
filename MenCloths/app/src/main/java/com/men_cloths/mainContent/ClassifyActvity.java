package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.ClassifyAdapter;
import com.men_cloths.model.Classify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ClassifyActvity extends Activity{

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mencloths_mall);
        listView= (ListView) findViewById(R.id.listview_mall);
        ClassifyAdapter classifyAdapter=new ClassifyAdapter(this,getData());
        listView.setAdapter(classifyAdapter);
    }

    public List<Classify> getData(){
        List<Classify> classifyList=new ArrayList<>();
        for(int i=0;i<12;i++){
            Classify classify=new Classify();
            switch (i%3){
                    case 0:
                        classify.setImgId1(R.mipmap.classify_t_shirt);
                        classify.setText1("T恤");
                        classify.setImgId2(R.mipmap.classify_shirt);
                        classify.setText2("衬衫");
                        break;
                case 1:
                    classify.setImgId1(R.mipmap.classify_hoodie);
                    classify.setText1("卫衣");
                    classify.setImgId2(R.mipmap.classify_trousers);
                    classify.setText2("裤装");
                    break;
                case 2:
                    classify.setImgId1(R.mipmap.classify_shoes);
                    classify.setText1("鞋子");
                    classify.setImgId2(R.mipmap.classify_accessories);
                    classify.setText2("配饰");
                    break;
            }
        classifyList.add(classify);
        }
        return classifyList;
    }
}

