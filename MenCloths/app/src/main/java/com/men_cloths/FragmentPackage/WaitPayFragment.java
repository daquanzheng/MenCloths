package com.men_cloths.FragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.WaitpayAdapter;
import com.men_cloths.model.Waitpay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class WaitPayFragment extends Fragment {
    ListView listView;
    List<Waitpay> lists = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan_daifukuan,null);
        listView = (ListView) view.findViewById(R.id.wait_all_listview);
        WaitpayAdapter waitpayAdapter = new WaitpayAdapter(getActivity(),getLists());
        listView.setAdapter(waitpayAdapter);
        return view;
    }
    public List<Waitpay> getLists(){
        for(int i=0;i<5;i++){
            Waitpay waitpay = new Waitpay();
            lists.add(waitpay);
        }
        return lists;
    }
}
