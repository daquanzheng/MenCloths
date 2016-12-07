package com.men_cloths.model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MemoryCache{
    private static MemoryCache cache;
    private  int total=10;
    private int position=0;
    private Bitmap [] bitmaps;
    private String point[];
    private MemoryCache(){}


    public void setTotal(int total){
        this.total=total;
    }

    public void add(String key,Bitmap bitmap){
       if(!has(key)){
           if(position<total){
               bitmaps[position]=bitmap;
               point[position]=key;
               position++;
           }else {
               position=0;
               bitmaps[position]=bitmap;
               point[position]=key;
               position++;
           }
       }
    }

    public Bitmap get(String key){
      if(key==null ){
          return null;
      }
      for(int i=0;i<point.length;i++){
          if(point[i]==null || bitmaps[i]==null){
              return null;
          }
          if(point[i].equals(key)){
              return bitmaps[i];
          }
      }
        return null;
    }

    private boolean has(String key){
        if(key==null){
            return false;
        }
        for(String i:point){
            if(i==null){
                return false;
            }
            if(i.equals(key)){
                return true;
            }
        }
        return false;
    }

    public static MemoryCache getMemoryCache(int total){
        if(cache==null){
            cache=new MemoryCache();
            cache.total=total;
            cache.bitmaps=new Bitmap[total];
            cache.point=new String[total];
        }
        return cache;
    }


}
