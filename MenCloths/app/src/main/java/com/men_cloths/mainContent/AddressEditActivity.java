package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/29.
 */
public class AddressEditActivity extends Activity{
    ImageView back;
    TextView saveAddress;
    EditText editName,editPhone,editAddressName,editStreet;
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_address_edit);
        //绑定控件
        back = (ImageView) findViewById(R.id.back);
        saveAddress = (TextView) findViewById(R.id.save_address);
        editName = (EditText) findViewById(R.id.edit_name);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editAddressName = (EditText) findViewById(R.id.edit_address_name);
        editStreet = (EditText) findViewById(R.id.edit_street);
        intent = getIntent();
        bundle = intent.getExtras();
        editName.setText(bundle.getString("name"));
        editPhone.setText(bundle.getString("phone"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        saveAddress();
                    }
                }.start();
            }
        });

    }
    public void saveAddress(){
        String string = "http://139.199.196.199/index.php/Home/Address/saveAddress";
        try {
            URL url = new URL(string+"?oldname="+bundle.getString("name")+"&name="+editName.getText()+"&phone="+editPhone.getText()+
                    "&address="+editAddressName.getText()+editStreet.getText());
            Log.i("url=======>",""+url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            finish();
            return true;
        }
    });
}
