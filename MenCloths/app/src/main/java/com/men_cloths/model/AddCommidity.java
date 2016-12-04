package com.men_cloths.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/4.
 */

public class AddCommidity {
    public static void add(String tel,String name,String color,String size,String price,File fileImage) {
        HttpUtils util = new HttpUtils();
        HashMap<String, String> hash = new HashMap<>();
        String str = "";
        hash.put("tel", tel);
        hash.put("name", name);
        hash.put("color", color);
        hash.put("size", size);
        hash.put("price", price);
        HashMap<String, File> file = new HashMap<>();
        file.put("test", fileImage);

        try {
            str = util.post("http://www.android.com/index.php/home/index/addcom", hash, file);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
