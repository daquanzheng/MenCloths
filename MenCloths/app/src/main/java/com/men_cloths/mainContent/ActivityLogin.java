package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLogin extends Activity {
    TextView login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //login();
        register();

    }

//    public void login(){
//    login=(TextView) findViewById(R.id.textview_loginpage_login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(ActivityLogin.this,下一个页面的类);
//                startActivity(intent);
//            }
//        });
//    }
    public void register(){
        register=(TextView)findViewById(R.id.textview_loginpage_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityLogin.this,ActivityRegister.class);
                startActivity(intent);
            }
        });
    }
}
