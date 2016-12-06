package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/30.
 */

public class BindSucessfuly extends Activity {

    ImageView imageView;
    Intent intent;
    String tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_successfully);
        imageView= (ImageView) findViewById(R.id.back);
        intent=getIntent();
        tel=intent.getStringExtra("tel");
        //Log.i("tel",tel);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents=new Intent(BindSucessfuly.this,Account.class);
                SharedPreferences sharedPreferences=getSharedPreferences("hello",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("tel",tel);
                editor.commit();
                startActivity(intents);

            }
        });
    }

}
