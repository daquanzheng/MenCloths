package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.model.LoadImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/10/19.
 */

public class PersonInfo extends Activity {
   private ImageView back;
   private ImageView head;
   private TextView userName;
   private String token,tel;
   private String imageUrl;
   private String name;
   private String str=null;
   private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);
        init();
        new Thread(){
            public void run(){
                getInfo();
            }
        }.start();

    }


    private void init(){
        back= (ImageView) findViewById(R.id.back);
        head= (ImageView) findViewById(R.id.head);
        userName= (TextView) findViewById(R.id.user_name);
        editText= (EditText) findViewById(R.id.editText2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (str==null || str.equals("")){
                  finish();
              }else {
                  Toast.makeText(PersonInfo.this,"正在保存信息请稍候",Toast.LENGTH_SHORT).show();
                  new Thread(){
                      public void run(){
                          setInfo(str);
                      }
                  }.start();
              }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                  str=s.toString();
            }
        });
        SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
        token=preferences.getString("token","");
        tel=preferences.getString("tel","");
    }

    private void getInfo(){
        String url="http://139.199.196.199/index.php/home/index/userinfo?token="+token+"&tel="+tel;
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
                    imageUrl=object.optString("head_portrait",null);
                    Message message=Message.obtain();
                    message.what=1;
                    handler.sendMessage(message);
                }else {
                    Message message=Message.obtain();
                    message.what=-1;
                    handler.sendMessage(message);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setInfo(String s){
        String url= null;
        try {
            url = "http://139.199.196.199/index.php/home/index/changeinformation?token="+token+"&tel="+tel+"&username="+ URLEncoder.encode(s,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                Message message=Message.obtain();
                message.what=0;
                handler.sendMessage(message);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    Handler handler=new Handler(){

        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    if(name!=null){
                        try {
                            editText.setText(URLDecoder.decode(name,"utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                    if(imageUrl!=null){
                        LoadImage.load(head,imageUrl);
                    }
                    break;
                case -1:
                    Toast.makeText(PersonInfo.this,"发生网络请求的错误",Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    finish();
                    break;
            }
        }
    };



}
