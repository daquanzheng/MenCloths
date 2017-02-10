package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.men_cloths.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/30.
 */

public class SoftPassWorldForChange extends Activity {

    private ImageView back;
    private String passwd="";
    private EditText editText[]=new EditText[4];
    private int[] id={R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4};
    private Button button;
    private String token,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_passwd_changed);
        SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
        token=preferences.getString("token","");
        tel=preferences.getString("tel","");

        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SoftPassWorldForChange.this,SoftPassword.class);
                startActivity(intent);
            }
        });
        for(int i=0;i<4;i++){
            editText[i]= (EditText) findViewById(id[i]);
        }
        button= (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwd="";
                for(int i=0;i<4;i++){
                    String c=editText[i].getText().toString();
                    if(c==null){
                        Toast.makeText(SoftPassWorldForChange.this,"密码必须满足四位",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        passwd+=c;
                    }
                }
                getInfo();


            }
        });

    }

    public void getInfo(){
        new Thread(){
            public void run(){
                String url="http://804904.ichengyun.net/index.php/home/index/userinfo?token="+token+"&tel="+tel;
                HttpURLConnection connection=null;
                BufferedReader reader=null;

                try {
                    connection= (HttpURLConnection) new URL(url).openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    connection.connect();

                    if(connection.getResponseCode()==200){
                        String line="";
                        reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        line=reader.readLine();
                        Log.i("hhh",line);
                        JSONObject o1=new JSONObject(line);
                        String state=o1.optString("statue","");
                        if(state.equals("1")){
                            JSONObject object=o1.getJSONObject("info");

                            Message message=Message.obtain();
                            message.what=1;
                            message.obj=object.getString("sofa_passwd");
                            handler.sendMessage(message);
                        }else {
                            Message message=Message.obtain();
                            message.what=-1;
                            handler.sendMessage(message);
                        }


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }.start();
    }

    Handler handler=new Handler(){
      public void handleMessage(Message message){
          switch (message.what){
              case 1:
                  if(passwd.equals(message.obj)) {
                      Intent intent = new Intent(SoftPassWorldForChange.this, Passwd.class);
                      startActivity(intent);
                  }else {
                      Toast.makeText(SoftPassWorldForChange.this,"密码错误,新用户默认密码为1234",Toast.LENGTH_SHORT).show();
                  }
                  break;
              case -1:
                  Toast.makeText(SoftPassWorldForChange.this,"网络错误，无法实现网络请求",Toast.LENGTH_SHORT).show();
                  break;
          }

      }
    };
}
