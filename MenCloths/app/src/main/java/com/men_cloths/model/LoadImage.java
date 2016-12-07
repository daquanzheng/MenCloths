package com.men_cloths.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/2.
 */
public class LoadImage extends AsyncTask<String,Void,Bitmap>{
    private ImageView imageView;
    private String url;
    private Context context;

    private LoadImage(){}

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null && imageView.getTag().equals(url)){
            imageView.setImageBitmap(bitmap);
        }
        //Log.i("hhh",bitmap+"");
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap=null;
        try {
            InputStream is=new URL(params[0]).openConnection().getInputStream();
           // bitmap= BitmapFactory.decodeStream(is);
            if(CachetToFile.isloaded(context,url)){
               is=CachetToFile.getImage(url,context);
               bitmap=BitmapFactory.decodeStream(is);
            }else {
                CachetToFile.saveImage(url,context,is);
                bitmap= BitmapFactory.decodeStream(CachetToFile.getImage(url,context));
                //is=new URL(params[0]).openConnection().getInputStream();

            }

            if (is!=null){
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void load(ImageView imageView,String url,Context context){
        LoadImage loadImage=new LoadImage();
        loadImage.imageView=imageView;
        loadImage.url=url;
        loadImage.context=context;
        imageView.setTag(url);
        loadImage.execute(url);
    }
}

