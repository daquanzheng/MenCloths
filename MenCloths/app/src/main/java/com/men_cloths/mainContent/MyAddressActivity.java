package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.adapter.GettingAddressAdapter;
import com.men_cloths.model.GettingAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyAddressActivity extends Activity{
    ImageView back;
    Button addNewAddress;
    ListView listView;
    List<GettingAddress> lists = new ArrayList<>();

    TextView name,phone;
    TextView editAddress,deleteAddress;
    String str1,str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_address);
        addNewAddress = (Button)findViewById(R.id.add_newaddress);
        back = (ImageView) findViewById(R.id.back);
        listView = (ListView) findViewById(R.id.my_address);

        GettingAddressAdapter gettingAddressAdapter = new GettingAddressAdapter(MyAddressActivity.this,getLists());
        listView.setAdapter(gettingAddressAdapter);
//        deleteAddress = (TextView) findViewById(R.id.delete_address);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                editAddress = (TextView) view.findViewById(R.id.edit_address);
//                name = (TextView) view.findViewById(R.id.name);
//                phone = (TextView) view.findViewById(R.id.phone);
//                editAddress.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        str1 = name.getText().toString();
//                        str2 = phone.getText().toString();
//                        intent.putExtra("name",str1);
//                        intent.putExtra("phone",str2);
//                        intent.setClass(MyAddressActivity.this, AddressEditActivity.class);
//                        Log.i("跳转到编辑地址界面=====>",""+str1+str2);
//                        startActivityForResult(intent,110);
//                    }
//                });
//            }
//        });

//        editAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
////                intent.putExtra("name",str1);
////                intent.putExtra("phone",str2);
//                intent.setClass(MyAddressActivity.this, AddressEditActivity.class);
//                MyAddressActivity.this.startActivity(intent);
////                startActivityForResult(intent,110);
//            }
//        });

        back.setOnClickListener(onClickListener);
        addNewAddress.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.add_newaddress:
                    Intent intent = new Intent(MyAddressActivity.this,AddNewAddressActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

//    @Override
//    protected void onActivityResult(int requestCode,
//                                    int resultCode,
//                                    Intent data) { //包含的数据，用bundle接收
//        super.onActivityResult(requestCode, resultCode, data);
//        Bundle bundle = data.getExtras();
//        String s1 = bundle.getString("name");
//        String s2 = bundle.getString("phone");
//        name.setText(s1);
//        phone.setText(s2);
//    }

    public List<GettingAddress> getLists(){
        for(int i=0;i<3;i++){
            GettingAddress gettingAddress = new GettingAddress();

            lists.add(gettingAddress);
        }
        return lists;
    }
}
