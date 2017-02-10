package com.men_cloths.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/1.
 */

public class Login{


    private  String user;
    private String passwd;
    private Context context;
    private Handler handler;

    private Login(String user, String passwd, Context context, Handler handler){
         this.user=user;
         this.passwd=passwd;
         this.context=context;
         this.handler=handler;
    }


    private void run(){
        String url="http://804904.ichengyun.net/index.php/home/index/login?tel="+user+"&passwd="+passwd;
        if(user==null || passwd==null){
            Message message=Message.obtain();
            message.what=-99;
            handler.sendMessage(message);
            return;
        }
        if(user.length()==0||passwd.length()==0){
            Message message=Message.obtain();
            message.what=-99;
            handler.sendMessage(message);
            return;
        }
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        try {
            connection= (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode()==200){
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
                line=reader.readLine();
                Log.i("hhh",line);
                jsonjx(line);

                String code=hashMap.get("statu");
                if(code.equals("1")){
                    SharedPreferences sharedPreferences=context.getSharedPreferences("login_info",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("islogin",true);
                    editor.commit();
                    Message message=Message.obtain();
                    message.what=1;
                    handler.sendMessage(message);
                }else if(code.equals("-1")){
                    Message message=Message.obtain();
                    message.what=-1;
                    handler.sendMessage(message);
                }

            }else{
                Message message=Message.obtain();
                message.what=-123;
                handler.sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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

    public static void login(String user, String passwd, Context context,Handler handler){
        Login login=new Login(user,passwd,context,handler);
        new Thread(new A(login)).start();
    }

    private static class A implements Runnable{

        Login login;
        A(Login login){
            this.login=login;
        }
        @Override
        public void run() {
            login.run();
        }
    }
    HashMap<String,String> hashMap=new HashMap<>();
    public void jsonjx(String json){

        String s="";
        for(int i=0;i<json.length();i++){
            if(json.charAt(i)!='\\' || json.charAt(i)!='n'){
                s+=json.charAt(i);
            }
        }
        json=s;
        Log.i("hhh",json);

        try {
            //"{\"statu\":1,\"message\":\"sucess!\\u767b\\u5f55\\u6210\\u529f\",\"token\":\"b554a115dba33569b30a19931b00a41f\"}\\n"
            JSONObject jsonObject=new JSONObject(json);
            hashMap.put("statu",jsonObject.getString("statu"));
            hashMap.put("message",jsonObject.getString("message"));
            hashMap.put("token",jsonObject.getString("token"));
            SharedPreferences sharedPreferences=context.getSharedPreferences("login_info",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("statu",jsonObject.getString("statu"));
            editor.putString("message",jsonObject.getString("message"));
            editor.putString("token",jsonObject.getString("token"));
            editor.putString("tel",user);
            editor.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }










}
