package com.men_cloths.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class HttpUtils {
    public String token;
    public String uid;
    
    //post閹绘劒姘orm-data缁鐎烽惃鍕殶閹圭晛ame=value
    public void postData(String httpUrl, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader in = null;
        String result = "";
        String end = "\r\n";//缂佹挻娼粭锔猴拷鎰床鐞涘被锟斤拷
        String twoHyphens = "--";//閸掑棝娈х粭锕�绱戞径鏉戠摟缁楋拷
        String boundary = "SJDASJODAODASSD";//
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();//閹垫挸绱戞潻鐐村复鐎电钖�
            connection.setDoOutput(true);//鐠佸墽鐤嗛崗浣筋啅鏉堟挸鍤�
            connection.setUseCaches(false);//鐠佸墽鐤嗘稉宥囩处鐎涳拷
            connection.setConnectTimeout(10000); // 鏉╃偞甯寸搾鍛娑擄拷10缁夛拷
            connection.setRequestMethod("POST");//鐠佸墽鐤嗙拠閿嬬湴閺傜懓绱�
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);// 鐠佸墽鐤嗙拠閿嬬湴閺佺増宓佺猾璇茬�烽獮鎯邦啎缂冪敚oundary闁劌鍨庨敍锟�
            connection.connect();//瀵拷閸氼垵绻涢幒锟�
            DataOutputStream ds = new DataOutputStream(
                    connection.getOutputStream());//閼惧嘲绶辨潏鎾冲毉濞翠胶娈戠�电钖�
            Set<Map.Entry<String, String>> paramEntrySet = map.entrySet();//鐏忓攲ap鏉烆剚宕查幋鎭爀t闂嗗棗鎮�
            Iterator paramIterator = paramEntrySet.iterator();//set闂嗗棗鎮庨惃鍕嚡娴狅絽娅�
            while (paramIterator.hasNext()) {//瀵邦亞骞嗛崣鏍у毉key閸滃瘉alue
                Map.Entry<String, String> entry = (Map.Entry<String, String>) paramIterator
                        .next();
                String key = entry.getKey();//閸欐牕绶眐ey閸婏拷
                String value = entry.getValue();//閸欐牕绶眝alue閻ㄥ嫬锟斤拷
                ds.writeBytes(twoHyphens + boundary + end);//--SJDASJODAODASSD
                ds.writeBytes("Content-Disposition: form-data; " + "name=\""
                        + key + "\"" + end + end + value);//Content-Disposition: form-data; name="key"
                ds.writeBytes(end);
                ds.writeBytes(twoHyphens + boundary + twoHyphens + end);//--SJDASJODAODASSD
                ds.flush();//閸掗攱鏌婇弫鐗堝祦
            }
            // 鐎规矮绠烞ufferedReader鏉堟挸鍙嗗ù浣规降鐠囪褰嘦RL閻ㄥ嫬鎼锋惔锟�
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            int statusCode = connection.getResponseCode();//閼惧嘲绶遍崫宥呯安閻樿埖锟戒胶鐖�
            if (statusCode == HttpURLConnection.HTTP_OK) {
                char[] buf = new char[1024];
                int len = -1;
                while ((len = in.read(buf, 0, buf.length)) != -1) {
                    stringBuilder.append(buf, 0, len);
                }
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(stringBuilder.toString());
//        Log.i("Post", "result===========>" + stringBuilder.toString());
//        try {
//            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
//            String message = jsonObject.optString("message", "");
//            token = jsonObject.optString("token", "");
//            uid = jsonObject.optString("uid", "");
//            Log.i("Post", "message=" + message);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
    
    
    public static String post(String url, Map<String, String> params, Map<String, File> files)
            throws IOException {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";


        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(10 * 1000); // 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);


        // 首先组拼文本类型的参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }


        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(sb.toString().getBytes());
        // 发送文件数据
        if (files != null)
            for (Map.Entry<String, File> file : files.entrySet()) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                        + file.getValue().getName() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());


                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }


                is.close();
                outStream.write(LINEND.getBytes());
            }


        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();
        // 得到响应码
        int res = conn.getResponseCode();
        InputStream in = conn.getInputStream();
        StringBuilder sb2 = new StringBuilder();
        if (res == 200) {
            int ch;
            while ((ch = in.read()) != -1) {
                sb2.append((char) ch);
            }
        }
        outStream.close();
        conn.disconnect();
        return sb2.toString();
    }




}
