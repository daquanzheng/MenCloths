package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Seting extends Activity{
    LinearLayout linearLayout,message,interchanged,personInfo;//账户

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seting);
        findViewById(R.id.come_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout= (LinearLayout) findViewById(R.id.account);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,Account.class);
                startActivity(intent);
            }
        });
        message= (LinearLayout) findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,MessAge.class);
                startActivity(intent);
            }
        });
        interchanged= (LinearLayout) findViewById(R.id.interchanged);
        interchanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,Interchangeable.class);
                startActivity(intent);
            }
        });

        personInfo= (LinearLayout) findViewById(R.id.personInfo);
        personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seting.this,PersonInfo.class);
                startActivity(intent);
            }
        });

    }
}
