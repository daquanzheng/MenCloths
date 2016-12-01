package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.model.Login;

import java.net.URL;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLogin extends Activity {
    TextView login;
    TextView register;
    ImageView tencent,wechat,sina,taobao;
    EditText  tel,passwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定控件
        login=(TextView) findViewById(R.id.textview_loginpage_login);
        register=(TextView)findViewById(R.id.textview_loginpage_register);
        tencent = (ImageView) findViewById(R.id.tencent);
        wechat = (ImageView) findViewById(R.id.wechat);
        sina = (ImageView) findViewById(R.id.sina);
        taobao = (ImageView) findViewById(R.id.taobao);
        tel= (EditText) findViewById(R.id.tel);
        passwd= (EditText) findViewById(R.id.passwd);
        //绑定监听事件
        login.setOnClickListener(onClickListener);
        register.setOnClickListener(onClickListener);
        tencent.setOnClickListener(onClickListener);
        wechat.setOnClickListener(onClickListener);
        sina.setOnClickListener(onClickListener);
        taobao.setOnClickListener(onClickListener);
    }
    Intent intent=new Intent();
    URL url;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textview_loginpage_login:
                    Login.login(tel.getText().toString(),passwd.getText().toString(),ActivityLogin.this,handler);
                    //intent.setClass(ActivityLogin.this,MyMainpageInActivity.class);
                   // startActivity(intent);
                    break;
                case R.id.textview_loginpage_register:
                    intent.setClass(ActivityLogin.this,ActivityRegister.class);
                    startActivity(intent);
                    break;
                case R.id.tencent:
                    break;
                case R.id.wechat:
                    break;
                case R.id.sina:
                    break;
                case R.id.taobao:
//                    try {
//                        url = new URL("http://api.m.taobao.com/rest/api2.do");
//                        HttpURLConnection httpURL = (HttpURLConnection) url.openConnection();
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    break;
            }
        }
    };

    Handler handler=new Handler(){

        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                     intent.setClass(ActivityLogin.this,MyMainpageInActivity.class);
                     startActivity(intent);
                    break;
                case -1:
                    Toast.makeText(ActivityLogin.this,"你的账号或者密码不正确",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
