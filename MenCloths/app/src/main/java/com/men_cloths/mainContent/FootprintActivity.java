package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
import android.os.Handler;
import android.os.Message;
import android.util.Log;
=======
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
=======
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.FootprintAdapter;
import com.men_cloths.model.Footprint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class FootprintActivity extends Activity{
<<<<<<< HEAD
<<<<<<< HEAD
    ImageView back;
    ListView listView;
    List<Footprint> lists = new ArrayList<>();
    FootprintAdapter footprintAdapter;
=======
    private  ImageView back;
    private  ListView listView;
    private  List<Footprint> lists = new ArrayList<>();
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
=======
    private  ImageView back;
    private  ListView listView;
    private  List<Footprint> lists = new ArrayList<>();
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_footprint);
        listView = (ListView) findViewById(R.id.listview_footprint);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(onClickListener);

        footprintAdapter = new FootprintAdapter(FootprintActivity.this,lists);
        listView.setAdapter(footprintAdapter);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
    public void getData(){
        String string = "http://192.168.7.9/index.php/home/footprint/getfootprint";
        try {
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String s;
                while ((s=reader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("获取到的数据-------->",""+stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.optJSONObject(i);
                    Footprint footprint = new Footprint();
//                    footprint.setTime();
                    footprint.setPicture(object.optInt("picture"));
                    footprint.setName(object.optString("name"));
                    footprint.setColor(object.optString("color"));
                    footprint.setSize(object.optString("size"));
                    footprint.setPrice(object.optString("price"));
                    lists.add(footprint);
                }
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            footprintAdapter.notifyDataSetChanged();
            return true;
        }
    });

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
    }
}
