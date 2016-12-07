package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;


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
                    //线程是耗时操作，必须等线程跑完了才能finish
                    break;
            }
        }
    };

    public void addNewAddress(){
        try {
            String string = "http://192.168.7.9/index.php/Home/Address/";
            URL url = new URL(string+"addnewaddress?name="+viewHolder.name.getText().toString()+"&phone="+viewHolder.phone.getText().toString()+
                    "&address="+viewHolder.address.getText().toString()+viewHolder.street.getText().toString());
           // Log.i("url=====>",""+url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
//            httpURLConnection.setRequestProperty();
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
//                StringBuilder stringBuilder = new StringBuilder();//单线程用StringBuffer速度快，多线程用StringBuffer保证安全
//                String s;
//                while ((s=bufferedReader.readLine())!=null){
//                    stringBuilder.append(s);
//                }
              //  Log.i("加入的数据",""+stringBuilder);
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            finish();
            return true;
        }
    });
}
