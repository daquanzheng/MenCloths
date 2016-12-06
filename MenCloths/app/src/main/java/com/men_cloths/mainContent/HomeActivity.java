package com.men_cloths.mainContent;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.men_cloths.FragmentPackage.ClassifyFragment;
import com.men_cloths.FragmentPackage.CollocateFragment;
import com.men_cloths.FragmentPackage.HomeFragment;
import com.men_cloths.FragmentPackage.MallFragment;
import com.men_cloths.FragmentPackage.MineFragment;
import com.men_cloths.FragmentPackage.MineLoginFragment;
import com.men_cloths.R;
import com.men_cloths.model.HasLogin;
import com.men_cloths.model.MyButton;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HomeActivity extends FragmentActivity{
    MyButton homeMyButton;
    MyButton mallMyButton;
    MyButton collocateMyButton;
    MyButton classifyMyButton;
    MyButton mineMyButton;
      public static Boolean[] myButtonClick={true,false,false,false,false,};
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_home_relativelayout);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        fragmentTransaction.add(R.id.root_relativeLayout,homeFragment);
        fragmentTransaction.commit();
        homeMyButton= (MyButton) findViewById(R.id.home_button);
        mallMyButton= (MyButton) findViewById(R.id.mall_button);
        collocateMyButton= (MyButton) findViewById(R.id.collocate_button);
        classifyMyButton= (MyButton) findViewById(R.id.classify_button);
        mineMyButton= (MyButton) findViewById(R.id.mine_button);
        homeMyButton.setOnClickListener(onClickListener);
        mallMyButton.setOnClickListener(onClickListener);
        collocateMyButton.setOnClickListener(onClickListener);
        classifyMyButton.setOnClickListener(onClickListener);
        mineMyButton.setOnClickListener(onClickListener);
        initial();
    }
    public void initial(){
        if (myButtonClick[0]) {
            homeMyButton.setImgResource(R.mipmap.home);
            homeMyButton.setText("");
            fragmentTransaction=fragmentManager.beginTransaction();
            HomeFragment homeFragment=new HomeFragment();
            fragmentTransaction.replace(R.id.root_relativeLayout,homeFragment);
            fragmentTransaction.commit();
        }else {
            homeMyButton.setImgResource(0);
            homeMyButton.setText("首页");
            homeMyButton.setTextSize(20);
            homeMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if (myButtonClick[1]) {
            mallMyButton.setImgResource(R.mipmap.mall);
            mallMyButton.setText("");
            fragmentTransaction=fragmentManager.beginTransaction();
            MallFragment mallFragment=new MallFragment();
            fragmentTransaction.replace(R.id.root_relativeLayout,mallFragment);
            fragmentTransaction.commit();
        }else {
            mallMyButton.setImgResource(0);
            mallMyButton.setText("商城");
            mallMyButton.setTextSize(20);
            mallMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if(myButtonClick[2]){
            collocateMyButton.setText("");
            collocateMyButton.setImgResource(R.mipmap.collocate);
            fragmentTransaction=fragmentManager.beginTransaction();
            CollocateFragment collocateFragment=new CollocateFragment();
            fragmentTransaction.replace(R.id.root_relativeLayout,collocateFragment);
            fragmentTransaction.commit();
        } else {
            collocateMyButton.setImgResource(0);
            collocateMyButton.setText("搭配");
            collocateMyButton.setTextSize(20);
            collocateMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if(myButtonClick[3]){
            classifyMyButton.setImgResource(R.mipmap.classify);
            classifyMyButton.setText("");
            fragmentTransaction=fragmentManager.beginTransaction();
            ClassifyFragment classifyFragment=new ClassifyFragment();
            fragmentTransaction.replace(R.id.root_relativeLayout,classifyFragment);
            fragmentTransaction.commit();
        }else {
            classifyMyButton.setImgResource(0);
            classifyMyButton.setText("分类");
            classifyMyButton.setTextSize(20);
            classifyMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if(myButtonClick[4]){
            mineMyButton.setText("");
            mineMyButton.setImgResource(R.mipmap.mine);
            fragmentTransaction=fragmentManager.beginTransaction();
            MineFragment mineFragment=new MineFragment();
            MineLoginFragment mineLoginFragment=new MineLoginFragment();
            if(!HasLogin.hasLogin(HomeActivity.this)) {
                fragmentTransaction.replace(R.id.root_relativeLayout, mineFragment);
            }
            else{
                fragmentTransaction.replace(R.id.root_relativeLayout, mineLoginFragment);
            }
            fragmentTransaction.commit();
        }else {
            mineMyButton.setImgResource(0);
            mineMyButton.setText("我的");
            mineMyButton.setTextSize(20);
            mineMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.home_button:
                    for(int i=0;i<myButtonClick.length;i++){
                        if(i==0){
                            myButtonClick[i]=true;
                        }else {
                            myButtonClick[i]=false;
                        }
                    }
                    initial();
                    break;
                case R.id.mall_button:
                    for(int i=0;i<myButtonClick.length;i++){
                        if(i==1){
                            myButtonClick[i]=true;
                        }else {
                            myButtonClick[i]=false;
                        }
                    }
                    initial();
                    break;
                case R.id.collocate_button:
                    for(int i=0;i<myButtonClick.length;i++){
                        if(i==2){
                            myButtonClick[i]=true;
                        }else {
                            myButtonClick[i]=false;
                        }
                    }
                    initial();
                    break;
                case R.id.classify_button:
                    for(int i=0;i<myButtonClick.length;i++){
                        if(i==3){
                            myButtonClick[i]=true;
                        }else {
                            myButtonClick[i]=false;
                        }
                    }
                    initial();
                    break;
                case R.id.mine_button:
                    for(int i=0;i<myButtonClick.length;i++){
                        if(i==4){
                            myButtonClick[i]=true;
                        }else {
                            myButtonClick[i]=false;
                        }
                    }
                    initial();
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        initial();
        super.onRestart();
    }
}
