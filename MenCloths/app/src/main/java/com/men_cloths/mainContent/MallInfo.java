package com.men_cloths.mainContent;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangcheng_xiangqing);

        new Thread() {
            @Override
            public void run() {
                getDate();
                super.run();
            }
        }.start();

        ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter(this, list);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(productDetailsAdapter);

    }



    public void getDate() {
        String httpURL = "http://192.168.7.2//index.php/Home/Index/productdetail";
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
                Log.i("date", "==========" + stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    ProductDetails productDetails = new ProductDetails();
                    productDetails.setDescription(object.getString("description"));
                    productDetails.setType(object.getString("type"));
                    productDetails.setPrice(object.getDouble("price"));
                    productDetails.setOriginal(object.getDouble("original"));
                    //productDetails.setProduct(object.getInt("product"));
                    list.add(productDetails);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
