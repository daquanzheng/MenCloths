package com.men_cloths;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.mainContent.MessageVerification;

public class MainActivity extends Activity{

    TextView tv;
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            Toast.makeText(MainActivity.this, (String) message.obj,Toast.LENGTH_SHORT).show();
            tv.setText((String) message.obj);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
//        GpsUtil gpsUtil=new GpsUtil(this,handler);
//        gpsUtil.getAddressInfo();
         Log.i("hhh","er ctbyuhaoskda");
         MessageVerification.load("15123142687","1234");


    }
}