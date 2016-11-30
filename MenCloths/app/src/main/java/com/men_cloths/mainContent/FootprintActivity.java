package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.men_cloths.R;
import com.men_cloths.adapter.FootprintAdapter;
import com.men_cloths.model.Footprint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class FootprintActivity extends Activity{
    ImageView back;
    ListView listView;
    List<Footprint> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_footprint);
        listView = (ListView) findViewById(R.id.listview_footprint);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(onClickListener);

        FootprintAdapter footprintAdapter = new FootprintAdapter(FootprintActivity.this,getLists());
        listView.setAdapter(footprintAdapter);
    }
    public List<Footprint> getLists(){
        for(int i=0;i<3;i++){
            Footprint footprint = new Footprint();


            lists.add(footprint);
        }
        return lists;
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
