package com.men_cloths.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.Shop;

import java.util.List;

public class Adapter {
	
	
	
	 
	 public SocllectAdapter getSocllectAdapter(Context context,List<Shop> list){
		 return new SocllectAdapter(context,list);
	 }
	
	 private class SocllectAdapter extends BaseAdapter{
		 
		 Context context;
		 List<Shop> list;
		 SocllectAdapter(Context context,List<Shop> list){
			 this.context=context;
			 this.list=list;
		 }
		 
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater=LayoutInflater.from(context);
			Holder holder;
			if(convertView==null)
			{
				convertView=inflater.inflate(R.layout.collect_list_item,null);
				holder=new Holder();
				holder.imageview=(ImageView) convertView.findViewById(R.id.image_collect);
				holder.title=(TextView) convertView.findViewById(R.id.title);
				holder.size=(TextView) convertView.findViewById(R.id.size);
				holder.price=(TextView) convertView.findViewById(R.id.price);
				convertView.setTag(holder);
				
			}else{
				holder=(Holder) convertView.getTag();
				
			}
			holder.imageview.setImageResource(list.get(position).getImage());
			holder.title.setText(list.get(position).getTitle());
			holder.size.setText(list.get(position).getSize());
			holder.price.setText(list.get(position).getPrice());
			
			
			
			
			
			
			
			
			return convertView;
		}
		
		class Holder{
			ImageView imageview;
			TextView title,size,price;
			
		}
		
	}
	 
	 
	 
	private class PopupWindowAdapter extends BaseAdapter{
		
		 
		 Context context;
		 List<Integer> list;
		 PopupWindowAdapter(Context context,List<Integer> list){
			 this.context=context;
			 this.list=list;
		 }
		 
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater=LayoutInflater.from(context);
			TextView textView=null;
			
			if(convertView==null)
			{
				convertView=inflater.inflate(R.layout.xml_item,null);
				textView= (TextView) convertView.findViewById(R.id.status);
				convertView.setTag(textView);
			}else {
				textView= (TextView) convertView.getTag();
			}
			if(list.get(position)==2) {
				textView.setText("未使用");
			}else if(list.get(position)==1){
				textView.setText("已使用");
			}else {
				textView.setText("已过期");
			}

			
			return convertView;
		}
		
	} 
	
	
	public PopupWindowAdapter getPopupWindowAdapter(Context context,List list){
		return new PopupWindowAdapter(context,list);
	}

}
