package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.ActivityManager;
import com.men_cloths.model.OFF;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Seting extends Activity{
    private LinearLayout linearLayout,message,interchanged,personInfo;//账户
    private TextView exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seting);
        setOnclit();
        Intent intent=new Intent(this,GetInfo.class);
        startService(intent);
        ActivityManager.getActivityManager().add(this);

    }

    public void setOnclit(){
        findViewById(R.id.come_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout= (LinearLayout) findViewById(R.id.account);//账户
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,Account.class);
                startActivity(intent);
            }
        });
        message= (LinearLayout) findViewById(R.id.message);//消息
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,MessAge.class);
                startActivity(intent);
            }
        });
        interchanged= (LinearLayout) findViewById(R.id.interchanged);//设置
        interchanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,Interchangeable.class);
                startActivity(intent);
            }
        });

        personInfo= (LinearLayout) findViewById(R.id.personInfo);//个人资料
        personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,PersonInfo.class);
                startActivity(intent);
            }
        });
        exit= (TextView) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OFF.unRegister(Seting.this,handler);

            }
        });
    }
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            ActivityManager.getActivityManager().exit();
        }
    };

}
