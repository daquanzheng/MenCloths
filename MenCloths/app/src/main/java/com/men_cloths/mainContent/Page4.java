package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class Page4 extends Activity{
    TextView tiyan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);
        tiyan=(TextView) findViewById(R.id.textview_experience);
        tiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Page4.this,ActivityLogin.class);
                startActivity(intent);
            }
        });

    }
}
