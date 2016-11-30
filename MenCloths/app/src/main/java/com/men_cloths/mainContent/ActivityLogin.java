package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;


import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.men_cloths.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLogin extends Activity {
    TextView login;
    TextView register;
    ImageView tencent,wechat,sina,taobao,showpassword,camera;
    EditText phonenumb,password;
    int i=0;
    PopupWindow popupWindow;
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
        showpassword=(ImageView)findViewById(R.id.img_login_showpassword);
        camera=(ImageView)findViewById(R.id.img_login_cawera);
        phonenumb=(EditText)findViewById(R.id.edit_login_phonenumb);
        password=(EditText)findViewById(R.id.edit_login_password);
        //绑定监听事件
        showpassword.setOnClickListener(onClickListener);
        camera.setOnClickListener(onClickListener);
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
                    intent.setClass(ActivityLogin.this,MyMainpageInActivity.class);
                    startActivity(intent);
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
                case R.id.img_login_cawera:
                    CreatePopuwindow();
                    Button camera=(Button)findViewById(R.id.btn_camera);
                    Button dismiss=(Button)findViewById(R.id.btn_longin_dismiss);
                    if(dismiss!=null){
                        dismiss.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                    }

                    break;
                case R.id.img_login_showpassword:
                    i++;
                    if(i%2==1){
                        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }else{
                        password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                    break;
            }
        }
    };
    public void CreatePopuwindow(){
        popupWindow=new PopupWindow(getLayoutInflater().inflate(R.layout.popuwindow_login_camera,null), LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(camera, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

    }
}
