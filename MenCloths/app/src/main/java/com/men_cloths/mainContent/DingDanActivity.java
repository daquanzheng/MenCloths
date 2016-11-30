package com.men_cloths.mainContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.men_cloths.FragmentPackage.AllOrderFragement;
import com.men_cloths.FragmentPackage.WaitAppraiseFragment;
import com.men_cloths.FragmentPackage.WaitGetFragment;
import com.men_cloths.FragmentPackage.WaitPayFragment;
import com.men_cloths.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/25.
 */
public class DingDanActivity extends FragmentActivity{
    ImageView back;
    RadioGroup radioGroup;
    RadioButton allOrder,waitPay,waitGet,waitAppraise;
    View radio1,radio2,radio3,radio4;
    ArrayList<Fragment> fragments;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_dingdan);
        back = (ImageView) findViewById(R.id.back);
        radio1 = findViewById(R.id.radiobutton1);
        radio2 = findViewById(R.id.radiobutton2);
        radio3 = findViewById(R.id.radiobutton3);
        radio4 = findViewById(R.id.radiobutton4);
        radioGroup = (RadioGroup) findViewById(R.id.order_radiogroup);
        allOrder = (RadioButton) findViewById(R.id.all_order);
        waitPay = (RadioButton) findViewById(R.id.wait_pay);
        waitGet = (RadioButton) findViewById(R.id.wait_get);
        waitAppraise = (RadioButton) findViewById(R.id.wait_appraise);
        viewPager = (ViewPager) findViewById(R.id.order_viewpager);

        fragments = new ArrayList<>();
        AllOrderFragement allOrderFragement = new AllOrderFragement();
        WaitPayFragment waitPayFragment = new WaitPayFragment();
        WaitGetFragment waitGetFragment = new WaitGetFragment();
        WaitAppraiseFragment waitAppraiseFragment = new WaitAppraiseFragment();
        fragments.add(allOrderFragement);
        fragments.add(waitPayFragment);
        fragments.add(waitGetFragment);
        fragments.add(waitAppraiseFragment);

        MyViewPagerFragment myViewPagerFragment = new MyViewPagerFragment(getSupportFragmentManager());
        viewPager.setAdapter(myViewPagerFragment);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        allOrder.setChecked(true);
                        break;
                    case 1:
                        waitPay.setChecked(true);
                        break;
                    case 2:
                        waitGet.setChecked(true);
                        break;
                    case 3:
                        waitAppraise.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                /**
                 * 页面滚动的回调
                 * ViewPager.SCROLL_STATE_IDLE   0  此时viewPager处于闲置状态
                 * ViewPager.SCROLL_STATE_DRAGGING  1  此时viewPager处于拖动状态
                 * ViewPager.SCROLL_STATE_SETTLING  2  此时viewPager处于切换状态
                 */
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.all_order:
                        viewPager.setCurrentItem(0);
                        radio1.setVisibility(View.VISIBLE);
                        radio2.setVisibility(View.INVISIBLE);
                        radio3.setVisibility(View.INVISIBLE);
                        radio4.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.wait_pay:
                        viewPager.setCurrentItem(1);
                        radio1.setVisibility(View.INVISIBLE);
                        radio2.setVisibility(View.VISIBLE);
                        radio3.setVisibility(View.INVISIBLE);
                        radio4.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.wait_get:
                        viewPager.setCurrentItem(2);
                        radio1.setVisibility(View.INVISIBLE);
                        radio2.setVisibility(View.INVISIBLE);
                        radio3.setVisibility(View.VISIBLE);
                        radio4.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.wait_appraise:
                        viewPager.setCurrentItem(3);
                        radio1.setVisibility(View.INVISIBLE);
                        radio2.setVisibility(View.INVISIBLE);
                        radio3.setVisibility(View.INVISIBLE);
                        radio4.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public class MyViewPagerFragment extends FragmentStatePagerAdapter {

        public MyViewPagerFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
