package com.men_cloths.mainContent;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/28.
 */

public class GpsUtil {
    private Context context;
    private Location location;

    private  Handler handler;
    public GpsUtil(Context context, Handler handler) {
        this.context = context;
        this.handler=handler;
    }

    private double jing, wei;
    private LocationManager locationManager;

    public void getAddressInfo() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        while (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location==null){
                location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
        new Thread(){
            public void run(){
                getHttp();
            }
        }.start();

    }


    public void getHttp(){
        HttpURLConnection connection=null;
        String path="http://apis.baidu.com/wxlink/here/here?lat=" +location.getLatitude()+ "&lng=" +location.getLongitude()+"&cst=1";
        BufferedReader reader=null;

        try {
            connection= (HttpURLConnection) new URL(path).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("apikey","b40c5fd6cf88ca538cd360c42d75c89d");
            connection.connect();
            if (connection.getResponseCode()==200){
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
                StringBuffer buffer=new StringBuffer();
                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }
                Log.i("hhh",buffer.toString());
                //Toast.makeText(this,buffer.toString(),Toast.LENGTH_SHORT).show();
                Message message=Message.obtain();
                //handler.sendMessage()
                message.obj=buffer.toString();
                handler.sendMessage(message);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                connection.disconnect();
            }
        }

    }






}
