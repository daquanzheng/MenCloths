package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.men_cloths.R;
import com.men_cloths.adapter.CollocateAdapter;
import com.men_cloths.mainContent.SearchActivity;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.Collocate;

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
public class CollocateFragment extends Fragment{
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
                LinearLayout linearLayout1= (LinearLayout) view.findViewById(R.id.collocate_linearlayout1);
                LinearLayout linearLayout2= (LinearLayout) view.findViewById(R.id.collocate_linearlayout2);
                LinearLayout linearLayout3= (LinearLayout) view.findViewById(R.id.collocate_linearlayout3);
                LinearLayout linearLayout4= (LinearLayout) view.findViewById(R.id.collocate_linearlayout4);
                linearLayout1.setOnClickListener(onClickListener);
                linearLayout2.setOnClickListener(onClickListener);
                linearLayout3.setOnClickListener(onClickListener);
                linearLayout4.setOnClickListener(onClickListener);
            }
        });
        return view;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.collocate_linearlayout1:
                    Intent intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
                case R.id.collocate_linearlayout2:
                    intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
                case R.id.collocate_linearlayout3:
                    intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);

                    break;
                case R.id.collocate_linearlayout4:
                    intent=new Intent(getActivity(), ShopInfo.class);
                    startActivity(intent);
                    break;
            }
        }
    };
        public class MyAsyncTask extends AsyncTask<String,Integer,String>{
            @Override
            protected void onPreExecute() {

                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                if(s!=null){
                    try {
                        List<Collocate> collocateList=new ArrayList<>();
                        JSONObject jsonObject=new JSONObject(s);
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++){
                            Collocate collocate=new Collocate();
                            JSONObject obj=jsonArray.getJSONObject(i);
                            collocate.setWholeImg(obj.getString("whole_img"));
                            collocate.setPartImg1(obj.getString("part_img1"));
                            collocate.setPartImg1Title(obj.getString("part_title1"));
                            collocate.setPartImg2(obj.getString("part_img2"));
                            collocate.setPartImg2Title(obj.getString("part_title2"));
                            collocate.setPartImg3(obj.getString("part_img3"));
                            collocate.setPartImg3Title(obj.getString("part_title3"));
                            collocate.setPartImg4(obj.getString("part_img4"));
                            collocate.setPartImg4Title(obj.getString("part_title4"));
                            collocateList.add(collocate);
                        }
                        CollocateAdapter collocateAdapter=new CollocateAdapter(getActivity(),collocateList);
                        listView.setAdapter(collocateAdapter);
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
                        if(is!=null){
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
            String httpUrl="http://804904.ichengyun.net/index.php/home/index/collocate";
            myAsyncTask.execute(httpUrl);
        }
}
