package com.men_cloths.mainContent;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.men_cloths.R;
import com.men_cloths.FragmentPackage.MyFrament;
import com.men_cloths.FragmentPackage.MyFramentToTwo;

/**
 * Created by Administrator on 2016/10/30.
 */

public class BindtoTel extends Activity{

    private  LinearLayout linearLayout;
    private  FragmentManager manager;
    private  MyFrament myFrament;
    private  MyFramentToTwo myFrament2;
    public static String tel="";
    private ImageView back;
    int flags=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_tel_phong);
         linearLayout= (LinearLayout) findViewById(R.id.linear_layout);
         myFrament=new MyFrament();

         manager=getFragmentManager();
         FragmentTransaction transaction=manager.beginTransaction();
         transaction.add(R.id.linear_layout,myFrament);
         transaction.commit();
         myFrament2=new MyFramentToTwo();
         myFrament.setOnMyFramentclikListener(new MyFrament.listener() {
            @Override
            public void onclik() {
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.linear_layout,myFrament2);
                transaction.commit();
                flags=1;
            }
        });
        myFrament.addMyTextChangedListener(new MyFrament.MyTexychanged() {
            @Override
            public void afterTextChanged(Editable s) {
                tel=s.toString();
            }
        });
        if (myFrament2!=null)
        myFrament2.setOnMyFramentclikListener(new MyFramentToTwo.listener() {
            @Override
            public void onclik() {
                Intent intent=new Intent(BindtoTel.this,BindSucessfuly.class);
                intent.putExtra("tel",tel);
                startActivity(intent);
            }
        });
        back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(flags==1){
                   FragmentTransaction transaction=manager.beginTransaction();
                   transaction.replace(R.id.linear_layout,myFrament);
                   transaction.commit();
                   flags=0;
               }else{
                   Intent intent=new Intent(BindtoTel.this,Account.class);
                   startActivity(intent);
               }
            }
        });
    }
}
