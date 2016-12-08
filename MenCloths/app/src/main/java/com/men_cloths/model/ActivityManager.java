package com.men_cloths.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ActivityManager extends Application{
    private static ActivityManager manager;
    private ActivityManager(){}
    private static List<Activity> list;
    public static MemoryCache memoryCache=MemoryCache.getMemoryCache(10);
    private static String token;
    private static String tel;
    private static Context context;

    public static ActivityManager getActivityManager() {
        if(manager==null){
            manager=new ActivityManager();
            list=new ArrayList<>();
        }
        return manager;
    }
    public void add(Activity activity){
        list.add(activity);
    }
    public void exit(){
        for(Activity activity:list){
            if(activity!=null){
                activity.finish();
            }
        }
        System.exit(0);
    }

    public static void getUserInfo(String token, String tel,  Context context){
        ActivityManager.token=token;
        ActivityManager.tel=tel;
        ActivityManager.context=context;
        new Thread(){
            public void run(){
                ActivityManager.getInfo(ActivityManager.token,ActivityManager.tel,ActivityManager.context);
            }
        }.start();

    }

    private static void getInfo(String token, String tel, Context context){
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
                    SharedPreferences preferences=context.getSharedPreferences("login_info",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putInt("coupon_expired_count",object.optInt("coupon_expired_count",0));
                    editor.putInt("coupon_used_count",object.optInt("coupon_used_count",0));
                    editor.putInt("coupon_notuesd_count",object.optInt("coupon_notuesd_count",0));
                    editor.commit();

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
}
