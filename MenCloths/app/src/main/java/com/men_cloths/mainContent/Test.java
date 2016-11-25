package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/10.
 */

public class Test extends Activity {
    public static  float WIDTH;
    ViewFillperForNormal viff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WIDTH=getWindowManager().getDefaultDisplay().getWidth();
        setContentView(R.layout.test);
        //viff= (ViewFillperForNormal) findViewById(R.id.viff);
//        Matrix matrix1=new Matrix();
//        matrix1.setValues(getfloats(200,0,1));
//        Matrix matrix2=new Matrix();
//        matrix1.setValues(getfloats(0,0,1));
//        Matrix matrix3=new Matrix();
//        matrix1.setValues(getfloats(100,0,1));
//        viff.setMatrix(matrix1,matrix2,matrix3);
    }
    public float[] getfloats(float x,float y,float s){
        float[] defaults={
                1 ,0, x,
                0 ,1, y,
                0 ,0, s
        };
        return defaults;
    }
}
