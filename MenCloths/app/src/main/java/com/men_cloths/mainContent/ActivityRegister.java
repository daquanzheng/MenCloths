package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.model.Register;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/10/30.
 */
public class ActivityRegister extends Activity {
    private TextView register;
    private MessageVerification verification;
    private   EditText tel,passwd,ver;
    boolean isverification=false;
    private  TextView getVer;
    boolean isClick=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        register();
    }

    public void init(){
        verification=new MessageVerification(this,handler);
        verification.init();
        tel= (EditText) findViewById(R.id.tel);
        passwd= (EditText) findViewById(R.id.passwd);
        ver= (EditText) findViewById(R.id.verification);
        getVer= (TextView) findViewById(R.id.get_verifications_tel);
        getVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(isClick){
                  isClick=false;
                  verification.send(tel.getText().toString());
                  canClick();
               }
            }
        });
    }

    public void register(){
        register=(TextView)findViewById(R.id.textview_register_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isverification){
                    verification.commint(ver.getText().toString());
                }else{
                    Toast.makeText(ActivityRegister.this,"验证码输入错误",Toast.LENGTH_SHORT).show();
                    isverification=false;

                }
            }
        });
    }
    Handler handler=new Handler(){

        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    Intent intent=new Intent(ActivityRegister.this,ActivityLoginSuccess.class);
                    startActivity(intent);
                    break;
                case -1:
                    Toast.makeText(ActivityRegister.this,"注册失败"+message.obj,Toast.LENGTH_SHORT).show();
                    break;
                case 1234:
                    int event=message.arg1;
                    int result=message.arg2;
                    Object data=message.obj;
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        Log.i("hhh","----------------------回调完成");

                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //提交验证码成功
                            Log.i("hhh","----------------------提交验证码成功");
                            isverification=true;
                            Register.register(handler,tel.getText().toString(),passwd.getText().toString());

                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //获取验证码成功
                            Log.i("hhh","----------------------获取验证码成功");

                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                            Log.i("hhh","----------------------返回支持发送验证码的国家列表");
                        }
                    }else{
                        ((Throwable)data).printStackTrace();
                        Toast.makeText(ActivityRegister.this,"验证码验证失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case -99:
                    Toast.makeText(ActivityRegister.this,"账户或者密码不能为空",Toast.LENGTH_SHORT).show();
                    break;
                case 12345:
                    if(message.arg1==0){
                        getVer.setText("获取验证码");
                    }else {
                        getVer.setText("重新获取"+message.arg1);
                    }
                    break;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        verification.cancellation();
    }

    public void canClick(){
        new Thread(){
            public void run(){
                int i=60;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message=Message.obtain();
                    message.what=12345;
                    message.arg1=i;
                    handler.sendMessage(message);
                    if(i==0){
                        isClick=true;
                        break;
                    }
                    i--;
                }
            }
        }.start();
    }
}
