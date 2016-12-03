package com.men_cloths.mainContent;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.men_cloths.FragmentPackage.MyFrament;
import com.men_cloths.FragmentPackage.MyFramentToTwo;
import com.men_cloths.R;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/10/30.
 */

public class BindtoTel extends Activity{

    private  LinearLayout linearLayout;
    private  FragmentManager manager;
    private  MyFrament myFrament;
    private  MyFramentToTwo myFrament2;
    private MessageVerification verification;
    public static String tel="";
    private ImageView back;
    int flags=0;
    private boolean isclick=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_tel_phong);
         verification=new MessageVerification(this,handler);
         verification.init();
         linearLayout= (LinearLayout) findViewById(R.id.linear_layout);
         myFrament=new MyFrament();

         manager=getFragmentManager();
         FragmentTransaction transaction=manager.beginTransaction();
         transaction.add(R.id.linear_layout,myFrament);
         transaction.commit();
         myFrament2=new MyFramentToTwo();
         myFrament.setOnMyFramentclikListener(new MyFrament.listener() {
            @Override
            public void onclik() {
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.linear_layout,myFrament2);
                transaction.commit();
                flags=1;
            }
        });
        myFrament.addMyTextChangedListener(new MyFrament.MyTexychanged() {
            @Override
            public void afterTextChanged(Editable s) {
                tel=s.toString();
            }
        });
        if (myFrament2!=null)
        myFrament2.setOnMyFramentclikListener(new MyFramentToTwo.listener() {
            @Override
            public void onclik() {

                if(myFrament2.editText!=null){
                    if(BindtoTel.this.myFrament2.editText.getText().toString()!=null)
                        verification.commint(BindtoTel.this.myFrament2.editText.getText().toString());
                }
//                Intent intent=new Intent(BindtoTel.this,BindSucessfuly.class);
//                intent.putExtra("tel",tel);
//                startActivity(intent);
            }

            @Override
            public void send() {
               if (isclick){
                   verification.send(tel);
                   isclick=false;
                   new Thread(){
                       public void run(){
                           int i=60;
                           while (true){
                               try {
                                   Thread.sleep(1000);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                               Message message=Message.obtain();
                               message.what=1;
                               message.arg1=i;
                               i--;
                               if(i==0){
                                   message.what=2;
                                   handler.sendMessage(message);
                                   break;
                               }
                               handler.sendMessage(message);

                           }
                       }
                   }.start();
               }
            }
        });
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(flags==1){
                   FragmentTransaction transaction=manager.beginTransaction();
                   transaction.replace(R.id.linear_layout,myFrament);
                   transaction.commit();
                   flags=0;
               }else{
                   Intent intent=new Intent(BindtoTel.this,Account.class);
                   startActivity(intent);
               }
            }
        });
    }

    private Handler handler=new Handler(){

        public void handleMessage(Message message){

           if (message.what==1234){
               int event=message.arg1;
               int result=message.arg2;
               Object data=message.obj;
               if (result == SMSSDK.RESULT_COMPLETE) {
                   //回调完成
                   Log.i("hhh","----------------------回调完成");

                   if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                       //提交验证码成功
                       Log.i("hhh","----------------------提交验证码成功");
                       Intent intent=new Intent(BindtoTel.this,BindSucessfuly.class);
                       intent.putExtra("tel",tel);
                       startActivity(intent);

                   }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                       //获取验证码成功
                       Log.i("hhh","----------------------获取验证码成功");

                   }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                       //返回支持发送验证码的国家列表
                       Log.i("hhh","----------------------返回支持发送验证码的国家列表");
                   }
               }else{
                   ((Throwable)data).printStackTrace();
               }
           }
            if(message.what==1){
                myFrament2.send.setText("重新发送"+message.arg1);
            }

            if(message.what==2){
                isclick=true;
                myFrament2.send.setText("发送");
            }

        }

    };
}
