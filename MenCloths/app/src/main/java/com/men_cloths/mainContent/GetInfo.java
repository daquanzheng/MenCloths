package com.men_cloths.mainContent;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/30.
 */

public class GetInfo extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            public void run(){
                getuserinfo();
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getuserinfo(){
        HttpURLConnection connection=null;
        StringBuffer buffer=new StringBuffer();
        BufferedReader bufferedReader=null;
        String url="http://www.android.com/index.php/home/index/userinfo?username=zhangwen&token=74c3e649262e4066533241c9e0084b84";

        try {
            connection= (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            if(connection.getResponseCode()==200){
              bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
              String line="";
              while((line=bufferedReader.readLine())!=null){
                  buffer.append(line);
              }
                Log.i("hhh",buffer.toString());
                SharedPreferences sharedPreferences=getSharedPreferences("userinfo",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("info",buffer.toString());
                editor.commit();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
