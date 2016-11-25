package com.men_cloths.FragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.NewProductAdapter;
import com.men_cloths.model.NewProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class NewProductFragment extends Fragment{
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.listview_no_divider,null);
        listView= (ListView) view.findViewById(R.id.listview_no_divider);
        NewProductAdapter newProductAdapter=new NewProductAdapter(getActivity(),getdata());
        listView.setAdapter(newProductAdapter);
        ImageView imageView=new ImageView(getActivity());
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.mipmap.new_top);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        listView.addHeaderView(imageView);
        return view;
    }
    public List<NewProduct> getdata(){
        List<NewProduct> newProductList=new ArrayList<>();
        for(int i=0;i<10;i++){
            NewProduct newProduct=new NewProduct();
            if(i%2==0){
                newProduct.setImg1(R.mipmap.new_coat);
                newProduct.setContent1("长款连帽毛呢大衣");
                newProduct.setPrice1(899.00);
                newProduct.setImg2(R.mipmap.new_shirt);
                newProduct.setContent2("灯芯绒纯棉长袖衬衫");
                newProduct.setPrice2(219.00);
            }else {
                newProduct.setImg1(R.mipmap.new_trouser);
                newProduct.setContent1("与狼共舞休闲裤");
                newProduct.setPrice1(355.00);
                newProduct.setImg2(R.mipmap.new_shoes);
                newProduct.setContent2("保罗运动跑步鞋");
                newProduct.setPrice2(289.00);
            }
            newProductList.add(newProduct);
        }
        return newProductList;
    }
}
