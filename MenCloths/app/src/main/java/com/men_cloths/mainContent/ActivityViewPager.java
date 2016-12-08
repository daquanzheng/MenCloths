package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.men_cloths.R;
import com.men_cloths.adapter.MyPagerAdapter;
import com.men_cloths.model.CachetToFile;
import com.men_cloths.model.HasLogin;

import java.util.ArrayList;



/**
 * Created by Administrator on 2016/10/31.
 */
public class ActivityViewPager extends Activity{
    private ViewPager viewPager;
    private ArrayList<View>arrayList;
    private TextView tiyan;
    private static final int OFF=-1;
    private static final int ON=1;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HasLogin.checkLogin(this,handler);//检查是否登录，不管是否登录都会进行接下来的步骤
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Handler handler=new Handler(){
      public void handleMessage(Message message){
          switch (message.what){
              case OFF:
                  next();
                  break;
              case ON:
                  next();
                  break;
          }
      }
    };

    public void next(){
        if(!HasLogin.isfirst(this)){//检查是否第一次登录，如果是就不进行导航页展示，直接跳转到主页面
            Intent intent=new Intent(ActivityViewPager.this,HomeActivity.class);
            CachetToFile.clearImage(this);
            startActivity(intent);
            finish();
            return;
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
                            if (HasLogin.isfirst(ActivityViewPager.this)){
                                SharedPreferences sharedPreferences=getSharedPreferences("login_info", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putBoolean("isfirst",false);
                                editor.commit();
                            }
                            finish();
                        }
                    });
                }
            }
        });
    }
}
