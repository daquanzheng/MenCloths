package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.model.Constants;
import com.men_cloths.model.Login;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLogin extends Activity {
   private TextView login;
   private TextView register;
    ImageView tencent,wechat,sina,taobao,camera,show;
   private EditText  tel,passwd;
   private SsoHandler mSsoHandler;
   private Oauth2AccessToken mAccessToken;
   private String touke,uid;
    int i=0;
    View view;
    PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData(savedInstanceState);
        //绑定控件
        login=(TextView) findViewById(R.id.textview_loginpage_login);
        register=(TextView)findViewById(R.id.textview_loginpage_register);
        tencent = (ImageView) findViewById(R.id.tencent);
        wechat = (ImageView) findViewById(R.id.wechat);
        sina = (ImageView) findViewById(R.id.sina);
        taobao = (ImageView) findViewById(R.id.taobao);
        camera=(ImageView)findViewById(R.id.img_login_camera);
        show=(ImageView)findViewById(R.id.img_login_show);
        tel= (EditText) findViewById(R.id.tel);
        passwd= (EditText) findViewById(R.id.passwd);
        //绑定监听事件
        login.setOnClickListener(onClickListener);
        register.setOnClickListener(onClickListener);
        tencent.setOnClickListener(onClickListener);
        wechat.setOnClickListener(onClickListener);
        sina.setOnClickListener(onClickListener);
        taobao.setOnClickListener(onClickListener);
        show.setOnClickListener(onClickListener);
        camera.setOnClickListener(onClickListener);

    }
    Intent intent=new Intent();
    URL url;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textview_loginpage_login:
                    Login.login(tel.getText().toString(),passwd.getText().toString(),ActivityLogin.this,handler);
                    //intent.setClass(ActivityLogin.this,MyMainpageInActivity.class);
                   // startActivity(intent);
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
                    AuthInfo mAuthInfo = new AuthInfo(ActivityLogin.this, Constants.APP_KEY, Constants.REDIRECT_URL,"");
                    mSsoHandler= new SsoHandler(ActivityLogin.this, mAuthInfo);
                    mSsoHandler.authorize(new AuthListener());
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
                case R.id.img_login_camera:
                    createPopuwindow();
                    break;
                case R.id.img_login_show:
                    i++;
                    Log.i("I=========",""+i);
                    if(i%2==1){
                        passwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }else{
                        passwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                    break;
            }
        }
    };

    Handler handler=new Handler(){

        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                     finish();
                    break;
                case -1:
                    Toast.makeText(ActivityLogin.this,"你的账号或者密码不正确",Toast.LENGTH_SHORT).show();
                    break;
                case 666:
                    finish();
                    break;
                case 555:
                    Toast.makeText(ActivityLogin.this,"第三方登录失败",Toast.LENGTH_SHORT).show();
                    break;
                case -123:
                    Toast.makeText(ActivityLogin.this,"网络不太好，请检查连接",Toast.LENGTH_SHORT).show();
                    break;
                case -99:
                    Toast.makeText(ActivityLogin.this,"账户名或者密码不能为空",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            //Toast.makeText(MainActivity.this,"我已经顺利回调",Toast.LENGTH_SHORT).show();
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //Toast.makeText(MainActivity.this,"我已经顺利回调"+mAccessToken,Toast.LENGTH_SHORT).show();

            // String mcode = values.getString("code", "");
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                // AccessTokenKeeper.writeAccessToken(MainActivity.this, mAccessToken);
                Log.i("hahaha",mAccessToken+"------->548487");
                touke=mAccessToken.getToken();
                uid=mAccessToken.getUid();
                Toast.makeText(ActivityLogin.this,"登录成功", Toast.LENGTH_SHORT).show();
                new Thread(){
                    public void run(){
                        regForOhther(touke);
                    }
                }.start();
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");
            }
        }
        @Override
        public void onCancel() {
        }
        @Override
        public void onWeiboException(WeiboException e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        switch (requestCode) {
            case RESULT_CAPTURE:
                if (resultCode == RESULT_OK) {
                    starCropPhoto(Uri.fromFile(tempFile));
                }
                break;
            case RESULT_PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    starCropPhoto(uri);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (intent != null) {
                        setPicToView(intent);
                    }
                }
                break;
            default:
                break;
        }
    }
    public void regForOhther(String token){
        String url="http://804904.ichengyun.net/index.php/home/index/three?token="+token;
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode()==200){
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String tel=reader.readLine();
                try {
                    JSONObject object=new JSONObject(tel);
                    tel=object.getString("tel");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SharedPreferences preferences=getSharedPreferences("login_info",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("token",touke);
                editor.putString("tel",tel);
                editor.putString("message","新浪微博用户");
                editor.putBoolean("islogin",true);
                editor.commit();
                Message message=Message.obtain();
                message.what=666;
                handler.sendMessage(message);
            }else {
                Message message=Message.obtain();
                message.what=555;
                handler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
        }
    }
    private static final int RESULT_CAPTURE = 100;
    private static final int RESULT_PICK = 101;
    private static final int CROP_PHOTO = 102;
    private File tempFile;




    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        }else{
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath()+"/clipHeaderLikeQQ/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }
    public void createPopuwindow(){
        view =getLayoutInflater().inflate(R.layout.popuwindow_login_camera,null);
        popupWindow=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(camera,Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        Button dissmiss=(Button)view.findViewById(R.id.btn_longin_dismiss);
        Button photo=(Button)view.findViewById(R.id.btn_choice_photo);
        Button camera=(Button)view.findViewById(R.id.btn_camera);
        dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "请选择图片"), RESULT_PICK);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, RESULT_CAPTURE);
            }
        });

    }
    /**
     * 打开截图界面
     * @param uri 原图的Uri
     */
    public void starCropPhoto(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipHeaderActivity.class);
        intent.setData(uri);
        intent.putExtra("side_length", 200);//裁剪图片宽高
        startActivityForResult(intent, CROP_PHOTO);


        //调用系统的裁剪
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", crop);
//        intent.putExtra("outputY", crop);
//        intent.putExtra("return-data", true);
//        intent.putExtra("noFaceDetection", true);
//        startActivityForResult(intent, CROP_PHOTO);
    }
    private void setPicToView(Intent picdata) {
        Uri uri = picdata.getData();
        if (uri == null) {
            return;
        }
        Log.i("hhh",uri+"");
        camera.setImageURI(uri);
    }
    /**
     *
     * @param dirPath
     * @return
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }



}
