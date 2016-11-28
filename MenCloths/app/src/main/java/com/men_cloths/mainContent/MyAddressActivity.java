package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyAddressActivity extends Activity{
    ImageView back;
    Button addNewAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_address);
        addNewAddress = (Button)findViewById(R.id.add_newaddress);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(onClickListener);
        addNewAddress.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.add_newaddress:
                    Intent intent = new Intent(MyAddressActivity.this,AddNewAddressActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
