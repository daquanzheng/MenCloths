package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.GettingAddressAdapter;
import com.men_cloths.model.GettingAddress;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyAddressActivity extends Activity{
    ImageView back;
    Button addNewAddress;
    ListView listView;
    List<GettingAddress> lists = new ArrayList<>();
    GettingAddressAdapter gettingAddressAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_address);
        addNewAddress = (Button)findViewById(R.id.add_newaddress);
        back = (ImageView) findViewById(R.id.back);
        listView = (ListView) findViewById(R.id.my_address);
        gettingAddressAdapter = new GettingAddressAdapter(MyAddressActivity.this,lists);
        //自定义的接口
        gettingAddressAdapter.setOnEidtOnClickListener(new GettingAddressAdapter.EditOnClickListenr() {
            @Override
            public void onClick(int itemid, String name, String phone) {
                Intent intent = new Intent(MyAddressActivity.this,AddressEditActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }

            @Override
            public void onClick(int position, String name) {
                final int index = position;
                final String str = name;
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String string = "http://192.168.7.9/index.php/Home/Address/deleteaddress";
                        try {
                            URL url = new URL(string+"?name="+str);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.connect();
                            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                                lists.remove(index);
                                handler.sendEmptyMessage(0);

                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        listView.setAdapter(gettingAddressAdapter);

        back.setOnClickListener(onClickListener);
        addNewAddress.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.add_newaddress:
                    Intent intent = new Intent(MyAddressActivity.this,AddNewAddressActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    public void getAddress(){
        String string = "http://192.168.7.9/index.php/Home/Address/getnewaddress";
        try {
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                StringBuilder stringBuilder = new StringBuilder();//单线程用StringBuffer速度快，多线程用StringBuffer保证安全
                String s;
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                // Log.i("获取到的数据",""+stringBuilder);
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    GettingAddress gettingAddress = new GettingAddress();
                    gettingAddress.setName(jsonObject1.optString("name"));
                    gettingAddress.setPhone(jsonObject1.optString("phone"));
                    gettingAddress.setAddressDetails(jsonObject1.optString("address"));
                    lists.add(gettingAddress);
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

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        new Thread(){
            @Override
            public void run() {
                getAddress();
            }
        }.start();
    }

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            gettingAddressAdapter.notifyDataSetChanged();
            return true;
        }
    });
}
