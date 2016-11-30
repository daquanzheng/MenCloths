package com.men_cloths.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView editAddress,deleteAddress;
    CheckBox showAddressDetails;
    String str1,str2;
    LinearLayout addressAll;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.wode_address_item,null);
            editAddress = (TextView) convertView.findViewById(R.id.edit_address);
            showAddressDetails = (CheckBox) convertView.findViewById(R.id.show_address_details);
            deleteAddress = (TextView) convertView.findViewById(R.id.delete_address);
            deleteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addressList.remove(position);
                    GettingAddressAdapter.this.notifyDataSetChanged();
                }
            });

            final View finalConvertView = convertView;
            showAddressDetails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(finalConvertView!=null){
                        addressAll = (LinearLayout) finalConvertView.findViewById(R.id.address_all);
                        if(isChecked){
//                        Toast.makeText(context,"3333",Toast.LENGTH_SHORT).show();
                            addressAll.setVisibility(View.VISIBLE);
                            GettingAddressAdapter.this.notifyDataSetChanged();
                        }else {
//                        Toast.makeText(context,"4444",Toast.LENGTH_SHORT).show();
                            addressAll.setVisibility(View.GONE);
                            GettingAddressAdapter.this.notifyDataSetChanged();
                        }
                    }
                }
            });
            editAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
//                    intent.putExtra("name",str1);
//                    intent.putExtra("phone",str2);
                    intent.setClass(context, AddressEditActivity.class);
                    context.startActivity(intent);
//                    context.startActivityForResult(intent,110);
                }
            });
        }
        return convertView;
    }
}
