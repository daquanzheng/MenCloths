package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/10/25.
 */

public class Account extends Activity{

    private TextView bindTelNumber,telNumber;
    private  LinearLayout linearLayout;
    private ImageView back;
    private TextView user_name;
    private   String name,token,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        init();
        SharedPreferences sharedPreferences=getSharedPreferences("hello",MODE_PRIVATE);
        String s= sharedPreferences.getString("tel","");

        bindTelNumber= (TextView) findViewById(R.id.change_tel_number);
        //telNumber= (TextView) findViewById(R.id.tel_number);

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
              if(tel.length()!=11){
                  Toast.makeText(Account.this,"该功能暂时不支持第三方用户",Toast.LENGTH_SHORT).show();
              }else {
                  Intent intent=new Intent(Account.this,BindtoTel.class);
                  startActivity(intent);
              }

            }
        });

        linearLayout= (LinearLayout) findViewById(R.id.changed_password);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tel.length()!=11){
                    Toast.makeText(Account.this,"该功能暂时不支持第三方用户",Toast.LENGTH_SHORT).show();
                    return;
                }
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
    protected void onResume() {
        super.onResume();
        if(tel.length()==11){
            getinfo();
        }else {
            user_name.setText(msg);
        }


    }
    private String msg;

    private  void init(){
        user_name= (TextView) findViewById(R.id.user_name);
        telNumber= (TextView) findViewById(R.id.tel_number);
        SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
        token=preferences.getString("token","");
        tel=preferences.getString("tel","");
        msg=preferences.getString("message","");
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


    public  void getinfo(){
         new Thread(){
             public void run(){
                 String url="http://804904.ichengyun.net/index.php/home/index/userinfo?token="+token+"&tel="+tel;
                 HttpURLConnection connection=null;
                 BufferedReader reader=null;

                 try {
                     connection= (HttpURLConnection) new URL(url).openConnection();
                     connection.setConnectTimeout(5000);
                     connection.setRequestMethod("GET");
                     connection.connect();

                     if(connection.getResponseCode()==200){
                         String line="";
                         reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                         line=reader.readLine();
                         Log.i("hhh",line);
                         JSONObject o1=new JSONObject(line);
                         String state=o1.optString("statue","");
                         if(state.equals("1")){
                             JSONObject object=o1.getJSONObject("info");
                             name=object.optString("username",null);
                             Message message=Message.obtain();
                             message.what=1;
                             handler.sendMessage(message);
                         }else {
                             Message message=Message.obtain();
                             message.what=0;
                             handler.sendMessage(message);
                         }


                     }else {
                         Message message=Message.obtain();
                         message.what=-1;
                         handler.sendMessage(message);
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }

         }.start();
    }

    Handler handler=new Handler(){

        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    if(name!=null){
                        try {
                            user_name.setText(URLDecoder.decode(name,"utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case -1:
                    Toast.makeText(Account.this,"网络连接异常",Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(Account.this,"你还没有设置任何信息",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
}
