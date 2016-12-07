package com.men_cloths.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CachetToFile {

    /**
     * 这是一个图片缓存机制。作者张文。
     *     加载图片之前先缓存，
     *     会清理时间超过三天的图片
     * */

    public static void  saveImage(String url, Context context,InputStream is){//保存图片
        FileOutputStream os=null;
        try {
             os=context.openFileOutput(filter(url,':','/','\\','?','*','>','<','|','"','\''),Context.MODE_PRIVATE);
             int i=0;
            while ((i=is.read())!=-1){
                os.write(i);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(os!=null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static InputStream getImage(String url,Context context){//获得图片
        try {
            return context.openFileInput(filter(url,':','/','\\','?','*','>','<','|','"','\''));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getstate(Context context,String url){
        url=filter(url,':','/','\\','?','*','>','<','|','"','\'');
        ImgaeStateSQL sql=new ImgaeStateSQL(context,"image",null,1);
        SQLiteDatabase db=sql.getWritableDatabase();
        String s="'"+url+"'"+","+"'"+System.currentTimeMillis()+"'";
        db.execSQL("insert into image (name,time)values(" +s+")");
        db.close();
    }

    public static boolean isloaded(Context context,String url){
        ImgaeStateSQL sql=new ImgaeStateSQL(context,"image",null,1);
        SQLiteDatabase db=sql.getWritableDatabase();
        Cursor c=db.rawQuery("select * from image",null);
        if(c==null){
            db.close();
            c.close();
            return false;
        }
        if(c!=null)
            while(c.moveToNext()){
                String s=c.getString(c.getColumnIndex("name"));
                if(s.equals(url)){
                    db.close();
                    c.close();
                    return true;
                }
            }
        c.close();
        db.close();
        return false;
    }


    private static String filter(String str,char ...chars){//字符过滤
        if(str==null){
            return null;
        }
        String result="";
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            boolean status=true;
            for(int j=0;j<chars.length;j++){
                if(c==chars[j]){
                    status=false;
                    j=chars.length;

                }
            }
            if(status){
                result+=c;
            }
        }

        return result;
    }

    public static void clearImage(Context context){//清理图片文件
        ImgaeStateSQL sql=new ImgaeStateSQL(context,"image",null,1);
        SQLiteDatabase db=sql.getWritableDatabase();

        Cursor c=db.rawQuery("select * from image",null);
        String time=null;
        String name=null;
        if(c!=null)
            while(c.moveToNext()){
                time=c.getString(c.getColumnIndex("time"));
                name=c.getString(c.getColumnIndex("name"));

            }
        if(time!=null){
            Long l=new Long(time);
            if(System.currentTimeMillis()-l>259200000)
            {
                db.execSQL("delete from image where time="+time);
                context.deleteFile(name);
            }
        }
        db.close();
    }


}
