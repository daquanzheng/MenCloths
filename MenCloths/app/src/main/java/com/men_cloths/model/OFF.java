package com.men_cloths.model;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/5.
 */

public class OFF extends BroadcastReceiver {

    private SharedPreferences sharedPreferences;
    private Context context;
    public static final String PACKG="com.men_cloths.model.BroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        sharedPreferences=context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
        context.unregisterReceiver(this);
        function();
    }

    public void function(){
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
                        //handler.sendEmptyMessage(0);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("islogin",false);
                        Activity activity= (Activity) context;
                        activity.finish();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
