package com.men_cloths.FragmentPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.men_cloths.R;
import com.men_cloths.adapter.HomeFragmentAdapter;
import com.men_cloths.mainContent.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HomeFragment extends Fragment{
    ViewPager viewPager;
    List<Fragment> fragmentList;
    NewProductFragment newProductFragment=new NewProductFragment();
    HotSellerFragment hotSellerFragment=new HotSellerFragment();
    TrendFragment trendFragment=new TrendFragment();
    ShowFragment showFragment=new ShowFragment();
    RadioGroup topRadioGroup;
    RadioButton newProductBtn;
    RadioButton hotSellerBtn;
    RadioButton trendBtn;
    RadioButton showBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mencloths_home,null);
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        viewPager= (ViewPager) view.findViewById(R.id.home_viewPager);
        topRadioGroup= (RadioGroup) view.findViewById(R.id.home_top_menubar);
        newProductBtn= (RadioButton) view.findViewById(R.id.home_new_product);
        hotSellerBtn= (RadioButton) view.findViewById(R.id.home_hot_seller);
        trendBtn= (RadioButton) view.findViewById(R.id.home_trend);
        showBtn= (RadioButton) view.findViewById(R.id.home_showing);
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
        return view;

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
