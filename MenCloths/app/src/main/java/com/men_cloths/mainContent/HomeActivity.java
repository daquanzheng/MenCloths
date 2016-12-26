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
import com.men_cloths.model.ActivityManager;
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
    HomeFragment homeFragment;
    MallFragment mallFragment;
    CollocateFragment collocateFragment;
    ClassifyFragment classifyFragment;
    MineLoginFragment mineLoginFragment;
    MineFragment mineFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_home_relativelayout);
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
        ActivityManager.getActivityManager().add(this);
    }
    public void initial(){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if (myButtonClick[0]) {
            homeMyButton.setImgResource(R.mipmap.home);
            homeMyButton.setText("");
            showFragment(fragmentTransaction,0);
        }else {
            homeMyButton.setImgResource(0);
            homeMyButton.setText("首页");
            homeMyButton.setTextSize(20);
            homeMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if (myButtonClick[1]) {
            mallMyButton.setImgResource(R.mipmap.mall);
            mallMyButton.setText("");
            showFragment(fragmentTransaction,1);
        }else {
            mallMyButton.setImgResource(0);
            mallMyButton.setText("商城");
            mallMyButton.setTextSize(20);
            mallMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if(myButtonClick[2]){
            collocateMyButton.setText("");
            collocateMyButton.setImgResource(R.mipmap.collocate);
            hideFragment(fragmentTransaction);
            showFragment(fragmentTransaction,2);
        } else {
            collocateMyButton.setImgResource(0);
            collocateMyButton.setText("搭配");
            collocateMyButton.setTextSize(20);
            collocateMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if(myButtonClick[3]){
            classifyMyButton.setImgResource(R.mipmap.classify);
            classifyMyButton.setText("");
            showFragment(fragmentTransaction,3);
        }else {
            classifyMyButton.setImgResource(0);
            classifyMyButton.setText("分类");
            classifyMyButton.setTextSize(20);
            classifyMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
        if(myButtonClick[4]){
            mineMyButton.setText("");
            mineMyButton.setImgResource(R.mipmap.mine);
            hideFragment(fragmentTransaction);
            showFragment(fragmentTransaction,4);
        }else {
            mineMyButton.setImgResource(0);
            mineMyButton.setText("我的");
            mineMyButton.setTextSize(20);
            mineMyButton.setTextColor(getResources().getColor(R.color.green_bg));
        }
    }
    public void hideFragment(FragmentTransaction fragmentTransaction){
        if(homeFragment!=null) {
            fragmentTransaction.hide(homeFragment);
        }
        if(mallFragment!=null) {
            fragmentTransaction.hide(mallFragment);
        }
        if(collocateFragment!=null) {
            fragmentTransaction.hide(collocateFragment);
        }
        if(classifyFragment!=null) {
            fragmentTransaction.hide(classifyFragment);
        }
        if(mineFragment!=null) {
            fragmentTransaction.hide(mineFragment);
        }
        if(mineLoginFragment!=null) {
            fragmentTransaction.hide(mineLoginFragment);
        }
    }
    public void showFragment(FragmentTransaction fragmentTransaction,int id){
        hideFragment(fragmentTransaction);
        switch (id){
            case 0:
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.root_relativeLayout,homeFragment);
                }else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                if(mallFragment==null){
                    mallFragment=new MallFragment();
                    fragmentTransaction.add(R.id.root_relativeLayout,mallFragment);
                }else {
                    fragmentTransaction.show(mallFragment);
                }
                break;
            case 2:
                if(collocateFragment==null){
                    collocateFragment=new CollocateFragment();
                    fragmentTransaction.add(R.id.root_relativeLayout,collocateFragment);
                }else {
                    fragmentTransaction.show(collocateFragment);
                }
                break;
            case 3:
                if(classifyFragment==null){
                    classifyFragment=new ClassifyFragment();
                    fragmentTransaction.add(R.id.root_relativeLayout,classifyFragment);
                }else {
                    fragmentTransaction.show(classifyFragment);
                }
                break;
            case 4:
                if(!HasLogin.hasLogin(HomeActivity.this)) {
                    if(mineFragment==null){
                        mineFragment=new MineFragment();
                        fragmentTransaction.add(R.id.root_relativeLayout,mineFragment);
                    }else {
                        fragmentTransaction.show(mineFragment);
                    }
                }
                else{
                    if(mineLoginFragment==null){
                        mineLoginFragment=new MineLoginFragment();
                        fragmentTransaction.add(R.id.root_relativeLayout,mineLoginFragment);
                    }else {
                        fragmentTransaction.show(mineLoginFragment);
                    }
                }
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
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
