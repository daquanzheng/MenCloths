package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.model.Constants;
import com.men_cloths.model.Login;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLogin extends Activity {
   private TextView login;
   private TextView register;
    ImageView tencent,wechat,sina,taobao;
   private EditText  tel,passwd;
   private SsoHandler mSsoHandler;
   private Oauth2AccessToken mAccessToken;
   private String touke,uid;


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
                    AuthInfo mAuthInfo = new AuthInfo(ActivityLogin.this, Constants.APP_KEY, Constants.REDIRECT_URL,"");
                    mSsoHandler= new SsoHandler(ActivityLogin.this, mAuthInfo);
                    mSsoHandler.authorize(new AuthListener());
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
                case 666:
                    intent.setClass(ActivityLogin.this,MyMainpageInActivity.class);
                    startActivity(intent);
                    break;
                case 555:
                    Toast.makeText(ActivityLogin.this,"第三方登录失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            //Toast.makeText(MainActivity.this,"我已经顺利回调",Toast.LENGTH_SHORT).show();
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //Toast.makeText(MainActivity.this,"我已经顺利回调"+mAccessToken,Toast.LENGTH_SHORT).show();

            // String mcode = values.getString("code", "");



            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                // AccessTokenKeeper.writeAccessToken(MainActivity.this, mAccessToken);
                Log.i("hahaha",mAccessToken+"------->548487");
                touke=mAccessToken.getToken();
                uid=mAccessToken.getUid();
                Toast.makeText(ActivityLogin.this,"登录成功", Toast.LENGTH_SHORT).show();



                new Thread(){
                    public void run(){
                        regForOhther(touke);
                    }
                }.start();



            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");
            }
        }


        @Override
        public void onCancel() {
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }


    public void regForOhther(String token){


        String url="http://139.199.196.199/index.php/home/index/three?token="+token;
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode()==200){
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String tel=reader.readLine();
                SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("token",touke);
                editor.putString("tel",tel);
                editor.commit();
                Message message=Message.obtain();
                message.what=666;
                handler.sendMessage(message);

            }else {
                Message message=Message.obtain();
                message.what=555;
                handler.sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
        }


    }


}
