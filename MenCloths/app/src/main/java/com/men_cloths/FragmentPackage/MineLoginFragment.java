package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.mainContent.Account;
import com.men_cloths.mainContent.Brand;
import com.men_cloths.mainContent.Collection;
import com.men_cloths.mainContent.Coupon;
import com.men_cloths.mainContent.DingDanActivity;
import com.men_cloths.mainContent.FootprintActivity;
import com.men_cloths.mainContent.MessAge;
import com.men_cloths.mainContent.MyAddressActivity;
import com.men_cloths.mainContent.MyHelperActivity;
import com.men_cloths.mainContent.Seting;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MineLoginFragment extends Fragment{

    ViewHolder viewHolder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.wode_main_in,null);
        viewHolder = new ViewHolder();
        viewHolder.set_page = (ImageView)view.findViewById(R.id.set_page);
        viewHolder.dingdan = (TextView) view.findViewById(R.id.dingdan);
        viewHolder.youhuijuan = (TextView)view.findViewById(R.id.youhuijuan);
        viewHolder.address = (TextView) view.findViewById(R.id.address);
        viewHolder.message = (TextView) view.findViewById(R.id.message);
        viewHolder.collect = (TextView) view.findViewById(R.id.collect);
        viewHolder.pinpai = (TextView) view.findViewById(R.id.pinpai);
        viewHolder.zuji = (TextView) view.findViewById(R.id.zuji);
        viewHolder.helper = (TextView)view.findViewById(R.id.helper);
        viewHolder.zhanghu = (TextView) view.findViewById(R.id.zhanghu);

        viewHolder.set_page.setOnClickListener(onClickListener);
        viewHolder.dingdan.setOnClickListener(onClickListener);
        viewHolder.youhuijuan.setOnClickListener(onClickListener);
        viewHolder.address.setOnClickListener(onClickListener);
        viewHolder.message.setOnClickListener(onClickListener);
        viewHolder.collect.setOnClickListener(onClickListener);
        viewHolder.pinpai.setOnClickListener(onClickListener);
        viewHolder.zuji.setOnClickListener(onClickListener);
        viewHolder.helper.setOnClickListener(onClickListener);
        viewHolder.zhanghu.setOnClickListener(onClickListener);



        return view;
    }
    Intent intent = new Intent();
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.set_page:
                    intent.setClass(getActivity(),Seting.class);
                    startActivity(intent);
                    break;
                case R.id.dingdan:
                    intent.setClass(getActivity(),DingDanActivity.class);
                    startActivity(intent);
                    break;
                case R.id.youhuijuan:
                    intent.setClass(getActivity(),Coupon.class);
                    startActivity(intent);
                    break;
                case R.id.address:
                    intent.setClass(getActivity(),MyAddressActivity.class);
                    startActivity(intent);
                    break;
                case R.id.message:
                    intent.setClass(getActivity(),MessAge.class);
                    startActivity(intent);
                    break;
                case R.id.collect:
                    intent.setClass(getActivity(),Collection.class);
                    startActivity(intent);
                    break;
                case R.id.pinpai:
                    intent.setClass(getActivity(),Brand.class);
                    startActivity(intent);
                    break;
                case R.id.zuji:
                    intent.setClass(getActivity(),FootprintActivity.class);
                    startActivity(intent);
                    break;
                case R.id.helper:
                    intent.setClass(getActivity(),MyHelperActivity.class);
                    startActivity(intent);
                    break;
                case R.id.zhanghu:
                    intent.setClass(getActivity(),Account.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    public class ViewHolder{
        ImageView set_page;
        TextView dingdan;
        TextView youhuijuan;
        TextView address;
        TextView message;
        TextView collect;
        TextView pinpai;
        TextView zuji;
        TextView helper;
        TextView zhanghu;
    }
}
