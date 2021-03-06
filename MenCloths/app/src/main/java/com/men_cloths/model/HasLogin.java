package com.men_cloths.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/5.
 */

public class HasLogin {
   public static  boolean hasLogin(Context context){
       SharedPreferences sharedPreferences=context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
       //Log.i("ststststst",sharedPreferences.getBoolean("islogin",false)+""+sharedPreferences.getString("token",""));
       return sharedPreferences.getBoolean("islogin",false);
   }
   public static  boolean isfirst(Context context){
       SharedPreferences sharedPreferences=context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
       return sharedPreferences.getBoolean("isfirst",true);
   }

   public static void checkLogin(Context context, Handler handler){
      new A(context,handler).start();
   }

    private static class A extends Thread{
        private Context context;
        private Handler handler;
        A(Context context,Handler handler){
            this.context=context;
            this.handler=handler;
        }
        public void run(){
            SharedPreferences sharedPreferences=context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
            String path="http://804904.ichengyun.net/index.php/home/index/islogin?tel="+sharedPreferences.getString("tel","")+
                    "&token="+sharedPreferences.getString("token","");
            HttpURLConnection connection=null;
            BufferedReader bufferedReader=null;
            try {
                connection= (HttpURLConnection) new URL(path).openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2000);
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    //handler.sendEmptyMessage(0);

                    bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    line=bufferedReader.readLine();
                   // Log.i("hhh",line+"dsadhandsajdasd"+sharedPreferences.getString("tel","")+sharedPreferences.getString("token",""));

                    try {
                        JSONObject object=new JSONObject(line);
                        String code=object.optString("code","");
                        if(code.equals("1")){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("islogin",true);
                            editor.commit();
                            if (handler!=null)
                            handler.sendEmptyMessage(-1);
                        }else if(code.equals("-1")){
                            if (handler!=null)
                            handler.sendEmptyMessage(1);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("islogin",false);
                            editor.commit();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }else {

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("islogin",false);
                    editor.commit();
                    if (handler!=null)
                    handler.sendEmptyMessage(-1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null){
                    connection.disconnect();
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
    }
}
