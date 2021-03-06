package com.men_cloths.FragmentPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/30.
 */

public class MyFramentToTwo extends Fragment {
    private Button button;
    public EditText editText;
    private listener lis;
    public TextView send;
    public interface  listener{
        public void onclik();
        public void send();
    }
    public void setOnMyFramentclikListener(listener listener){
        this.lis=listener;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bind_tel_phong_frament_yanzheng,null);
        button= (Button) view.findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lis.onclik();
            }
        });
         send= (TextView) view.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lis.send();
            }
        });
        editText= (EditText) view.findViewById(R.id.editText);
        return view;


    }
}
