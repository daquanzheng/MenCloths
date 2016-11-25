package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/24.
 */

public class MessAge extends Activity{


    /**
     *
     *  需要确认信息的类型，从而选择加载不同类型的数据
     *    如果查看了消息，那么通过改变
     *
     *
     *
     * */

    Boolean isemptyForNotification=true;
    Boolean isemptyForTransaction=true;
    LinearLayout linearLayout;
    LayoutInflater inflater;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        inflater=LayoutInflater.from(this);
        linearLayout= (LinearLayout) findViewById(R.id.linear_layout);
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        addView();
        /**
         * 通过判断消息是否读完，选择重新加载
         *
         *
         * */
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**
         *
         * 清除布局中的控件
         *
         * */
        linearLayout.removeAllViews();
        isemptyForTransaction=false;
    }

    public void addView(){
        /**
         * 两种类型的消息，如果一种为空显示另外的一种消息
         * 如果两者都为空则不显示
         * */
        if(isemptyForNotification && !isemptyForTransaction){
           linearLayout.addView(inflater.inflate(R.layout.message_notification_item,null));
        }else if(isemptyForTransaction && !isemptyForNotification){
            linearLayout.addView(inflater.inflate(R.layout.message_transaction_item,null));
        }else if(isemptyForNotification&&isemptyForTransaction){
            linearLayout.addView(inflater.inflate(R.layout.message_notification_item,null));
            linearLayout.addView(inflater.inflate(R.layout.message_transaction_item,null));
        }
    }
}
