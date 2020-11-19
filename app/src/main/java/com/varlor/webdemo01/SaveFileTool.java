package com.varlor.webdemo01;

import android.os.Environment;

public class SaveFileTool {
    public static void saveImage(){
        String path = Environment.getExternalStorageDirectory().getPath();
        System.out.println(path);
    }
}
