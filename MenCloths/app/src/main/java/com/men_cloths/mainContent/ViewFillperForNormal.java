package com.men_cloths.mainContent;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.men_cloths.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class ViewFillperForNormal extends View {

    public static final float WIDTH= ShopInfo.WIDTH;
    private Bitmap left,center,right;
    private Matrix matrixLeft,matrixCenter,matrixRight;
    private float width,height;
    private float v=0,h=0;
    private List<Float> list=new ArrayList();
    private float up=0,down=0;
    //private float[] pic={R.mipmap.clothes1,R.mipmap.clothes2,R.mipmap.clothes3};
    private int picLetf=R.mipmap.clothes1;
    private  int picright=R.mipmap.clothes2;
    private  int piccenter=R.mipmap.clothes3;
    private  int piccatch;
    private int cursor=2;
    interface CursorChanged{
        public void changedleft(int c);
        public void changedright(int c);
    }
    CursorChanged changed;
    public void setChangedListener(CursorChanged changed){
        this.changed=changed;
    }

    public void setCursor(boolean b){
        if(b){
            if(cursor==3){
                cursor=1;
            }else {
                cursor++;
            }

        }else{
            if(cursor==1){
                cursor=3;
            }else {
                cursor--;
            }

        }
    }


    public ViewFillperForNormal(Context context) {
        super(context);
        init(context);
       // Log.i("hh","一开始");
    }

    public ViewFillperForNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    //    Log.i("hh","一开始");
    }

    public ViewFillperForNormal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
     //   Log.i("hh","一开始");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(left,matrixLeft,null);
        canvas.drawBitmap(right,matrixRight,null);
        canvas.drawBitmap(center,matrixCenter,null);


    }



    public void setMatrix(Matrix matrixLeft, Matrix matrixCenter, Matrix matrixRight) {
        this.matrixLeft = matrixLeft;
        this.matrixRight=matrixRight;
        this.matrixCenter=matrixCenter;
        invalidate();//
    }

    public void init(Context context){
        left= BitmapFactory.decodeResource(context.getResources(), R.mipmap.clothes1);
        right= BitmapFactory.decodeResource(context.getResources(), R.mipmap.clothes2);
        center= BitmapFactory.decodeResource(context.getResources(), R.mipmap.clothes3);
        width=left.getWidth();
        height=left.getHeight();
        matrixLeft=new Matrix();
        matrixRight=new Matrix();
        matrixCenter=new Matrix();

        float y=(height-height*0.8f)/2;
        float x=((WIDTH-width)*0.8f)/2;

        matrixLeft.setValues(getfloats(0,y,1,0.8f));
        matrixRight.setValues(getfloats(WIDTH-width+x,y,1,0.8f));
        matrixCenter.setValues(getfloats((WIDTH-width)/2,0,1,1));
    }

    public float[] getfloats(float x,float y,float a,float b){
        float[] defaults={
                a ,h, x,
                v ,b, y,
                0 ,0, 1
        };
        v=0;
        h=0;
        return defaults;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                down=event.getX();

                break;
            case MotionEvent.ACTION_UP:

                up=event.getX();
                float min,max;
                min=getMin();max=getMax();
                float abs=max-min;

              //  Log.i("hh",up+"");
             //   Log.i("hh",down+"");
                if(up>down){
                    //右滑
                    setAnimation(true,600);
                    setCursor(false);
                    changed.changedleft(cursor);
                    //reset(true);
                  // Log.i("hh","酱油-----"+cursor);


                }else if(up<down){
                    //左滑
                    setAnimation(false,600);
                    setCursor(true);
                    changed.changedright(cursor);
                //   Log.i("hh","大米-----"+cursor);
                   // reset(false);

                }

                break;
            case MotionEvent.ACTION_MOVE:
                float f=event.getX();
                list.add(f);

                break;
        }

        return true;
    }

    private float getMin(){
        float min=0f;
        for(float i:list){
            if(i<min){
                min=i;
            }
        }
        return min;
    }

    private float getMax(){
        float max=0f;
        for(float i:list){
            if(i>max){
                max=i;
            }
        }
        return max;
    }

    private boolean isdandiao(){


        return false;
    }


    public void setAnimation(boolean b,int time){


//        float v1,v2,v3;
//        float s1=0,s2=0,s3=0;
//        for(int i=0;i<time;i++)
//        {
//            if(b){//右移
//                v1=(WIDTH-width)/time;
//                v2=((WIDTH-width)/2)/time;
//                v3=(WIDTH/2-width/2)/time;
//                s1+=v1;
//                s2+=v2;
//                s3+=v3;
//                matrixLeft.setValues(getfloats(s2,0,1,1));
//                matrixRight.setValues(getfloats((WIDTH-width)-s3,0,1,1));
//                matrixCenter.setValues(getfloats((WIDTH-width)/2+s2,0,1,1));
//                invalidate();
//
//            }else {//左移
//                v1=(WIDTH-width)/time;
//                v2=((WIDTH-width)/2)/time;
//                v3=(WIDTH/2-width/2)/time;
//                s1+=v1;
//                s2+=v2;
//                s3+=v3;
//                matrixLeft.setValues(getfloats(s1,0,1,1));
//                matrixRight.setValues(getfloats((WIDTH-width)-s3,0,1,1));
//                matrixCenter.setValues(getfloats((WIDTH-width)/2-s2,0,1,1));
//                invalidate();
//            }
//
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
        animation(b,time);
      //  Log.i("hh","炸酱");







    }

    public void reset(boolean b){

        //番茄
        if(b)
        {
            piccatch=picright;
            picright=piccenter;
            piccenter=picLetf;
            picLetf=piccatch;
        }else{
            piccatch=picLetf;
            picLetf=piccenter;
            piccenter=picright;
            picright=piccatch;

        }
        left= BitmapFactory.decodeResource(getResources(),picLetf);
        right= BitmapFactory.decodeResource(getResources(),picright);
        center= BitmapFactory.decodeResource(getResources(),piccenter);
        width=left.getWidth();
        height=left.getHeight();
//        matrixLeft=new Matrix();
//        matrixRight=new Matrix();
//        matrixCenter=new Matrix();
        float y=(height-height*0.8f)/2;
        float x=((WIDTH-width)*0.8f)/2;
        matrixLeft.setValues(getfloats(0,y,1,0.8f));
        matrixRight.setValues(getfloats(WIDTH-width+x,y,1,0.8f));
        matrixCenter.setValues(getfloats((WIDTH-width)/2,0,1,1));
        invalidate();
    }

    public void animation(boolean b,int time){
       // Log.i("hh","番茄");
       if(b){
           ValueAnimator left=ValueAnimator.ofFloat(0,(WIDTH-width)/2);
           left.addUpdateListener(new A());
           left.setDuration(time);
           left.start();

           ValueAnimator right=ValueAnimator.ofFloat(WIDTH-width,0);
           right.addUpdateListener(new B());
           right.setDuration(time);
           right.start();

           ValueAnimator center=ValueAnimator.ofFloat((WIDTH-width)/2,WIDTH-width);
           center.addUpdateListener(new C());
           center.setDuration(time);
           center.addListener(new Animator.AnimatorListener() {
               @Override
               public void onAnimationStart(Animator animation) {

               }

               @Override
               public void onAnimationEnd(Animator animation) {
                   reset(false);
               }

               @Override
               public void onAnimationCancel(Animator animation) {

               }

               @Override
               public void onAnimationRepeat(Animator animation) {

               }
           });
           center.start();

       }else{
           //Log.i("hh","鸡蛋");

           ValueAnimator left=ValueAnimator.ofFloat(0,WIDTH-width);
           left.addUpdateListener(new A());
           left.setDuration(time);
           left.start();

           ValueAnimator right=ValueAnimator.ofFloat(WIDTH-width,(WIDTH-width)/2);
           right.addUpdateListener(new B());
           right.setDuration(time);
           right.start();

           ValueAnimator center=ValueAnimator.ofFloat((WIDTH-width)/2,0);
           center.addUpdateListener(new C());
           center.setDuration(time);
           center.addListener(new Animator.AnimatorListener() {
               @Override
               public void onAnimationStart(Animator animation) {

               }

               @Override
               public void onAnimationEnd(Animator animation) {
                   reset(false);
               }

               @Override
               public void onAnimationCancel(Animator animation) {

               }

               @Override
               public void onAnimationRepeat(Animator animation) {

               }
           });
           center.start();

//           set.play(left).with(right).with(center);
//
//           set.addListener(new Animator.AnimatorListener() {
//               @Override
//               public void onAnimationStart(Animator animation) {
//
//               }
//
//               @Override
//               public void onAnimationEnd(Animator animation) {
//                   reset(false);
//
//               }
//
//               @Override
//               public void onAnimationCancel(Animator animation) {
//
//               }
//
//               @Override
//               public void onAnimationRepeat(Animator animation) {
//
//               }
//           });


       }

    }

    class A implements  ValueAnimator.AnimatorUpdateListener{

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {

             matrixLeft.setValues(getfloats((Float) animation.getAnimatedValue(),0,1,1));

           //  Log.i("hh","鸡蛋炒番茄");
             invalidate();
        }
    }
    class B implements  ValueAnimator.AnimatorUpdateListener{

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {

            matrixRight.setValues(getfloats((Float) animation.getAnimatedValue(),0,1,1));

           // Log.i("hh","鸡蛋番茄汤");
            invalidate();
        }
    }
    class C implements  ValueAnimator.AnimatorUpdateListener{

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            matrixCenter.setValues(getfloats((Float) animation.getAnimatedValue(),0,1,1));
            //Log.i("hh","鸡蛋番茄饼");
            invalidate();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int)WIDTH,(int)height);
//        Log.i("hha","W----"+WIDTH);
//        Log.i("hha","w----"+height);

    }



}
