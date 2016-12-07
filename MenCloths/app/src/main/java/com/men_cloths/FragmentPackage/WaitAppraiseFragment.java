package com.men_cloths.FragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.WaitappraiseAdapter;
import com.men_cloths.model.Waitappraise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitAppraiseFragment extends Fragment{
    private   ListView listView;
    private  List<Waitappraise> lists = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_daifukuan,null);
        listView = (ListView) view.findViewById(R.id.wait_all_listview);
        WaitappraiseAdapter waitappraiseAdapter = new WaitappraiseAdapter(getActivity(),getLists());
        listView.setAdapter(waitappraiseAdapter);
        return view;
    }

    public List<Waitappraise> getLists() {
        for(int i=0;i<5;i++){
            Waitappraise waitappraise = new Waitappraise();
            lists.add(waitappraise);
        }
        return lists;
    }
}
