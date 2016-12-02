package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ActivityLoginSuccess extends Activity {
    TextView loginNow;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        loginNow();
    }

    public void loginNow(){
        loginNow=(TextView) findViewById(R.id.textview_login_now);
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityLoginSuccess.this,ActivityLogin.class);
                startActivity(intent);
            }
        });
    }
}
