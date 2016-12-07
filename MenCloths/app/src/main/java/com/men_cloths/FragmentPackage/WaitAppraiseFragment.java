package com.men_cloths.FragmentPackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.WaitappraiseAdapter;
import com.men_cloths.model.Waitappraise;

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
public class WaitAppraiseFragment extends Fragment{
<<<<<<< HEAD
<<<<<<< HEAD
    ListView listView;
    List<Waitappraise> lists = new ArrayList<>();
    WaitappraiseAdapter waitappraiseAdapter;
=======
    private   ListView listView;
    private  List<Waitappraise> lists = new ArrayList<>();
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
=======
    private   ListView listView;
    private  List<Waitappraise> lists = new ArrayList<>();
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_daifukuan,null);
        listView = (ListView) view.findViewById(R.id.wait_all_listview);
        waitappraiseAdapter = new WaitappraiseAdapter(getActivity(),lists);
        listView.setAdapter(waitappraiseAdapter);
        return view;
    }
    public void getData(){
        String string = "http://192.168.7.9/index.php/home/waitappraise/getorder";
        try {
            URL url = new URL(string);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(5000);
            http.connect();
            if(http.getResponseCode()==200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String s;
                while ((s=reader.readLine())!=null){
                    stringBuilder.append(s);
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.optJSONObject(i);
                    Waitappraise waitappraise = new Waitappraise();
                    waitappraise.setPicture(object.optInt("picture"));
                    waitappraise.setName(object.optString("name"));
                    waitappraise.setColor(object.optString("color"));
                    waitappraise.setSize(object.optString("size"));
                    waitappraise.setPrice(object.optString("price"));
                    lists.add(waitappraise);
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
            waitappraiseAdapter.notifyDataSetChanged();
            return true;
        }
    });

    @Override
    public void onResume() {
        super.onResume();
        lists.clear();
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
    }
}
