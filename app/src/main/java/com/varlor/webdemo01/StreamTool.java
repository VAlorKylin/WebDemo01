package com.varlor.webdemo01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {
    //从流中读取数据
    public static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=inputStream.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        inputStream.close();
        return outStream.toByteArray();
    }
}
