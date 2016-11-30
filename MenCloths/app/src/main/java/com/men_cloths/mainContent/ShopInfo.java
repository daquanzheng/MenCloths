package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/9.
 */

public class ShopInfo extends Activity {
    public static float width;
    public static  float WIDTH;
    private ViewFillperForNormal normal;
    private View v1,v2,v3;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        width=getWindowManager().getDefaultDisplay().getWidth();
        WIDTH=width;
        setContentView(R.layout.single_product_info);
        init();
        back.setOnClickListener(listener);


    }

    public void init(){
        normal= (ViewFillperForNormal) findViewById(R.id.normal);
        v1=findViewById(R.id.view1);
        v2=findViewById(R.id.view2);
        v3=findViewById(R.id.view3);
        normal.setChangedListener(new ViewFillperForNormal.CursorChanged(){
            @Override
            public void changedleft(int c) {
                switch (c){
                    case 1:
                        v1.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        v2.setVisibility(View.VISIBLE);
                        v1.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        v3.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v1.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void changedright(int c) {
                switch (c){
                    case 1:
                        v1.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        v2.setVisibility(View.VISIBLE);
                        v1.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        v3.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v1.setVisibility(View.INVISIBLE);
                        break;
                }

            }
        });

        back= (ImageView) findViewById(R.id.back);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
            }
        }
    };


}
