package com.men_cloths.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/5.
 */

public class HasLogin {
   public static  boolean hasLogin(Context context){
       SharedPreferences sharedPreferences=context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
       return sharedPreferences.getBoolean("islogin",false);
   }
   public static  boolean isfirst(Context context){
       SharedPreferences sharedPreferences=context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
       return sharedPreferences.getBoolean("isfirst",true);
   }
}
