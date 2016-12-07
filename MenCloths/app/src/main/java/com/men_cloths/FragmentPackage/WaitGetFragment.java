package com.men_cloths.FragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.WaitgetAdapter;
import com.men_cloths.model.WaitGet;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitGetFragment extends Fragment{
    ListView listView;
    List<WaitGet> lists = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_daifukuan,null);
        listView = (ListView) view.findViewById(R.id.wait_all_listview);
        WaitgetAdapter waitgetAdapter = new WaitgetAdapter(getActivity(),getLists());
        listView.setAdapter(waitgetAdapter);
        return view;
    }
    public List<WaitGet> getLists(){
        for(int i=0;i<4;i++){
            WaitGet waitget = new WaitGet();
            lists.add(waitget);
        }
        return lists;
    }
}
