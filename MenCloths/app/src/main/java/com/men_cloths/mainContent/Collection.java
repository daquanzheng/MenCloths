package com.men_cloths.mainContent;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.adapter.Adapter;
import com.men_cloths.model.Shop;

import java.util.ArrayList;
import java.util.List;
/**
 * //还需要删除远程数据库的内容
 *
 * */
public class Collection extends Activity{
	
	private ListView listView;
	private List<Shop> list;
	private ImageView imageView;
	private BaseAdapter baseAdapter;
	private int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		init();
		createData();
		listView=(ListView) findViewById(R.id.collect_list_view);
		baseAdapter=new Adapter().getSocllectAdapter(this, list);
		listView.setAdapter(baseAdapter);
		onclickForitem();
		
		
		
		
		
	}

	public void init(){
		imageView= (ImageView) findViewById(R.id.back);
		imageView.setOnClickListener(listener);
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
		list=new ArrayList<Shop>();
		Shop shop=new Shop(R.mipmap.ic_launcher,"马克华菲长袖衬衫男士韩版时尚修身纯色亚麻 白衬衣潮2016秋装新款",
				"颜色：白色 尺码：XL",
				"178.00");
		list.add(shop);
		
	}

	public void onclickForitem(){
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Collection.this.position=position;
				TextView textView= (TextView) view.findViewById(R.id.cancel);
				textView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						list.remove(Collection.this.position);
						//还需要删除远程数据库的内容
						baseAdapter.notifyDataSetChanged();
					}
				});

			}
		});
	}

}
