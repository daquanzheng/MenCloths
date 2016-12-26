package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.HotSellerAdapter;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.HotSeller;

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
 * Created by Administrator on 2016/11/1.
 */
public class HotSellerFragment extends Fragment{
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.listview_no_divider,null);
        listView= (ListView) view.findViewById(R.id.listview_no_divider);
        startAsyncTask();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ShopInfo.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
    public class MyAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null){
                try {
                    List<HotSeller> hotSellerList=new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj=jsonArray.getJSONObject(i);
                        HotSeller hotSeller=new HotSeller();
                        hotSeller.setHotContent(obj.getString("introduction"));
                        hotSeller.setHotPrice(obj.getDouble("price"));
                        hotSeller.setHotImg(obj.getString("img"));
                        hotSellerList.add(hotSeller);
                    }
                    HotSellerAdapter hotSellerAdapter=new HotSellerAdapter(getActivity(),hotSellerList);
                    listView.setAdapter(hotSellerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuilder stringBuilder=new StringBuilder();
            InputStream is=null;
            try {
                URL url=new URL(params[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();
                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    is=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is,"utf-8"));
                    String s;
                    while((s=bufferedReader.readLine())!=null){
                        stringBuilder.append(s);
                    }
                   return stringBuilder.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(is!=null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    public void startAsyncTask(){
        MyAsyncTask myAsyncTask=new MyAsyncTask();
        String httpUrl="http://139.199.196.199/index.php/home/index/new_product";
        myAsyncTask.execute(httpUrl);
    }
}
