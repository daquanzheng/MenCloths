package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.WaitgetAdapter;
import com.men_cloths.mainContent.MyWuliuActivity;
import com.men_cloths.model.Waitget;


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
public class WaitGetFragment extends Fragment{
    ListView listView;
    List<Waitget> lists = new ArrayList<>();
    WaitgetAdapter waitgetAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_daifukuan,null);
        listView = (ListView) view.findViewById(R.id.wait_all_listview);
        waitgetAdapter = new WaitgetAdapter(getActivity(),lists);
        waitgetAdapter.setEditOrderClick(new WaitgetAdapter.EditOrderClick() {
            @Override
            public void deleteOrder(int position, String name) {
                final int po = position;
                final String na = name;
                new Thread(){
                    @Override
                    public void run() {
                        String str = "http://139.199.196.199/index.php/Home/waitget/deleteorder?name="+na;
                        try {
                            URL url = new URL(str);
                            HttpURLConnection http = (HttpURLConnection) url.openConnection();
                            http.setRequestMethod("GET");
                            http.setConnectTimeout(5000);
                            if(http.getResponseCode()==200){
                                lists.remove(po);
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

            @Override
            public void checkWuliu(int position, String name) {
                Intent intent = new Intent(getActivity(), MyWuliuActivity.class);
                intent.putExtra("goodsname",name);
                startActivity(intent);
            }
        });
        listView.setAdapter(waitgetAdapter);
        return view;
    }

    public void getData(){
        String string = "http://139.199.196.199/index.php/Home/waitget/getorder";
        try {
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
                StringBuilder stringBuilder = new StringBuilder();//单线程用StringBuffer速度快，多线程用StringBuffer保证安全
                String s;
                while ((s=reader.readLine())!=null){
                    stringBuilder.append(s);
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                    Waitget waitget = new Waitget();
                    waitget.setPicture(jsonObject1.optInt("picture"));
                    waitget.setName(jsonObject1.optString("name"));
                    waitget.setColor(jsonObject1.optString("color"));
                    waitget.setSize(jsonObject1.optString("size"));
                    waitget.setPrice(jsonObject1.optString("price"));
                    lists.add(waitget);
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

    //进入界面自动调用此方法
    @Override
    public void onResume() {
        super.onResume();
        lists.clear();
        new Thread(){
            @Override
            public void run() {
                super.run();
                getData();
            }
        }.start();
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            waitgetAdapter.notifyDataSetChanged();
            return true;
        }
    });
}
