package com.varlor.webdemo01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView txtMenu,txtShow;
    private ImageView imgPic;
    private WebView webView;
    private ScrollView scroll;
    private Bitmap bitmap;
    private String detail="";
    private boolean flag=false;
    private final static String PIC_URL="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604570914556&di=59a6806c096d4b7616c0222d6f1065b8&imgtype=0&src=http%3A%2F%2Fstatic.1sapp.com%2Flw%2Fimg%2F2019%2F11%2F27%2F1bebbacdfd7a6a0be35c7cb313168278.jpeg";
    private final static String HTML_URL="https://www.baidu.com";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0X001:
                    hideAllWidget();
                    imgPic.setVisibility(View.VISIBLE);
                    imgPic.setImageBitmap(bitmap);
                    Toast.makeText(MainActivity.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0X002:
                    hideAllWidget();
                    scroll.setVisibility(View.VISIBLE);
                    txtShow.setText(detail);
                    Toast.makeText(MainActivity.this, "代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0X003:
                    hideAllWidget();
                    webView.setVisibility(View.VISIBLE);
                    webView.loadDataWithBaseURL("",detail,"text/html","UTF-8","");
                    Toast.makeText(MainActivity.this, "网页加载完毕", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        txtMenu = findViewById(R.id.txtMenu);
        txtShow = findViewById(R.id.txtshow);
        imgPic = findViewById(R.id.imgPic);
        webView = findViewById(R.id.webview);
        scroll = findViewById(R.id.scroll);
        registerForContextMenu(txtMenu);
    }
    //定义一个隐藏所有控件的方法
    private void hideAllWidget(){
        imgPic.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }

    @Override
    //重写上下文菜单的创建方法
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.menus, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    //上下文菜单被点击是触发该方法

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.one:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            byte[] data = GetData.getImage(PIC_URL);
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0X001);
                    }
                }).start();
                break;
            case R.id.two:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            detail = GetData.getHtml(HTML_URL);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0X002);
                    }
                }).start();
                break;
            case R.id.three:
                if (detail==null||"".equals(detail)){
                    Toast.makeText(this, "空", Toast.LENGTH_SHORT).show();
                }else {
                    handler.sendEmptyMessage(0X003);
                }
                break;
        }
        return true;
    }
}