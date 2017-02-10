package com.men_cloths.model;

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
import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/12/1.
 */

public class Register{


    private Handler handler;
    private String user;
    private String passwd;

    private Register( Handler handler, String user, String passwd) {

        this.handler = handler;
        this.user = user;
        this.passwd = passwd;
    }

    private void function(){
        String url="http://804904.ichengyun.net/index.php/home/index/register?tel="+user+"&passwd="+passwd;
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
        BufferedReader bufferedReader=null;

        try {
            connection= (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();

            if(connection.getResponseCode()==200){
                bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
                line=bufferedReader.readLine();
                Log.i("hhh",line);
                /**{
                 'statu':1
                 'message':注册成功
                 }
                 * */

                try {
                    JSONObject object=new JSONObject(line);
                    String code=object.getString("statu");
                    if(code!=null)
                        if(code.equals("1")){
                            Message message=Message.obtain();
                            message.what=1;
                            handler.sendMessage(message);
                        }else {
                            Message message=Message.obtain();
                            message.what=-1;
                            String ss=object.getString("message");
                            message.obj= URLDecoder.decode(ss,"utf-8");
                            handler.sendMessage(message);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void register( Handler handler, String user, String passwd){
        Register register=new Register(handler,user,passwd);
        new Thread(new A(register)).start();
    }

    private static class A implements Runnable{

        Register register;
        A(Register register){
            this.register=register;
        }
        @Override
        public void run() {
            register.function();
        }
    }


}
