package com.men_cloths.mainContent;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/11/28.
 *
 * 此类为工具类，调用了第三方的短信验证接口
 *
 * 使用:
 *    使用前必须调用init()进行初始化;
 *    send(telphone)发送验证码到用户指定的手机
 *    commint( String code)：上传验证码
 *
 *    使用完成后必须调用回调注销
 *
 *
 */

public class MessageVerification{

    private final static String APPKEY="196900d9a47c0";
    private final static String APPSECRET="a00afccd48c75575d8b0100aaed82dad";
    private final static String COUNTRY="86";

    private String tel;
    private Context context;
    public MessageVerification(Context context,Handler handler){
        this.context=context;
        this.handler=handler;
    }
    private Handler handler;


    public void init(){
        SMSSDK.initSDK(context,APPKEY,APPSECRET);
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
               // Log.i("hhh","我已经顺利回调");
                Message message=Message.obtain();
                message.arg1=event;
                message.arg2=result;
                message.obj=data;
                message.what=1234;
                handler.sendMessage(message);
            }
        };

        SMSSDK.registerEventHandler(eh);

    }


    public void send(String tel){//发送验证码到用户手机
        this.tel=tel;
        SMSSDK.getVerificationCode(COUNTRY, tel, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String country, String phone) {
                return false;
            }
        });
    }

    public void commint( String code){//从用户手机中上传验证码到服务器
        SMSSDK.submitVerificationCode(COUNTRY,tel,code);
    }

    public void cancellation(){//注销验证码请求
        SMSSDK.unregisterEventHandler(new EventHandler());
    }

    public static void bianliHash(HashMap map){
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            Log.i("hhh","key="+key+"  value="+val);
        }
    }









}
