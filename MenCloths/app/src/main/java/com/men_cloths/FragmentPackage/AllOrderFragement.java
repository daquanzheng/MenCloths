package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.mainContent.HomeActivity;
import com.men_cloths.mainContent.ShopInfo;

/**
 * Created by Administrator on 2016/11/28.
 */
public class AllOrderFragement extends Fragment{
    TextView goShopping;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_empty,null);
        goShopping = (TextView) view.findViewById(R.id.go_shop);
        goShopping.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//添加下划线
        goShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<HomeActivity.myButtonClick.length;i++){
                    if(i==1){
                        HomeActivity.myButtonClick[i]=true;
                    }else {
                        HomeActivity.myButtonClick[i]=false;
                    }
                }
                getActivity().finish();
            }
        });
        return view;
    }
}
