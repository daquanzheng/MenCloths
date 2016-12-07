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
import com.men_cloths.adapter.WaitpayAdapter;
import com.men_cloths.model.Waitpay;

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
public class WaitPayFragment extends Fragment {
    ListView listView;
    List<Waitpay> lists = new ArrayList<>();
    WaitpayAdapter waitpayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_daifukuan,null);
        listView = (ListView) view.findViewById(R.id.wait_all_listview);
        waitpayAdapter = new WaitpayAdapter(getActivity(),lists);
//        waitpayAdapter.setCheckOrder(new WaitpayAdapter.CheckOrder() {
//            @Override
//            public void check(String name) {
//                Intent intent = new Intent(getActivity(),);
//                startActivity(intent);
//            }
//        });
        listView.setAdapter(waitpayAdapter);
        return view;
    }
   public void getLists(){
       String string = "http://192.168.7.9/index.php/home/waitpay/getorder";
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
                   JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                   Waitpay waitpay = new Waitpay();
                   waitpay.setPicture(jsonObject1.optInt("picture"));
                   waitpay.setName(jsonObject1.optString("name"));
                   waitpay.setColor(jsonObject1.optString("color"));
                   waitpay.setSize(jsonObject1.optString("size"));
                   waitpay.setPrice(jsonObject1.optString("price"));
                   lists.add(waitpay);
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
    public void onResume() {
        super.onResume();
        lists.clear();
        new Thread(){
            @Override
            public void run() {
                getLists();
            }
        }.start();
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            waitpayAdapter.notifyDataSetChanged();
            return true;
        }
    });
}
