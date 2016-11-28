package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.mainContent.ActivityLogin;
import com.men_cloths.mainContent.ActivityRegister;

/**
 * Created by Administrator on 2016/11/25.
 */
public class MineFragment extends Fragment{
    ViewHolder viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wode_main,null);
        viewHolder = new ViewHolder();
        viewHolder.enter = (Button) view.findViewById(R.id.denglu);
        viewHolder.addUser = (Button)view.findViewById(R.id.zhuce);
        viewHolder.dingdan = (TextView) view.findViewById(R.id.dingdan);
        viewHolder.youhuijuan = (TextView) view.findViewById(R.id.youhuijuan);
        viewHolder.address = (TextView) view.findViewById(R.id.address);
        viewHolder.message = (TextView) view.findViewById(R.id.message);
        viewHolder.collect = (TextView) view.findViewById(R.id.collect);
        viewHolder.pinpai = (TextView) view.findViewById(R.id.pinpai);
        viewHolder.zuji = (TextView) view.findViewById(R.id.zuji);
        viewHolder.helper = (TextView) view.findViewById(R.id.helper);
        viewHolder.zhanghu = (TextView) view.findViewById(R.id.zhanghu);

        viewHolder.set_page.setOnClickListener(onClickListener);
        viewHolder.enter.setOnClickListener(onClickListener);
        viewHolder.addUser.setOnClickListener(onClickListener);
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
                case R.id.denglu:
                    intent.setClass(getActivity(),ActivityLogin.class);
                    startActivity(intent);
                    break;
                case R.id.zhuce:
                    intent.setClass(getActivity(),ActivityRegister.class);
                    startActivity(intent);
                    break;
                case R.id.dingdan:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.youhuijuan:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.address:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.message:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.collect:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pinpai:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.zuji:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.helper:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.zhanghu:
                    Toast.makeText(getActivity(),"亲，你还没有登录哦",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    public class ViewHolder{
        ImageView set_page;
        Button enter;
        Button addUser;
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
