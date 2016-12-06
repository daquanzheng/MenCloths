package com.men_cloths.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/5.
 */

public class OFF {

    private static Context context;
    private static SharedPreferences sharedPreferences;
    private static Handler handler;
    public static void unRegister(Context context,  Handler handler){
        OFF.context=context;
        OFF.handler=handler;
        sharedPreferences= OFF.context.getSharedPreferences("login_info",Context.MODE_PRIVATE);
        new Thread(){
            public void run(){
                String path="http://139.199.196.199/index.php/home/index/signout?tel="+sharedPreferences.getString("tel","")+
                        "&token="+sharedPreferences.getString("token","");
                HttpURLConnection connection=null;
                try {
                    connection= (HttpURLConnection) new URL(path).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){

                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("islogin",false);
                        editor.commit();
                        OFF.handler.sendEmptyMessage(0);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }


}
