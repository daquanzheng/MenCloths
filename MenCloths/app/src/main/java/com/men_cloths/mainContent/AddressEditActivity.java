package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/29.
 */
public class AddressEditActivity extends Activity{
    ImageView back;
    TextView saveAddress;
    EditText editName,editPhone,editAddressName,editStreet;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_address_edit);
        //绑定控件
        back = (ImageView) findViewById(R.id.back);
        saveAddress = (TextView) findViewById(R.id.save_address);
        editName = (EditText) findViewById(R.id.edit_name);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editAddressName = (EditText) findViewById(R.id.edit_address_name);
        editStreet = (EditText) findViewById(R.id.edit_street);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        editName.setText(bundle.getString("name"));
//        editPhone.setText(bundle.getString("phone"));

//                intent.putExtra("name",editName.getText());
//                intent.putExtra("phone",editPhone.getText());
//                setResult(110,intent);
                finish();
            }
        });

    }
}
