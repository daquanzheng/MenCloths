package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.TrendAdapter;
import com.men_cloths.model.Trend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public class TrendActivity extends Activity{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mencloths_home_fashion);
        listView= (ListView) findViewById(R.id.listview_trend);
        TrendAdapter trendAdapter=new TrendAdapter(this,getdata());
        listView.setAdapter(trendAdapter);
        View view=new View(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,12
        );
        view.setLayoutParams(layoutParams);
        view.setBackgroundResource(R.color.spacing_simple);
        listView.addHeaderView(view);
    }
        public List<Trend> getdata(){
            List<Trend> trendList=new ArrayList<>();
            for(int i=0;i<10;i++){
                Trend trend=new Trend();
                if(i%2==0){
                    trend.setImg(R.mipmap.img_fashion_up);
                    trend.setTitle("2016年秋冬运动新品速递");
                }else {
                    trend.setImg(R.mipmap.img_fashion_down);
                    trend.setTitle("2016年时尚秋装推荐展示");
                }
                trendList.add(trend);
            }
            return  trendList;
        }
}
