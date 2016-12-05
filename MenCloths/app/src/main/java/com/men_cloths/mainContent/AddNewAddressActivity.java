package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/28.
 */
public class AddNewAddressActivity extends Activity{
    ViewHolder viewHolder;
    public class ViewHolder{
        ImageView back;
        TextView saveNewAddress,name,phone,address,street;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_add_newaddress);
        viewHolder = new ViewHolder();
        viewHolder.back = (ImageView) findViewById(R.id.back);
        viewHolder.saveNewAddress = (TextView) findViewById(R.id.save_new_address);
        viewHolder.name = (TextView) findViewById(R.id.name);
        viewHolder.phone = (TextView) findViewById(R.id.phone);
        viewHolder.address = (TextView) findViewById(R.id.address);
        viewHolder.street = (TextView) findViewById(R.id.street);


        viewHolder.back.setOnClickListener(onClickListener);
        viewHolder.saveNewAddress.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.save_new_address:
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            addNewAddress();
                        }
                    }.start();
//                    Intent intent = getIntent();
//                    intent.putExtra("newname",viewHolder.name.getText().toString());
//                    intent.putExtra("newphone",viewHolder.phone.getText().toString());
//                    intent.putExtra("newaddress",viewHolder.address.getText().toString());
//                    intent.putExtra("newstreet",viewHolder.street.getText().toString());
//                    setResult(121,intent);
                    finish();
                    break;
            }
        }
    };
    public void addNewAddress(){
        try {
            String string = "http://10.0.2.2/index.php/Home/index/";
            URL url = new URL(string+"addNewAddress?name="+viewHolder.name.getText()+"phone="+viewHolder.phone.getText()+
                    "address="+viewHolder.address.getText());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
//            httpURLConnection.setRequestProperty();
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                StringBuilder stringBuilder = new StringBuilder();//单线程用StringBuffer速度快，多线程用StringBuffer保证安全
                String s;
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("加入的数据",""+stringBuilder);
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
