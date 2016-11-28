package com.men_cloths.mainContent;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/28.
 */

public class MessageVerification{


//    String result = requestData("https://webapi.sms.mob.com/sms/verify",
//            "appkey=xxxx&phone=xxx&zone=xx&&code=xx");
//    //System.out.println(result);

    static private String tel="",code="";
    public static void load(String tel, String code){
        MessageVerification.tel=tel;
        MessageVerification.code=code;
         new Thread(){
          public void run(){
              String result = requestData("https://webapi.sms.mob.com/sms/verify",
                      "appkey=196900d9a47c0&phone="+MessageVerification.tel+"&zone=86&code="+MessageVerification.code);

          }
      }.start();

    }


    /**
     * 发起https 请求
     * @param address
     * @param
     * @return
     */
    public  static String requestData(String address ,String params) {

        String code;
        Log.i("hhh", "fjysuadhnas");
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            conn= (HttpURLConnection) new URL(address).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            OutputStream os=conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            conn.connect();

           if(conn.getResponseCode()==200){
               reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
               code=reader.readLine();
               Log.i("hhh",code+"123");
               return code;
           }else {
               Log.i("hhh","网络访问失败!");
           }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;


    }


}
