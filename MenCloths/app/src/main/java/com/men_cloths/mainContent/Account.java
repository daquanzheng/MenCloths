package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/25.
 */

public class Account extends Activity{

    private TextView bindTelNumber,telNumber;
    private  LinearLayout linearLayout;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        SharedPreferences sharedPreferences=getSharedPreferences("hello",MODE_PRIVATE);
        String s= sharedPreferences.getString("tel","");

        bindTelNumber= (TextView) findViewById(R.id.change_tel_number);
        telNumber= (TextView) findViewById(R.id.tel_number);

        if(s==null){
            bindTelNumber.setText("绑定手机号");
            telNumber.setText("");
        }else{
            telNumber.setText(s);
            bindTelNumber.setText("修改手机号码");
        }
        telNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Account.this,BindtoTel.class);
                startActivity(intent);

            }
        });

        linearLayout= (LinearLayout) findViewById(R.id.changed_password);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Account.this,SoftPassword.class);
                startActivity(intent);
            }
        });
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //Intent intent=getIntent();
        //telNumber.setText(intent.getStringExtra("tel"));
       // Log.i("hmp",intent.getStringExtra("tel"));
        SharedPreferences sharedPreferences=getSharedPreferences("hello",MODE_PRIVATE);
        String s= sharedPreferences.getString("tel","");
        telNumber.setText(s);
        bindTelNumber.setText("修改手机号码");

    }
}
