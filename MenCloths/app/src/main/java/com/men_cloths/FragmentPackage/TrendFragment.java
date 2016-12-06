package com.men_cloths.FragmentPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.TrendAdapter;
import com.men_cloths.model.Trend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class TrendFragment extends Fragment{
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.listview_have_divider,null);
        listView= (ListView) view.findViewById(R.id.listview_have_divider);
        TrendAdapter trendAdapter=new TrendAdapter(getActivity(),getdata());

        View headview=new View(getActivity());
        AbsListView.LayoutParams layoutParams=new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,12
        );
        headview.setLayoutParams(layoutParams);
        headview.setBackgroundResource(R.color.spacing_simple);
        listView.addHeaderView(headview);
        listView.setAdapter(trendAdapter);
        return view;
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
