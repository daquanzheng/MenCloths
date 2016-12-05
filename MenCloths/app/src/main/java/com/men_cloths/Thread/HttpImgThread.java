package com.men_cloths.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/17.
 */
public class HttpImgThread extends Thread{
    private ImageView imageView;
    private String url;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(imageView.getTag().equals(url)) {
                imageView.setImageBitmap((Bitmap) msg.obj);
            }
            super.handleMessage(msg);
        }
    };
    public HttpImgThread(ImageView imageView, String url){
        this.imageView=imageView;
        this.url=url;
    }
    @Override
    public void run() {
        //SharedPreferences sharedPreferences=context.getSharedPreferences("图片",Context.MODE_PRIVATE);
        try {
            URL httpurl=new URL(url);
            HttpURLConnection httpURLConnection= (HttpURLConnection) httpurl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            Bitmap bitmap= BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();
            Message message=Message.obtain();
            message.obj=bitmap;
            handler.sendMessage(message);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
