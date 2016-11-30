package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.ShowAdapter;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ShowFragment extends Fragment{
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.listview_have_divider,null);
        listView= (ListView) view.findViewById(R.id.listview_have_divider);
        ShowAdapter showAdapter=new ShowAdapter(getActivity(),getData());
        listView.setAdapter(showAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ShopInfo.class);
                        getActivity().startActivity(intent);
            }
        });
        return view;
    }
    public List<Show> getData(){
        List<Show> showList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Show show=new Show();
            if(i%2==0){
                show.setTitleImgId(R.mipmap.home_show_title_img1);
                show.setTitle("戴手表的兔子");
                show.setContent("入秋了，好好爱自己");
                show.setPartImgId1(R.mipmap.home_show_part_img1);
                show.setPartImgId2(R.mipmap.home_show_part_img2);
            }else {
                show.setTitleImgId(R.mipmap.home_show_title_img2);
                show.setTitle("大头怪物");
                show.setContent("狂拽炫酷吊炸天");
                show.setPartImgId1(R.mipmap.home_show_part_img3);
                show.setPartImgId2(R.mipmap.home_show_part_img4);
            }
            showList.add(show);
        }
        return showList;
    }
}
