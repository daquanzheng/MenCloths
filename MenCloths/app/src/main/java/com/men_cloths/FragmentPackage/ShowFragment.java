package com.men_cloths.FragmentPackage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.ShowAdapter;
import com.men_cloths.mainContent.ShopInfo;
import com.men_cloths.model.Show;

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
public class ShowFragment extends Fragment{
    private ListView listView;
    String url="http://804904.ichengyun.net/index.php/home/index/show";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.listview_have_divider,null);
        listView= (ListView) view.findViewById(R.id.listview_have_divider);
        new MyAsyncTask().execute(url);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView= (ImageView) view.findViewById(R.id.home_show_part_img1);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ShopInfo.class);
                        getActivity().startActivity(intent);
                    }
                });
               ImageView imageView1= (ImageView) view.findViewById(R.id.home_show_part_img2);
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ShopInfo.class);
                        getActivity().startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
   private class MyAsyncTask extends AsyncTask<String,Integer,String>{
       @Override
       protected void onPostExecute(String s) {
           if(s!=null){
               try {
                   List<Show> showList=new ArrayList<>();
                   JSONObject jsonObject=new JSONObject(s);
                   JSONArray jsonArray=jsonObject.getJSONArray("data");
                   for(int i=0;i<jsonArray.length();i++){
                       JSONObject object=jsonArray.getJSONObject(i);
                       Show show=new Show();
                       show.setTitleImg(object.getString("wholeimg"));
                       show.setPartImg1(object.getString("partimg1"));
                       show.setPartImg2(object.getString("partimg2"));
                       show.setNickName(object.getString("nickname"));
                       show.setMood(object.getString("mood"));
                       showList.add(show);
                   }
                   ShowAdapter showAdapter=new ShowAdapter(getActivity(),showList);
                   listView.setAdapter(showAdapter);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }


           super.onPostExecute(s);
       }

       @Override
       protected String doInBackground(String... params) {
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
                   if(is!=null)
                       is.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           return null;
       }
   }

}
