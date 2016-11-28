package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/28.
 */
public class AddNewAddressActivity extends Activity{
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_add_newaddress);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
}
