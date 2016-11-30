package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/21.
 */

public class Interchangeable extends Activity{

    private ImageView back;
    private SwitchButton switchButton;//开启省流量模式
    private SwitchButton switchButton1;//开启定位服务
    private Boolean stat1=true,stat2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interchangeable);
        init();
        SharedPreferences preferences=getSharedPreferences("switch_set",MODE_PRIVATE);
        switchButton.setState(preferences.getBoolean("switch1",true));
        switchButton1.setState( preferences.getBoolean("switch2",false));

    }

    public void init(){
        switchButton= (SwitchButton) findViewById(R.id.button);
        switchButton.setOnSwitchButtonClickListene(new SwitchButton.OnSwitchButtonClickListene() {
            @Override
            public void OnSwitchButtonClick(boolean state) {
              //  Log.i("hhh","------------------------"+state+"");
              //  Toast.makeText(Interchangeable.this,state+"",Toast.LENGTH_SHORT).show();
                stat1=state;
            }
        });
        switchButton1= (SwitchButton) findViewById(R.id.button_);
        switchButton1.setOnSwitchButtonClickListene(new SwitchButton.OnSwitchButtonClickListene() {
            @Override
            public void OnSwitchButtonClick(boolean state) {
                stat2=state;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences=getSharedPreferences("switch_set",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("switch1",stat1);
        editor.putBoolean("switch2",stat2);
        editor.commit();

    }
}
