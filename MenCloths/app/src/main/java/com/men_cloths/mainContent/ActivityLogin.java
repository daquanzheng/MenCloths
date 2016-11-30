package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;


import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.men_cloths.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLogin extends Activity {
    TextView login;
    TextView register;
    ImageView tencent,wechat,sina,taobao,showpassword,camera;
    EditText phonenumb,password;
    String phoneNumb,passWord;
    int i=0;
    PopupWindow popupWindow;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定控件
        login=(TextView) findViewById(R.id.textview_loginpage_login);
        register=(TextView)findViewById(R.id.textview_loginpage_register);
        tencent = (ImageView) findViewById(R.id.tencent);
        wechat = (ImageView) findViewById(R.id.wechat);
        sina = (ImageView) findViewById(R.id.sina);
        taobao = (ImageView) findViewById(R.id.taobao);
        showpassword=(ImageView)findViewById(R.id.img_login_showpassword);
        camera=(ImageView)findViewById(R.id.img_login_cawera);
        phonenumb=(EditText)findViewById(R.id.edit_login_phonenumb);
        password=(EditText)findViewById(R.id.edit_login_password);
        //绑定监听事件
        showpassword.setOnClickListener(onClickListener);
        camera.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
        register.setOnClickListener(onClickListener);
        tencent.setOnClickListener(onClickListener);
        wechat.setOnClickListener(onClickListener);
        sina.setOnClickListener(onClickListener);
        taobao.setOnClickListener(onClickListener);

        phonenumb.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher1);
    }
    TextWatcher textWatcher1=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            passWord=s.toString();
        }
    };
    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            phoneNumb = s.toString();

        }
    };



    Intent intent=new Intent();
    URL url;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textview_loginpage_login:
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            login();
//                            super.run();
//
//                        }
//                    }.start();
                   intent.setClass(ActivityLogin.this,MyMainpageInActivity.class);
                  startActivity(intent);
                    break;
                case R.id.textview_loginpage_register:
                    intent.setClass(ActivityLogin.this,ActivityRegister.class);
                    startActivity(intent);
                    break;
                case R.id.tencent:
                    break;
                case R.id.wechat:
                    break;
                case R.id.sina:
                    break;
                case R.id.taobao:
//                    try {
//                        url = new URL("http://api.m.taobao.com/rest/api2.do");
//                        HttpURLConnection httpURL = (HttpURLConnection) url.openConnection();
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    break;
                case R.id.img_login_cawera:
                    CreatePopuwindow();
                    break;
                case R.id.img_login_showpassword:
                    i++;
                    if(i%2==1){
                        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }else{
                        password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                    break;
            }
        }
    };
    public void login(){
        String httpURL="http://10.0.0.2:3306/index.php/Home/Index/LOGIN?"+"username="+phoneNumb+"password="+passWord;
        String httpURL1="http://10.0.2.2:3306/index.php/Home/Index/register?username=15213613201&password=123456&repassword=123456";
        try {
            StringBuilder stringBuilder=new StringBuilder();
            URL url=new URL(httpURL1);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setConnectTimeout(50000);
            httpURLConnection.connect();
//           Log.i("getResponseCode",""+httpURLConnection.getResponseCode());if(httpURLConnection.getResponseCode()==200){
                BufferedReader bufferedReader=new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                String str;
                while((str=bufferedReader.readLine())!=null){
                    stringBuilder.append(str);
                }
                Log.i("data",stringBuilder.toString());
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void CreatePopuwindow(){
        view=getLayoutInflater().inflate(R.layout.popuwindow_login_camera,null);
        popupWindow=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(camera, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        Button camera=(Button)view.findViewById(R.id.btn_camera);
        Button dismiss=(Button)view.findViewById(R.id.btn_longin_dismiss);
        Button photo=(Button)view.findViewById(R.id.btn_choice_photo);
        if(dismiss!=null){
            dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, getUri());
                    startActivityForResult(intent, 1000);
                }
            });
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
    private Uri getUri() {

        return Uri.fromFile(getFile());
    }

    /**
     * 该方法用于获取指定路径 和 名字 的file
     * @return
     */
    private File getFile() {
        File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "CameraDemo");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //将图片保存的名字设置为当前拍照的时间
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = format.format(new Date());
        File file = new File(filePath.getPath() + File.separator + name + ".jpg");
        return file;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                Log.d("print", "拍照成功");
            }
        }
    }
}
