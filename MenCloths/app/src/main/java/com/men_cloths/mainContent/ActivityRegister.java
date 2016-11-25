package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/30.
 */
public class ActivityRegister extends Activity {
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register();
    }

    public void register(){
        register=(TextView)findViewById(R.id.textview_register_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityRegister.this,ActivityLoginSuccess.class);
                startActivity(intent);
            }
        });
    }
}
