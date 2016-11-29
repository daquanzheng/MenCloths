package com.men_cloths.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.mainContent.AddressEditActivity;
import com.men_cloths.model.GettingAddress;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class GettingAddressAdapter extends BaseAdapter{
    Context context;
    List<GettingAddress> addressList;
    LayoutInflater inflater;

    public GettingAddressAdapter(Context context,List<GettingAddress> addressList){
        this.context = context;
        this.addressList = addressList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    TextView editAddress;
    TextView deleteAddress;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.wode_address_item,null);
            editAddress = (TextView) convertView.findViewById(R.id.edit_address);
            deleteAddress = (TextView) convertView.findViewById(R.id.delete_address);
            editAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    intent.setClass(context, AddressEditActivity.class);
                    context.startActivity(intent);
                }
            });
            deleteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addressList.remove(position);
                }
            });
        }
        return convertView;
    }
}
