package com.vincent.julie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.vincent.julie.service.MyNettyPushService;
import com.vincent.julie.tcp.TcpClient;
import com.vincent.mylibrary.entity.NetworkResponseEntity;
import com.vincent.mylibrary.network.NetworkUtils;
import com.vincent.mylibrary.network.callback.NetworkResponseListener;
import com.vincent.mylibrary.util.MainHandler;
import com.vincent.mylibrary.util.MyToastUtil;
import com.vincent.mylibrary.util.ThreadManager;
import com.vincent.mylibrary.util.ThreadPoolManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "啊啊啊";
    private String url = "http://123.207.47.61:8080/MyWeb/user/login?user_password=555555&user_phone=18696855784";
    private EditText etInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                startService(new Intent(MainActivity.this, MyNettyPushService.class));
            }
        });
        etInput = findViewById(R.id.et_input);
        findViewById(R.id.btn_network).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TcpClient.getInstance().startConnectSocketService(etInput.getText().toString());
                Log.d(TAG, "onClick: "+TcpClient.getInstance().isConnect());
            }
        });
    }




}
