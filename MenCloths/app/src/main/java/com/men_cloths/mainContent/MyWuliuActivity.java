package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MyWuliuActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_wuliu);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
