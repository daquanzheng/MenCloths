package com.men_cloths.mainContent;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.men_cloths.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ViewFillerForEasy extends RelativeLayout{


    private ImageView left,center,right;
    private List<ImageView> list=new ArrayList<>();
    private float first,now;
    private final float WIDTH= ShopInfo.width;
    private ImageView catchImage;
    LayoutParams layoutParams1,layoutParams2,layoutParam;
    private float beginLeft=0,beginRight=0,bginCenter=0;





    public ViewFillerForEasy(Context context) {
        super(context);

    }

    public ViewFillerForEasy(Context context, AttributeSet attrs) {
        super(context, attrs);

        left=new ImageView(context);
        right=new ImageView(context);
        center=new ImageView(context);

        layoutParams1=new  LayoutParams(482,
                562);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        left.setLayoutParams(layoutParams1);

        layoutParams2=new  LayoutParams(482,
                562);
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        right.setLayoutParams(layoutParams2);

        layoutParam=new  LayoutParams(482,
                562);
        layoutParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        center.setLayoutParams(layoutParam);

        left.setImageDrawable(context.getResources().getDrawable(R.mipmap.clothes1));
        right.setImageDrawable(context.getResources().getDrawable(R.mipmap.clothes2));
        center.setImageDrawable(context.getResources().getDrawable(R.mipmap.clothes3));
        left.setScaleType(ImageView.ScaleType.CENTER_CROP);
        right.setScaleType(ImageView.ScaleType.CENTER_CROP);
        center.setScaleType(ImageView.ScaleType.CENTER_CROP);

        addView(left);
        addView(right);
        addView(center);


        center.setScaleY(1f);
        center.setAlpha(1f);
        left.setScaleY(0.8f);
        right.setScaleY(0.8f);
        left.setAlpha(0.9f);
        right.setAlpha(0.9f);
        Log.i("hhh", ShopInfo.width+"");

    }

    public ViewFillerForEasy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                first=event.getX();
                break;
            case  MotionEvent.ACTION_UP:
                if(first-now>100)
                {

                    catchImage=left;
                    left=center;
                    center=right;
                    right=catchImage;
                    left.setLayoutParams(layoutParams1);
                    right.setLayoutParams(layoutParams2);
                    center.setLayoutParams(layoutParam);
                    center.setScaleY(1f);
                    center.setAlpha(1f);
                    left.setScaleY(0.8f);
                    right.setScaleY(0.8f);
                    left.setAlpha(0.9f);
                    right.setAlpha(0.9f);
                    left.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    right.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    center.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    removeAllViews();
                    addView(left);
                    addView(right);
                    addView(center);

                }
                if(first-now<-100){

                    catchImage=right;
                    right=center;
                    center=left;
                    left=catchImage;
                    left.setLayoutParams(layoutParams1);
                    right.setLayoutParams(layoutParams2);
                    center.setLayoutParams(layoutParam);
                    center.setScaleY(1f);
                    center.setAlpha(1f);
                    left.setScaleY(0.8f);
                    right.setScaleY(0.8f);
                    left.setAlpha(0.9f);
                    right.setAlpha(0.9f);
                    left.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    right.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    center.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    removeAllViews();
                    addView(left);
                    addView(right);
                    addView(center);
                }
                first=0;
                now=0;
                break;
            case MotionEvent.ACTION_MOVE:
                now=event.getX();
                break;
        }

        return true;


    }




}
