package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/26.
 */

public class SoftPassword extends Activity{

    LinearLayout linearLayout;
    SwitchButton switchButton;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sofa_password);

        linearLayout= (LinearLayout) findViewById(R.id.linear_layout);
        switchButton= (SwitchButton) findViewById(R.id.switchButton);


       switchButton.setOnSwitchButtonClickListene(new SwitchButton.OnSwitchButtonClickListene() {
           @Override
           public void OnSwitchButtonClick(boolean state) {
               if(state)
               {
                   linearLayout.setVisibility(View.VISIBLE);
               }else{
                   linearLayout.setVisibility(View.GONE);
               }
           }
       });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SoftPassword.this,SoftPassWorldForChange.class);
                startActivity(intent);
            }
        });

        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SoftPassword.this,Account.class);
                startActivity(intent);
            }
        });
    }
}
