package com.men_cloths.model;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyButton extends RelativeLayout {

    private ImageView imageView;
    private TextView textView;

    public MyButton(Context context) {
        super(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mybutton_relativelayout, this, true);
        this.imageView = (ImageView) findViewById(R.id.mybutton_imgview);
        this.textView = (TextView) findViewById(R.id.mybutton_textview);
        this.setClickable(true);
        this.setFocusable(true);
    }

    public void setImgResource(int resourceId) {
        this.imageView.setImageResource(resourceId);
    }

    public void setText(String text) {
        this.textView.setText(text);
    }

    public void setTextColor(int color) {
        this.textView.setTextColor(color);
    }

    public void setTextSize(float size) {
        this.textView.setTextSize(size);
    }
}
