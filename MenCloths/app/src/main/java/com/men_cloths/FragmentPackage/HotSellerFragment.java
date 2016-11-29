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
import com.men_cloths.adapter.HotSellerAdapter;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.HotSeller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class HotSellerFragment extends Fragment{
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.listview_no_divider,null);
        listView= (ListView) view.findViewById(R.id.listview_no_divider);
        HotSellerAdapter hotSellerAdapter=new HotSellerAdapter(getActivity(),getdata());
        listView.setAdapter(hotSellerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ShopInfo.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
    public List<HotSeller> getdata(){
        List<HotSeller> hotSellerList=new ArrayList<>();
        for(int i=0;i<12;i++){
            HotSeller hotSeller=new HotSeller();
            if(i%4==0){
                hotSeller.setHotImg(R.mipmap.home_hotseller_shirt1);
                hotSeller.setHotContent(getResources().getText(R.string.hot_seller_item_title1).toString());
                hotSeller.setHotPrice(299.0);
            }else if(i%4==1){
                hotSeller.setHotImg(R.mipmap.home_hot_seller_shirt2);
                hotSeller.setHotContent(getResources().getText(R.string.hot_seller_item_title2).toString());
                hotSeller.setHotPrice(476.0);
            }else if(i%4==2){
                hotSeller.setHotImg(R.mipmap.home_hotseller_trouser);
                hotSeller.setHotContent(getResources().getText(R.string.hot_seller_item_title3).toString());
                hotSeller.setHotPrice(189.0);
            }else if(i%4==3){
                hotSeller.setHotImg(R.mipmap.home_hot_seller_shoes);
                hotSeller.setHotContent(getResources().getText(R.string.hot_seller_item_title4).toString());
                hotSeller.setHotPrice(268.0);
            }
            hotSellerList.add(hotSeller);
        }
        return hotSellerList;
    }
}
