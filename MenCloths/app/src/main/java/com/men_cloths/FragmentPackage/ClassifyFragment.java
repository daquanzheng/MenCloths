package com.men_cloths.FragmentPackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.ClassifyAdapter;
import com.men_cloths.mainContent.ClassifyInfo;
import com.men_cloths.mainContent.SearchActivity;
import com.men_cloths.model.Classify;

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
public class ClassifyFragment extends Fragment{
    ListView listView;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mencloths_mall,null);
        listView= (ListView)view.findViewById(R.id.listview_mall);
        editText= (EditText) view.findViewById(R.id.edit_mall);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
        startAsyncTask();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView= (ImageView) view.findViewById(R.id.classify_item_img1);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ClassifyInfo.class);
                        getActivity().startActivity(intent);
                    }
                });
                ImageView imageView1= (ImageView) view.findViewById(R.id.classify_item_img2);
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ClassifyInfo.class);
                        getActivity().startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
    public class MyAsyncTask extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            //在execute(Params... params)被调用后立即执行，一般用来在执行后台任务前对UI做一些标记
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {//执行UI操作
            if(s!=null){
                try {
                    List<Classify> classifyList=new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        if(i%2==0) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Classify classify = new Classify();
                            classify.setText1(obj.getString("categroy"));
                            classify.setImgUrl1(obj.getString("img"));
                            JSONObject object = jsonArray.getJSONObject(i + 1);
                            classify.setText2(object.getString("categroy"));
                            classify.setImgUrl2(object.getString("img"));
                            classifyList.add(classify);
                        }
                    }
                    ClassifyAdapter classifyAdapter=new ClassifyAdapter(getActivity(),classifyList);
                    listView.setAdapter(classifyAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新类似于进度条的控件的进度效果
            super.onProgressUpdate(values);
        }

        @Override//所有的耗时操作都在此时进行，不能进行UI操作
        protected String doInBackground(String... params) {//在后台运行的回调方法
            StringBuilder stringBuilder=new StringBuilder();//可序列
            InputStream is=null;
            try {
                URL url=new URL(params[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();//建立本次网络请求的连接
                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    is=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader
                            (new InputStreamReader(is,"utf-8"));
                    String s;
                    while ((s=bufferedReader.readLine())!=null){
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
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    public void startAsyncTask(){
        MyAsyncTask myAsyncTask=new MyAsyncTask();
        String httpUrl="http://192.168.7.20/index.php/home/index/product_categroy";
        myAsyncTask.execute(httpUrl);
    }
}
