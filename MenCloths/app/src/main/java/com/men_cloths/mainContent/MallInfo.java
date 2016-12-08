package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.BinderThread;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.men_cloths.R;
import com.men_cloths.adapter.ProductDetailsAdapter;
import com.men_cloths.model.ProductDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class MallInfo extends Activity {
    List<ProductDetails> list = new ArrayList<>();
    ListView listView;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangcheng_xiangqing);
        listView = (ListView) findViewById(R.id.listview);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        new Thread() {
            @Override
            public void run() {
                getDate();
                super.run();
            }
        }.start();




    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter(MallInfo.this, list);
            listView.setAdapter(productDetailsAdapter);

        }
    };
    public void getDate() {
        String httpURL = "http://139.199.196.199//index.php/Home/Index/productdetail";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(httpURL).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String date;
                while ((date = bufferedReader.readLine()) != null) {
                    stringBuilder.append(date);
                }

                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("result");


//得到该图片的id(name 是该图片的名字，"drawable" 是该图片存放的目录，appInfo.packageName是应用程序的包)
                //int resID = getResources().getIdentifier(name, "mipmap", appInfo.packageName);
//代码如下
//            public Bitmap getRes(String name) {
//                ApplicationInfo appInfo = getApplicationInfo();
//                int resID = getResources().getIdentifier(name, "drawable", appInfo.packageName);
//                return BitmapFactory.decodeResource(getResources(), resID);}
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    ProductDetails productDetails = new ProductDetails();
                    productDetails.setDescription(object.getString("description"));
                    productDetails.setType(object.getString("type"));
                    productDetails.setPrice(object.getDouble("price"));
                    productDetails.setOriginal(object.getDouble("original"));
                    try {
                        URL url=new URL(object.getString("product"));
                        HttpURLConnection httpURLConnection1=(HttpURLConnection) url.openConnection();
                        httpURLConnection1.setRequestMethod("GET");
                        httpURLConnection1.connect();
                        if((httpURLConnection1.getResponseCode()==200)){
                            Bitmap bitmap= BitmapFactory.decodeStream(httpURLConnection1.getInputStream());
                            productDetails.setProduct(bitmap);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

//                    int resID = getResources().getIdentifier(object.getString("product"), "mipmap", this.getPackageName());
//                    Log.i("packagename=======",""+this.getPackageName());
//                    Bitmap bitmap=BitmapFactory.decodeResource(getResources(), resID);
//                    productDetails.setProduct(bitmap);
                    list.add(productDetails);

                }
                handler.sendEmptyMessage(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
