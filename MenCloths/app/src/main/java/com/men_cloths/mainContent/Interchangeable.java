package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/21.
 */

public class Interchangeable extends Activity{

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interchangeable);
        SwitchButton switchButton= (SwitchButton) findViewById(R.id.button);
        switchButton.setOnSwitchButtonClickListene(new SwitchButton.OnSwitchButtonClickListene() {
            @Override
            public void OnSwitchButtonClick(boolean state) {

            }
        });
        SwitchButton switchButton1= (SwitchButton) findViewById(R.id.button_);
        switchButton1.setOnSwitchButtonClickListene(new SwitchButton.OnSwitchButtonClickListene() {
            @Override
            public void OnSwitchButtonClick(boolean state) {

            }
        });

        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
