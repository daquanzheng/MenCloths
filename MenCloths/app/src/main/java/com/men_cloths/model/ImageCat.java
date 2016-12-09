package com.men_cloths.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ImageCat extends View{
    private Bitmap bitmap=null;
    private Paint paint;


    public ImageCat(Context context) {
        super(context);
    }

    public ImageCat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageCat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
    }

    private void init(){
        bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.clothes1);
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.button_red));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
    }
}
