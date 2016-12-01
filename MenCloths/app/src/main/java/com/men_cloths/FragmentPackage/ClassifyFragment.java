package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.ClassifyAdapter;
import com.men_cloths.mainContent.ClassifyInfo;
import com.men_cloths.mainContent.SearchActivity;
import com.men_cloths.model.Classify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class ClassifyFragment extends Fragment{
    ListView listView;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mencloths_mall,null);
        listView= (ListView)view.findViewById(R.id.listview_mall);
        editText= (EditText) view.findViewById(R.id.edit_mall);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
        ClassifyAdapter classifyAdapter=new ClassifyAdapter(getActivity(),getData());
        listView.setAdapter(classifyAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView= (ImageView) view.findViewById(R.id.classify_item_img1);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ClassifyInfo.class);
                        getActivity().startActivity(intent);
                    }
                });
                ImageView imageView1= (ImageView) view.findViewById(R.id.classify_item_img2);
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ClassifyInfo.class);
                        getActivity().startActivity(intent);
                    }
                });
            }
        });
        return view;
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
