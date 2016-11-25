package com.men_cloths.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.CollocateAdapter;
import com.men_cloths.model.Collocate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class CollocateActivity extends Activity{
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mencloths_mall);
        listView= (ListView) findViewById(R.id.listview_mall);
        CollocateAdapter collocateAdapter=new CollocateAdapter(this,getData());
        listView.setAdapter(collocateAdapter);

    }


    public List<Collocate> getData(){
        List<Collocate> collocateList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Collocate collocate=new Collocate();
            if(i%2==0){
                collocate.setWholeImg1(R.mipmap.collocate_img01);
                collocate.setPartImg1(R.mipmap.collocate_img01_01);
                collocate.setPartImg1Title("牛仔夹克外套");
                collocate.setPartImg2(R.mipmap.collocate_img01_02);
                collocate.setPartImg2Title("白色衬衫");
                collocate.setPartImg3(R.mipmap.collocate_img01_03);
                collocate.setPartImg3Title("白色牛仔裤");
                collocate.setPartImg4(R.mipmap.collocate_img01_04);
                collocate.setPartImg4Title("深蓝色乐福鞋");
            }else {
                collocate.setWholeImg2(R.mipmap.collocate_img02);
                collocate.setPartImg1(R.mipmap.collocate_img02_01);
                collocate.setPartImg1Title("休闲呢子大衣");
                collocate.setPartImg2(R.mipmap.collocate_img02_02);
                collocate.setPartImg2Title("宽檐大檐帽子");
                collocate.setPartImg3(R.mipmap.collocate_img02_03);
                collocate.setPartImg3Title("灰色阔腿裤");
                collocate.setPartImg4(R.mipmap.collocate_img02_04);
                collocate.setPartImg4Title("黑色系带皮鞋");
            }
        collocateList.add(collocate);
        }
        return collocateList;
    }
}
