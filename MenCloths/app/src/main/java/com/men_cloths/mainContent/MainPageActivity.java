package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/25.
 */
public class MainPageActivity extends Activity{
    ViewHolder viewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_main);
        viewHolder = new ViewHolder();
        viewHolder.set_page = (ImageView) findViewById(R.id.set_page);
        viewHolder.enter = (Button) findViewById(R.id.denglu);
        viewHolder.addUser = (Button) findViewById(R.id.zhuce);
        viewHolder.dingdan = (TextView) findViewById(R.id.dingdan);
        viewHolder.youhuijuan = (TextView) findViewById(R.id.youhuijuan);
        viewHolder.address = (TextView) findViewById(R.id.address);
        viewHolder.message = (TextView) findViewById(R.id.message);
        viewHolder.collect = (TextView) findViewById(R.id.collect);
        viewHolder.pinpai = (TextView) findViewById(R.id.pinpai);
        viewHolder.zuji = (TextView) findViewById(R.id.zuji);
        viewHolder.helper = (TextView) findViewById(R.id.helper);
        viewHolder.zhanghu = (TextView) findViewById(R.id.zhanghu);

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
    }
    Intent intent;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.set_page:
                    intent = new Intent();
                    intent.setClass(MainPageActivity.this,Seting.class);
                    startActivity(intent);
                    break;
                case R.id.denglu:
                    break;
                case R.id.zhuce:
                    break;
                case R.id.dingdan:
                    break;
                case R.id.youhuijuan:
                    break;
                case R.id.address:
                    break;
                case R.id.message:
                    break;
                case R.id.collect:
                    break;
                case R.id.pinpai:
                    break;
                case R.id.zuji:
                    break;
                case R.id.helper:
                    break;
                case R.id.zhanghu:
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
