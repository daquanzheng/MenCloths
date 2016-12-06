package com.men_cloths.mainContent;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.adapter.Adapter;
import com.men_cloths.model.Commodity;

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
 * //还需要删除远程数据库的内容
 *
 * */
public class Collection extends Activity{
	
	private ListView listView;
	private List<Commodity> list;
	private ImageView imageView;
	private BaseAdapter baseAdapter;
	private int position;
	private String tel;
	private static View view;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		init();
		createData();
		listView=(ListView) findViewById(R.id.collect_list_view);
		baseAdapter=new Adapter().getSocllectAdapter(this,list);
		listView.setAdapter(baseAdapter);
		//listView.setDescendantFocusability();
		//listView.setItemsCanFocus(true);
		onclickForitem();

		
		
		
		
		
	}

	public void init(){
		imageView= (ImageView) findViewById(R.id.back);
		imageView.setOnClickListener(listener);
		SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
		tel=preferences.getString("tel",null);
		list=new ArrayList<>();



	}

	View.OnClickListener listener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.back:
					finish();
					break;
			}
		}
	};
	
	public void createData(){
		list.clear();
		new Thread(){
			public void run(){
				getHttpURL();
			}
		}.start();
	}

	public void onclickForitem(){
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Collection.this.position=position;

				TextView textView= (TextView) view.findViewById(R.id.cancel);
				Collection.view=view;
				parent.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
				textView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						new Thread(){
							public void run(){
								if(remove((String)Collection.view.getTag(),tel)) {
									list.remove(Collection.this.position);
									handler.sendEmptyMessage(0);
								}
							}
						}.start();
					}
				});

			}
		});
	}

	public void getHttpURL(){
		String utl="http://139.199.196.199/index.php/home/index/collection?tel="+tel;
		HttpURLConnection connection=null;
		BufferedReader bufferedReader=null;

		try {
			connection= (HttpURLConnection) new URL(utl).openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.connect();
			if(connection.getResponseCode()==200){
				String line;
				bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				line=bufferedReader.readLine();
				Log.i("hhh",line);
				jsonjx(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null){
				connection.disconnect();
			}
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}

	public void jsonjx(String string){
		try {
			JSONObject jsonObject=new JSONObject(string);
			String code=jsonObject.optString("code",null);
			if(code.equals("1")){
				JSONArray array=jsonObject.getJSONArray("commodity");
				for(int i=0;i<array.length();i++){
					JSONObject object=array.getJSONObject(i);
					String name=object.getString("name");
					String image=object.getString("image_url");
					String color=object.getString("color");
					String price=object.getString("price");
					String size=object.getString("size");
					String id=object.getString("id");
					Commodity commodity=new Commodity(name,"颜色:"+color+" 尺码:"+size,price,image,id);
					list.add(commodity);

				}

				handler.sendEmptyMessage(0);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	Handler handler=new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			baseAdapter.notifyDataSetChanged();
			return true;
		}
	});


	public boolean remove(String id,String tel){
		String url="http://139.199.196.199/index.php/home/index/remove?tel="+tel+"&id="+id;

		HttpURLConnection connection=null;
		BufferedReader bufferedReader=null;

		try {
			connection= (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.connect();
			if(connection.getResponseCode()==200){
				String line;
				bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				line=bufferedReader.readLine();
				Log.i("hhh",line);
				try {
					JSONObject object=new JSONObject(line);
					String code=object.getString("code");
					if(code.equals("1")){
						return true;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}


			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null){
				connection.disconnect();
			}
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

}
