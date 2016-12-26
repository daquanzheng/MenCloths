package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.adapter.Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Coupon extends Activity{
	private ImageView back;
	private RadioGroup group;
	private List<Integer> list=new ArrayList<>();

	private	RadioButton radiao[]=new RadioButton[3];
	private	View views[]=new View[3];
	private ListView listView;
	private  SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.copon);
		group=(RadioGroup) findViewById(R.id.group);
		preferences=getSharedPreferences("login_info",MODE_PRIVATE);

		back = (ImageView) findViewById(R.id.back);
		
		radiao[0]=(RadioButton) findViewById(R.id.already_used);
		radiao[1]=(RadioButton) findViewById(R.id.can_use);
		radiao[2]=(RadioButton) findViewById(R.id.not_used);
		views[0]=findViewById(R.id.view1);
		views[1]=findViewById(R.id.view2);
		views[2]=findViewById(R.id.view3);
		listView= (ListView) findViewById(R.id.list_view);
		createDatebase1(preferences.getInt("coupon_used_count",0));
		listView.setAdapter(baseAdapter);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});




		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
					case R.id.back:
						finish();
						break;
				case R.id.already_used:

					createDatebase1(preferences.getInt("coupon_used_count",0));
					baseAdapter.notifyDataSetChanged();
					for(int i=0;i<3;i++)
					{
						if(i==0){
							views[i].setVisibility(View.VISIBLE);
						}
						else
							views[i].setVisibility(View.INVISIBLE);
					}
					break;
                case R.id.can_use:
					createDatebase2(preferences.getInt("coupon_notuesd_count",0));
					baseAdapter.notifyDataSetChanged();
                	for(int i=0;i<3;i++)
					{
						if(i==1){
							views[i].setVisibility(View.VISIBLE);
						}
						else
						views[i].setVisibility(View.INVISIBLE);
					}
					break;
               case R.id.not_used:
				   createDatebase3(preferences.getInt("coupon_expired_count",0));
				   baseAdapter.notifyDataSetChanged();
            	   for(int i=0;i<3;i++)
					{
						if(i==2){
							views[i].setVisibility(View.VISIBLE);
						}
						else
						views[i].setVisibility(View.INVISIBLE);
					}
	               break;
				}
				
			}
		});

		new Thread(){public void run(){getInfo();}}.start();//内容更新
	}

	public void createDatebase1(int len){
		list.clear();
		for(int i=0;i<len;i++){
			list.add(1);
		}
		
	}
	public void createDatebase2(int len){
		list.clear();
		for(int i=0;i<len;i++){
			list.add(2);
		}

	}
	public void createDatebase3(int len){
		list.clear();
		for(int i=0;i<len;i++){
			list.add(3);
		}

	}

	BaseAdapter baseAdapter=new Adapter().getPopupWindowAdapter(this, list);
//	PopupWindow pop;
//
//	BaseAdapter baseAdapter;
//	public void createPopupWindow(View view){
//		createDatebase1();
//		View v=LayoutInflater.from(this).inflate(R.layout.popupwindow_item,null);
//		ListView listview=(ListView) v.findViewById(R.id.list_view_popupwindow);
//		baseAdapter=new Adapter().getPopupWindowAdapter(this, list);
//		baseAdapter.notifyDataSetChanged();
//		listview.setAdapter(baseAdapter);
//
//
//		pop=new PopupWindow(v);
//		pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//		pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//		pop.showAsDropDown(view);
//
//	}


	private void getInfo(){
		String url="http://139.199.196.199/index.php/home/index/userinfo?token="+preferences.getString("token","")+"&tel="+preferences.getString("tel","");
		HttpURLConnection connection=null;
		BufferedReader reader=null;

		try {
			connection= (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			connection.connect();

			if(connection.getResponseCode()==200){
				String line="";
				reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				line=reader.readLine();
				Log.i("hhh",line);
				JSONObject o1=new JSONObject(line);
				String state=o1.optString("statue","");
				if(state.equals("1")){
					JSONObject object=o1.getJSONObject("info");
					Message message=Message.obtain();
					message.what=1;
					handler.sendMessage(message);
					SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
					SharedPreferences.Editor editor=preferences.edit();
					editor.putInt("coupon_expired_count",object.optInt("coupon_expired_count",0));
					editor.putInt("coupon_used_count",object.optInt("coupon_used_count",0));
					editor.putInt("coupon_notuesd_count",object.optInt("coupon_notuesd_count",0));
					editor.commit();

				}else {
					Message message=Message.obtain();
					message.what=-2;
					handler.sendMessage(message);
				}


			}else {
				Message message=Message.obtain();
				message.what=-1;
				handler.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally {
			if (connection!=null){
				connection.disconnect();
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Handler handler=new Handler(){

		public void handleMessage(Message message){
			switch (message.what){
				case 1:
					if(radiao[0].isChecked()){
						createDatebase1(preferences.getInt("coupon_used_count",0));
					}else if(radiao[1].isChecked()){
						createDatebase2(preferences.getInt("coupon_notuesd_count",0));
					}else if(radiao[2].isChecked()){
						createDatebase3(preferences.getInt("coupon_expired_count",0));
					}
					baseAdapter.notifyDataSetChanged();
					break;
				case -1:
					Toast.makeText(Coupon.this,"当前网络连接出现问题",Toast.LENGTH_SHORT).show();

					break;
				case -2:
					Toast.makeText(Coupon.this,"账号出现异常，或在其他地方已经登录",Toast.LENGTH_SHORT).show();

					break;

			}
		}
	};



}
