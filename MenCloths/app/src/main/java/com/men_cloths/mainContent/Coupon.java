package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.men_cloths.R;
import com.men_cloths.adapter.Adapter;

import java.util.ArrayList;
import java.util.List;

public class Coupon extends Activity{

	private RadioGroup group;
	private List<Integer> list=new ArrayList<>();

	private	RadioButton radiao[]=new RadioButton[3];
	private	View views[]=new View[3];
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.copon);
		group=(RadioGroup) findViewById(R.id.group);
		
		radiao[0]=(RadioButton) findViewById(R.id.already_used);
		radiao[1]=(RadioButton) findViewById(R.id.can_use);
		radiao[2]=(RadioButton) findViewById(R.id.not_used);
		views[0]=findViewById(R.id.view1);
		views[1]=findViewById(R.id.view2);
		views[2]=findViewById(R.id.view3);
		listView= (ListView) findViewById(R.id.list_view);
		createDatebase1(3);
		listView.setAdapter(baseAdapter);



		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
				case R.id.already_used:

					createDatebase1(3);
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
					createDatebase2(3);
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
				   createDatebase3(3);
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

}
