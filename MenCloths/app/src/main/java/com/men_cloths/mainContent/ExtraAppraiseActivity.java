package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ExtraAppraiseActivity extends Activity{
    private   TextView cancel,extraAppraiseOk;
    private  EditText extraAppraise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_extra_pingjia);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cancel = (TextView) findViewById(R.id.cancel_appraise);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        extraAppraise = (EditText) findViewById(R.id.extra_appraise_input);
        extraAppraiseOk = (TextView) findViewById(R.id.extra_appraise_ok);
        extraAppraise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
//                extraAppraise.setText(str);
                if(str.length()>=20){
                    extraAppraiseOk.setEnabled(true);
                    if(extraAppraiseOk.isEnabled()){
                        extraAppraiseOk.setTextColor(getResources().getColor(R.color.black_model));
                    }else
                        extraAppraiseOk.setTextColor(getResources().getColor(R.color.enbled_false));
                }else
                    extraAppraiseOk.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        extraAppraiseOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendMessage(extraAppraise.getText());
                Toast.makeText(ExtraAppraiseActivity.this,"评价成功！",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
