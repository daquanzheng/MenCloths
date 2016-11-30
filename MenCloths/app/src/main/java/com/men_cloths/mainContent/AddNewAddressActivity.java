package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/28.
 */
public class AddNewAddressActivity extends Activity{
    ImageView back;
    TextView saveNewAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_add_newaddress);
        back = (ImageView) findViewById(R.id.back);
        saveNewAddress = (TextView) findViewById(R.id.save_new_address);

        back.setOnClickListener(onClickListener);
        saveNewAddress.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.save_new_address:
//                    setResult();
                    finish();
                    break;
            }
        }
    };
}
