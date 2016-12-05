package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.men_cloths.adapter.MyPagerAdapter;
import com.men_cloths.R;
import com.men_cloths.model.HasLogin;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/10/31.
 */
public class ActivityViewPager extends Activity{
    ViewPager viewPager;
    ArrayList<View>arrayList;
    TextView tiyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!HasLogin.isfirst(this)){
            Intent intent=new Intent(ActivityViewPager.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activityviewpager);
        viewPager=(ViewPager)findViewById(R.id.viewpager);

        arrayList=new ArrayList<View>();
        View view1= getLayoutInflater().inflate(R.layout.page1,null);
        View view2= getLayoutInflater().inflate(R.layout.page2,null);
        View view3= getLayoutInflater().inflate(R.layout.page3,null);
        View view4= getLayoutInflater().inflate(R.layout.page4,null);

        arrayList.add(view1);
        arrayList.add(view2);
        arrayList.add(view3);
        arrayList.add(view4);

        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(arrayList);
        viewPager.setAdapter(myPagerAdapter);
        //设置viewPager的监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(viewPager.getCurrentItem()==3){
                    tiyan=(TextView)findViewById(R.id.textview_experience);
                    tiyan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(ActivityViewPager.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });







    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
