package com.men_cloths.mainContent;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.men_cloths.R;
import com.men_cloths.adapter.Adapter;
import com.men_cloths.model.Shop;

public class Collection extends Activity{
	
	private ListView listView;
	private List<Shop> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		
		createData();
		listView=(ListView) findViewById(R.id.collect_list_view);
		listView.setAdapter(new Adapter().getSocllectAdapter(this, list));
		
		
		
		
		
	}
	
	public void createData(){
		list=new ArrayList<Shop>();
		Shop shop=new Shop(R.mipmap.ic_launcher,"马克华菲长袖衬衫男士韩版时尚修身纯色亚麻 白衬衣潮2016秋装新款",
				"颜色：白色 尺码：XL",
				"178.00");
		list.add(shop);
		
	}

}
