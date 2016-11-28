package com.men_cloths.FragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.CollocateAdapter;
import com.men_cloths.model.Collocate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CollocateFragment extends Fragment{
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mencloths_mall,null);
        listView= (ListView)view.findViewById(R.id.listview_mall);
        CollocateAdapter collocateAdapter=new CollocateAdapter(getActivity(),getData());
        listView.setAdapter(collocateAdapter);
        return view;
    }
    public List<Collocate> getData(){
        List<Collocate> collocateList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Collocate collocate=new Collocate();
            if(i%2==0){
                collocate.setWholeImg1(R.mipmap.collocate_img01);
                collocate.setPartImg1(R.mipmap.collocate_img01_01);
                collocate.setPartImg1Title("牛仔夹克外套");
                collocate.setPartImg2(R.mipmap.collocate_img01_02);
                collocate.setPartImg2Title("白色衬衫");
                collocate.setPartImg3(R.mipmap.collocate_img01_03);
                collocate.setPartImg3Title("白色牛仔裤");
                collocate.setPartImg4(R.mipmap.collocate_img01_04);
                collocate.setPartImg4Title("深蓝色乐福鞋");
            }else {
                collocate.setWholeImg2(R.mipmap.collocate_img02);
                collocate.setPartImg1(R.mipmap.collocate_img02_01);
                collocate.setPartImg1Title("休闲呢子大衣");
                collocate.setPartImg2(R.mipmap.collocate_img02_02);
                collocate.setPartImg2Title("宽檐大檐帽子");
                collocate.setPartImg3(R.mipmap.collocate_img02_03);
                collocate.setPartImg3Title("灰色阔腿裤");
                collocate.setPartImg4(R.mipmap.collocate_img02_04);
                collocate.setPartImg4Title("黑色系带皮鞋");
            }
            collocateList.add(collocate);
        }
        return collocateList;
    }
}
