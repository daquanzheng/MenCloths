package com.men_cloths.FragmentPackage;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.MallAdapter;
import com.men_cloths.mainContent.MallInfo;
import com.men_cloths.mainContent.SearchActivity;
import com.men_cloths.model.ProductClassify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MallFragment extends Fragment {
    ListView listView;
    EditText editText;
    List<ProductClassify> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mencloths_mall, null);

        editText = (EditText) view.findViewById(R.id.edit_mall);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
        new Thread() {
            @Override
            public void run() {
                getData();
                super.run();
            }
        }.start();
        listView = (ListView) view.findViewById(R.id.listview_mall);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MallInfo.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MallAdapter mallAdapter = new MallAdapter(getActivity(), list);
            listView.setAdapter(mallAdapter);
        }
    };

    public void getData() {
        String httpURL = "http://192.168.7.2//index.php/Home/Index/productclassify";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(httpURL).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String str;
                if ((str = bufferedReader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                Log.i("object====", "" + jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    ProductClassify productClassify = new ProductClassify();
                    URL url = new URL(object.getString("imgname"));
                    HttpURLConnection httpURLConnection1 = (HttpURLConnection) url.openConnection();
                    httpURLConnection1.setRequestMethod("GET");
                    httpURLConnection1.connect();
                    Log.i("object====", "" + httpURLConnection1.getResponseCode());
                    if (httpURLConnection1.getResponseCode() == 200) {
                        Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection1.getInputStream());
                        productClassify.setImgname(bitmap);
                        Log.i("object====", "" + bitmap);
                    }
                    list.add(productClassify);
                }
                handler.sendEmptyMessage(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
