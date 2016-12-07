package com.men_cloths.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.men_cloths.R;
import com.men_cloths.adapter.GettingAddressAdapter;
import com.men_cloths.model.GettingAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyAddressActivity extends Activity{
<<<<<<< HEAD
<<<<<<< HEAD
    ImageView back;
    Button addNewAddress;
    ListView listView;
    List<GettingAddress> lists = new ArrayList<>();
    GettingAddressAdapter gettingAddressAdapter;
=======
=======
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
    private  ImageView back;
    private   Button addNewAddress;
    private  ListView listView;
    private   List<GettingAddress> lists = new ArrayList<>();
    private  GettingAddressAdapter gettingAddressAdapter;
    private int position;
<<<<<<< HEAD
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
=======
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_address);
        addNewAddress = (Button)findViewById(R.id.add_newaddress);
        back = (ImageView) findViewById(R.id.back);
        listView = (ListView) findViewById(R.id.my_address);
        gettingAddressAdapter = new GettingAddressAdapter(MyAddressActivity.this,getLists());
        //自定义的接口
        gettingAddressAdapter.setOnEidtOnClickListener(new GettingAddressAdapter.EditOnClickListenr() {
            @Override
            public int onClick(int itemid, String name, String phone) {
                Intent intent = new Intent(MyAddressActivity.this,AddressEditActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                startActivityForResult(intent,110);
                position = itemid;
                return position;
            }
        });
        listView.setAdapter(gettingAddressAdapter);

        back.setOnClickListener(onClickListener);
        addNewAddress.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.add_newaddress:
                    Intent intent = new Intent(MyAddressActivity.this,AddNewAddressActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
<<<<<<< HEAD
<<<<<<< HEAD

    public void getAddress(){
        String string = "http://192.168.7.9/index.php/Home/Address/getnewaddress";
        try {
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                StringBuilder stringBuilder = new StringBuilder();//单线程用StringBuffer速度快，多线程用StringBuffer保证安全
                String s;
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
               // Log.i("获取到的数据",""+stringBuilder);
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                    GettingAddress gettingAddress = new GettingAddress();
                    gettingAddress.setName(jsonObject1.optString("name"));
                    gettingAddress.setPhone(jsonObject1.optString("phone"));
                    gettingAddress.setAddressDetails(jsonObject1.optString("address"));
                    lists.add(gettingAddress);
                }
                handler.sendEmptyMessage(0);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

=======
    //跳转回来的回调
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
=======
    //跳转回来的回调
>>>>>>> 35b1354cf7320dbbe06ac56e4a2c3f4d04b00733
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) { //包含的数据，用bundle接收
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==110){
//            Bundle bundle = data.getExtras();
//            getLists().get(position).setName(bundle.getString("name"));
//            getLists().get(position).setPhone(bundle.getString("phone"));
//            getLists().get(position).setAddressDetails(bundle.getString("address")+bundle.getString("street"));
//            gettingAddressAdapter.notifyDataSetChanged();
//        }
    }

    public List<GettingAddress> getLists(){
        for(int i=0;i<3;i++) {
            GettingAddress gettingAddress = new GettingAddress();
            lists.add(gettingAddress);
        }
    return lists;
    }
}
