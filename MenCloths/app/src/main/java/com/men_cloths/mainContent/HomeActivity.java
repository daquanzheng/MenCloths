package com.men_cloths.mainContent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.men_cloths.FragmentPackage.HotSellerFragment;
import com.men_cloths.FragmentPackage.NewProductFragment;
import com.men_cloths.FragmentPackage.ShowFragment;
import com.men_cloths.FragmentPackage.TrendFragment;
import com.men_cloths.R;
import com.men_cloths.adapter.HomeFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class HomeActivity extends FragmentActivity{
    ViewPager viewPager;
    List<Fragment> fragmentList;
    NewProductFragment newProductFragment=new NewProductFragment();
    HotSellerFragment hotSellerFragment=new HotSellerFragment();
    TrendFragment trendFragment=new TrendFragment();
    ShowFragment showFragment=new ShowFragment();
    FragmentManager fragmentManager=getSupportFragmentManager();
    RadioGroup topRadioGroup;
    RadioButton newProductBtn;
    RadioButton hotSellerBtn;
    RadioButton trendBtn;
    RadioButton showBtn;
    RadioGroup homeRadioGroup;
    RadioButton homeBtn;
    RadioButton mallBtn;
    RadioButton collocateBtn;
    RadioButton classifyBtn;
    RadioButton mineBtn;
    boolean style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mencloths_home);
        viewPager= (ViewPager) findViewById(R.id.home_viewPager);
        homeRadioGroup= (RadioGroup) findViewById(R.id.home_radioGroup);
        homeRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        homeBtn= (RadioButton) findViewById(R.id.home);
        mallBtn=(RadioButton) findViewById(R.id.mall);
        collocateBtn=(RadioButton) findViewById(R.id.collocate);
        classifyBtn=(RadioButton) findViewById(R.id.classify);
        mineBtn=(RadioButton) findViewById(R.id.mine);
         topRadioGroup= (RadioGroup) findViewById(R.id.home_top_menubar);
         newProductBtn= (RadioButton) findViewById(R.id.home_new_product);
        hotSellerBtn= (RadioButton) findViewById(R.id.home_hot_seller);
         trendBtn= (RadioButton) findViewById(R.id.home_trend);
         showBtn= (RadioButton) findViewById(R.id.home_showing);
        topRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        HomeFragmentAdapter homeFragmentAdapter=new HomeFragmentAdapter(fragmentManager,getFragmentList());
        viewPager.setAdapter(homeFragmentAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                        switch (position){
                            case 0:
                                newProductBtn.setChecked(true);
                                break;
                            case 1:
                                hotSellerBtn.setChecked(true);
                                break;
                            case 2:
                                trendBtn.setChecked(true);
                                break;
                            case 3:
                                showBtn.setChecked(true);
                                break;
                        }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public List<Fragment> getFragmentList(){
        fragmentList=new ArrayList<>();
        fragmentList.add(newProductFragment);
        fragmentList.add(hotSellerFragment);
        fragmentList.add(trendFragment);
        fragmentList.add(showFragment);
        return fragmentList;
    }
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.home:
                    homeBtn.setText(null);
                    homeBtn.setButtonDrawable(R.mipmap.home);
                    mallBtn.setText("商城");
                    mallBtn.setButtonDrawable(null);
                    collocateBtn.setText("搭配");
                    collocateBtn.setButtonDrawable(null);
                    classifyBtn.setText("分类");
                    classifyBtn.setButtonDrawable(null);
                    mineBtn.setText("我的");
                    mineBtn.setButtonDrawable(null);
                    break;
                case R.id.mall:
                    homeBtn.setText("首页");
                    homeBtn.setButtonDrawable(null);
                    mallBtn.setText(null);
                    mallBtn.setButtonDrawable(R.mipmap.mall);
                    collocateBtn.setText("搭配");
                    collocateBtn.setButtonDrawable(null);
                    classifyBtn.setText("分类");
                    classifyBtn.setButtonDrawable(null);
                    mineBtn.setText("我的");
                    mineBtn.setButtonDrawable(null);
                    Intent intent=new Intent(HomeActivity.this,MallActivity.class);
                    startActivity(intent);
                    break;
                case R.id.collocate:
                    homeBtn.setText("首页");
                    homeBtn.setButtonDrawable(null);
                    mallBtn.setText("商城");
                    mallBtn.setButtonDrawable(null);
                    collocateBtn.setText(null);
                    collocateBtn.setButtonDrawable(R.mipmap.collocate);
                    classifyBtn.setText("分类");
                    classifyBtn.setButtonDrawable(null);
                    mineBtn.setText("我的");
                    mineBtn.setButtonDrawable(null);
                    Intent intent1=new Intent(HomeActivity.this,CollocateActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.classify:
                    homeBtn.setText("首页");
                    homeBtn.setButtonDrawable(null);
                    mallBtn.setText("商城");
                    mallBtn.setButtonDrawable(null);
                    collocateBtn.setText("搭配");
                    collocateBtn.setButtonDrawable(null);
                    classifyBtn.setText(null);
                    classifyBtn.setButtonDrawable(R.mipmap.classify);
                    mineBtn.setText("我的");
                    mineBtn.setButtonDrawable(null);
                    Intent intent2=new Intent(HomeActivity.this,ClassifyActvity.class);
                    startActivity(intent2);
                    break;
                case R.id.mine:
                    homeBtn.setText("首页");
                    homeBtn.setButtonDrawable(null);
                    mallBtn.setText("商城");
                    mallBtn.setButtonDrawable(null);
                    collocateBtn.setText("搭配");
                    collocateBtn.setButtonDrawable(null);
                    classifyBtn.setText("分类");
                    classifyBtn.setButtonDrawable(null);
                    mineBtn.setText(null);
                    mineBtn.setButtonDrawable(R.mipmap.mine);
                    Intent intent3=new Intent(HomeActivity.this,MyMainpageInActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.home_new_product:
                    viewPager.setCurrentItem(0);
                    newProductBtn.setTextColor(getResources().getColor(R.color.green_bg));
                    hotSellerBtn.setTextColor(getResources().getColor(R.color.black_model));
                    trendBtn.setTextColor(getResources().getColor(R.color.black_model));
                    showBtn.setTextColor(getResources().getColor(R.color.black_model));
                    break;
                case R.id.home_hot_seller:
                    viewPager.setCurrentItem(1);
                    newProductBtn.setTextColor(getResources().getColor(R.color.black_model));
                    hotSellerBtn.setTextColor(getResources().getColor(R.color.green_bg));
                    trendBtn.setTextColor(getResources().getColor(R.color.black_model));
                    showBtn.setTextColor(getResources().getColor(R.color.black_model));
                    break;
                case R.id.home_trend:
                    viewPager.setCurrentItem(2);
                    newProductBtn.setTextColor(getResources().getColor(R.color.black_model));
                    hotSellerBtn.setTextColor(getResources().getColor(R.color.black_model));
                    trendBtn.setTextColor(getResources().getColor(R.color.green_bg));
                    showBtn.setTextColor(getResources().getColor(R.color.black_model));
                    break;
                case R.id.home_showing:
                    viewPager.setCurrentItem(3);
                    newProductBtn.setTextColor(getResources().getColor(R.color.black_model));
                    hotSellerBtn.setTextColor(getResources().getColor(R.color.black_model));
                    trendBtn.setTextColor(getResources().getColor(R.color.black_model));
                    showBtn.setTextColor(getResources().getColor(R.color.green_bg));
                    break;
            }
        }
    };



}
