package com.men_cloths.model;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ActivityManager extends Application{
    private static ActivityManager manager;
    private ActivityManager(){}
    private static List<Activity> list;
    public static MemoryCache memoryCache=MemoryCache.getMemoryCache(10);

    public static ActivityManager getActivityManager() {
        if(manager==null){
            manager=new ActivityManager();
            list=new ArrayList<>();
        }
        return manager;
    }
    public void add(Activity activity){
        list.add(activity);
    }
    public void exit(){
        for(Activity activity:list){
            if(activity!=null){
                activity.finish();
            }
        }
        System.exit(0);
    }
}
