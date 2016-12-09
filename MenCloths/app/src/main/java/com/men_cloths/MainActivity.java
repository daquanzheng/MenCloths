package com.men_cloths;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity{

//    /**
//     * message.arg1=event;
//     message.arg2=result;
//     message.obj=data;*/
//    TextView tv;
//    Handler handler=new Handler(){
//        public void handleMessage(Message message){
//
//            int event=message.arg1;
//            int result=message.arg2;
//            Object data=message.obj;
//            if (result == SMSSDK.RESULT_COMPLETE) {
//                    //回调完成
//                Log.i("hhh","----------------------回调完成");
//
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        //提交验证码成功
//                        Log.i("hhh","----------------------提交验证码成功");
//                    HashMap<String,Object> hashMap= (HashMap<String, Object>) data;
//                    MessageVerification.bianliHash(hashMap);
//
//                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
//                        //获取验证码成功
//                        Log.i("hhh","----------------------获取验证码成功");
//
//                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
//                        //返回支持发送验证码的国家列表
//                        Log.i("hhh","----------------------返回支持发送验证码的国家列表");
//                }
//            }else{
//                    ((Throwable)data).printStackTrace();
//                }
//        }
//    };
//    EditText editText;
//    String code;
//    Button button;
//    MessageVerification verification;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        tv= (TextView) findViewById(R.id.tv);
////        GpsUtil gpsUtil=new GpsUtil(this,handler);
////        gpsUtil.getAddressInfo();
//        editText= (EditText) findViewById(R.id.editText);
//        button= (Button) findViewById(R.id.button2);
//        verification=new MessageVerification(this,handler);
//
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                code=s.toString();
//
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verification.init();
//                verification.send("15123142687");
//            }
//        });
//
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verification.commint(code);
//                Toast.makeText(MainActivity.this,"已发送成功",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        verification.cancellation();
//        //HasLogin.hasLogin(this);
//    }

    private TextView tuku,xiangche,paizhao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void init(){
        tuku= (TextView) findViewById(R.id.t1);
        xiangche= (TextView) findViewById(R.id.t2);
        paizhao= (TextView) findViewById(R.id.t3);
    }
}