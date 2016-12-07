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
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.adapter.CollocateAdapter;
import com.men_cloths.mainContent.SearchActivity;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.Collocate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CollocateFragment extends Fragment{
    private  ListView listView;
    private  EditText editText;
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
        CollocateAdapter collocateAdapter=new CollocateAdapter(getActivity(),getData());
        listView.setAdapter(collocateAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView1= (TextView) view.findViewById(R.id.collocate_img01_text01);
                TextView textView2= (TextView) view.findViewById(R.id.collocate_img01_text02);
                TextView textView3= (TextView) view.findViewById(R.id.collocate_img01_text03);
                TextView textView4= (TextView) view.findViewById(R.id.collocate_img01_text04);
                textView1.setOnClickListener(onClickListener);
                textView2.setOnClickListener(onClickListener);
                textView3.setOnClickListener(onClickListener);
                textView4.setOnClickListener(onClickListener);
            }
        });
        return view;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.collocate_img01_text01:
                    Intent intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
                case R.id.collocate_img01_text02:
                    intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
                case R.id.collocate_img01_text03:
                    intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
                case R.id.collocate_img01_text04:
                    intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
            }
        }
    };
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
