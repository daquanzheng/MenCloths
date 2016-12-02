package com.men_cloths.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/12/2.
 */

public class LoadImage extends AsyncTask<String,Void,Bitmap>{

    private ImageView imageView;
    private String url;
    private String surl;


    private LoadImage(ImageView imageView, String url) {
        this.imageView = imageView;
        this.url = url;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap!=null){
            if(imageView.getTag().equals(surl)){
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap=null;
        BufferedInputStream is=null;
        surl=params[0];
        try {
            URLConnection connection=new URL(params[0]).openConnection();
            connection.setConnectTimeout(5000);
            is=new BufferedInputStream(connection.getInputStream());
            bitmap= BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return bitmap;
    }


    public static void load(ImageView imageView, String url){
        LoadImage image=new LoadImage(imageView,url);
        imageView.setTag(url);
        image.execute(url);
    }
}
