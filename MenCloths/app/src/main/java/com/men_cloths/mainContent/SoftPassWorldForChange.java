package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/30.
 */

public class SoftPassWorldForChange extends Activity {

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_passwd_changed);
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SoftPassWorldForChange.this,SoftPassword.class);
                startActivity(intent);
            }
        });

    }
}
