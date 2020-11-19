package com.varlor.webdemo01;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SaveFileTool {

    public static void saveImage(byte[] data, Context context) throws IOException {
        String fileName="";
        fileName=System.currentTimeMillis()+"WatchDog.jpg";
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME,fileName);
        values.put(MediaStore.Images.Media.DESCRIPTION,fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/"+"*");
        values.put(MediaStore.Images.Media.RELATIVE_PATH,"DCIM/test");
        Uri externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Uri insert = contentResolver.insert(externalContentUri, values);
        OutputStream outputStream = contentResolver.openOutputStream(insert);
        //data是InputStream转换成的byte[]
        outputStream.write(data);
        outputStream.close();
    }
}
