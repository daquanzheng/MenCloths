package com.men_cloths.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.model.GettingAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class GettingAddressAdapter extends BaseAdapter{
    private Context context;
    private  List<GettingAddress> addressList = new ArrayList<>();
    private  LayoutInflater inflater;
    private  EditOnClickListenr editOnClickListener;
    public interface  EditOnClickListenr{//接口
        public void onClick(int itemid,String name,String phone);
        public void onClick(int position,String name);
    }
    public void setOnEidtOnClickListener(EditOnClickListenr editOnClickListener){
        this.editOnClickListener=editOnClickListener;
    }

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
    LinearLayout addressAll;
    String str1,str2,str3;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.wode_address_item,null);
            editAddress = (TextView) convertView.findViewById(R.id.edit_address);
            deleteAddress = (TextView) convertView.findViewById(R.id.delete_address);
            viewHolder = new ViewHolder();
            viewHolder.newname = (TextView) convertView.findViewById(R.id.name);
            viewHolder.newphone = (TextView) convertView.findViewById(R.id.phone);
            viewHolder.newaddress = (TextView) convertView.findViewById(R.id.address_details);
            convertView.setTag(viewHolder);

            final ViewHolder finalViewHolder1 = viewHolder;
            deleteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.i("删除的区域下标======>",""+position);
//                    addressList.remove(position);
//                    GettingAddressAdapter.this.notifyDataSetChanged();
                str3 = finalViewHolder1.newname.getText().toString();
                editOnClickListener.onClick(position,str3);
                }
            });

            showAddressDetails = (CheckBox) convertView.findViewById(R.id.show_address_details);
            final View finalConvertView = convertView;
            showAddressDetails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(finalConvertView !=null) {
                        addressAll = (LinearLayout) finalConvertView.findViewById(R.id.address_all);
                        if (isChecked) {
//                        Log.i("详细地址显示========>",""+finalConvertView.toString());
                            addressAll.setVisibility(View.VISIBLE);
                            GettingAddressAdapter.this.notifyDataSetChanged();
                        } else {
//                            Log.i("详细地址隐藏========>",""+finalConvertView.toString());
                            addressAll.setVisibility(View.INVISIBLE);
                            GettingAddressAdapter.this.notifyDataSetChanged();
                        }
                    }
                }
            });
            final ViewHolder finalViewHolder = viewHolder;
            editAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    str1 = finalViewHolder.newname.getText().toString();
                    str2 = finalViewHolder.newphone.getText().toString();
                    editOnClickListener.onClick(position,str1,str2);//当我点击某个item的编辑按钮然后通知activity
                }
            });
        }
        viewHolder = (ViewHolder) convertView.getTag();
        GettingAddress gettingAddress = addressList.get(position);
        viewHolder.newname.setText(gettingAddress.getName());
        viewHolder.newphone.setText(gettingAddress.getPhone());
        viewHolder.newaddress.setText(gettingAddress.getAddressDetails());
        return convertView;
    }
    public class ViewHolder{
        TextView newname;
        TextView newphone,newaddress;
    }
}
