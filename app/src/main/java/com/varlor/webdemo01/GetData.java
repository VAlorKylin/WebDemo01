package com.varlor.webdemo01;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetData {
    //定义一个获取网络图片数据的方法
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置连接超时为5秒
        conn.setConnectTimeout(5000);
        //设置请求为GET
        conn.setRequestMethod("GET");
        //判断url是否请求成功
        if (conn.getResponseCode()!=200){
            throw new RuntimeException("请求url失败");
        }
        InputStream inStream = conn.getInputStream();
        byte[] bt = StreamTool.read(inStream);
        inStream.close();
        return bt;
    }

    //获取网页html源代码
    public static String getHtml(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置连接超时为5秒
        conn.setConnectTimeout(5000);
        //设置请求为GET
        conn.setRequestMethod("GET");
        //判断url是否请求成功
        Log.i("TAG",conn.getResponseCode()+"");
        if (conn.getResponseCode()==200){
            InputStream in = conn.getInputStream();
            byte[] data = StreamTool.read(in);
            String html = new String(data,"UTF-8");
            return html;
        }
        return null;
    }
}
