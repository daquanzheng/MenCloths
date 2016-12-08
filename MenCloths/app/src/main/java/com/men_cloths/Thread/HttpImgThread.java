package com.men_cloths.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/17.
 */
public class HttpImgThread {
    private ImageView imageView;
    private String url;
    private LruCache<String,Bitmap> mCaches;
    //增加到缓存，增加之前需判断缓存中是否已有此Bitmap
    public void addBitmapToCache(String url,Bitmap bitmap){
            if(getBitmapFromCache(url)==null){
                mCaches.put(url,bitmap);
            }
    }

    public Bitmap getBitmapFromCache(String url){

        return mCaches.get(url);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(imageView.getTag().equals(url)) {
                imageView.setImageBitmap((Bitmap) msg.obj);
            }
            super.handleMessage(msg);
        }
    };
    public HttpImgThread(){
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();

        int cacheSize=maxMemory/4;

        mCaches= new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }
   public void showImgByThread(ImageView imageView, final String url){
            this.imageView=imageView;
            this.url=url;
       new Thread(){
           @Override
           public void run() {
               super.run();
               Bitmap bitmap=getBitmapFromCache(url);
               Message message=Message.obtain();
               message.obj=bitmap;
               handler.sendMessage(message);
           }
       }.start();
   }
    public Bitmap getBitmapFromUrl(String url){
        Bitmap bitmap;
        InputStream is=null;
        try {
            URL httpurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpurl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            is=httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            httpURLConnection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return null;
    }


    private class NewAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private  ImageView mImageView;
        private  String mUrl;

        public NewAsyncTask(ImageView imageView,String url){
            mImageView=imageView;
            mUrl=url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap(bitmap);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=getBitmapFromUrl(params[0]);
            if(bitmap!=null){
                addBitmapToCache(params[0],bitmap);
            }
            return bitmap;
        }
    }

    public void showImageByAsyncTask(ImageView imageView,String url){
        Bitmap bitmap=getBitmapFromCache(url);
        if(bitmap==null){
        new NewAsyncTask(imageView,url).execute(url);
        }else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
