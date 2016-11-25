package com.men_cloths.FragmentPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.men_cloths.R;

/**
 * Created by Administrator on 2016/10/30.
 */

public class MyFrament extends Fragment{

    private Button button;


    private EditText editText;
    private listener lis;
    public interface  listener{
        public void onclik();
    }
    public void setOnMyFramentclikListener(listener listener){
        this.lis=listener;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    public interface  MyTexychanged{
        public void afterTextChanged(Editable s);
    }
    private  MyTexychanged myTexychanged;
    public void addMyTextChangedListener(MyTexychanged listener){
        this.myTexychanged=listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.binf_tel_phong_frament_inputnum,null);
        button= (Button) view.findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lis.onclik();
            }
        });
        editText= (EditText) view.findViewById(R.id.tel_number_input);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myTexychanged.afterTextChanged(s);

            }
        });
        return view;


    }


}
