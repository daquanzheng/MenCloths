package com.men_cloths.mainContent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/20.
 */

public class SwitchButton extends View{
    private Paint mpaint,paint;
    private Rect drect,res;
    private Bitmap first,second,ro;



    Boolean state;
    private OnSwitchButtonClickListene listener;
    public  interface OnSwitchButtonClickListene{
        public void OnSwitchButtonClick(boolean state);
    }
    public void setOnSwitchButtonClickListene(OnSwitchButtonClickListene listener){
        this.listener=listener;
    }

    public SwitchButton(Context context) {
        super(context);
        init();
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        //obtainStyledAttributes(int[]) (in android.content.Context)
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.switchButton);
        state=typedArray.getBoolean(R.styleable.switchButton_state,true);
        if(state==true)
        {
            flag=75;
        }else{
            flag=25;
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==25)
                {
                    flag=75;
                    state=true;
                    invalidate();
                }else if(flag==75)
                {
                    flag=25;
                    state=false;
                    invalidate();
                }
                listener.OnSwitchButtonClick(state);
            }

        });

    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private float flag=25;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawBitmap(Bitmap, Rect, RectF, Paint) (in android.graphics.Canvas)

        //createBitmap(Bitmap, int, int, int, int) (in android.graphics.Bitmap)
        //setBackgroundColor(getResources().getColor(R.color.white));
       // paint=new Paint();


        //paint.setColor(Color.WHITE);
      if(flag==25)
      {
          //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

          canvas.drawBitmap(first,50,0,null);
          //canvas.drawBitmap(second,50,0,paint);
          canvas.drawBitmap(ro,flag,0,null);
         // paint.setXfermode(null);


      }else{
         // paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

          //canvas.drawBitmap(first,50,0,paint);
          canvas.drawBitmap(second,50,0,null);
          canvas.drawBitmap(ro,flag,0,null);
        //  canvas.drawRect(drect,null);
         // paint.setXfermode(null);

      }




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(first.getWidth()+ro.getWidth()/2,first.getHeight());
    }

    public void init(){
//        mpaint=new Paint();
//        mpaint.setAntiAlias(true);
//        mpaint.setAlpha(255);
//        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.STROKE);
//        mpaint.setStrokeWidth(20);
        first= BitmapFactory.decodeResource(getResources(),R.mipmap.fillet);
        second=BitmapFactory.decodeResource(getResources(),R.mipmap.fillet_green);
        ro=BitmapFactory.decodeResource(getResources(),R.mipmap.ro1);
        drect=new Rect(0,0,first.getWidth(),first.getHeight());
//        res=new Rect();
    }


}
