package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.MallAdapter;
import com.men_cloths.mainContent.MallInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MallFragment extends Fragment{
    ListView listView;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mencloths_mall,null);
        listView= (ListView) view.findViewById(R.id.listview_mall);
        editText= (EditText) view.findViewById(R.id.edit_mall);
        MallAdapter mallAdapter=new MallAdapter(getActivity(),getData());
        listView.setAdapter(mallAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), MallInfo.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
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
