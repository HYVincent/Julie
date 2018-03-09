package com.vincent.julie.tcp;

import android.text.TextUtils;
import android.util.Log;

import com.vincent.mylibrary.util.ThreadPoolManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.tcp
 * @class describe
 * @date 2018/3/9 22:48
 */


public class TcpClient {
    private static final String TAG = TcpClient.class.getSimpleName();
    private static TcpClient instance;
    //服务器端口
    private static final int SERVICE_PORT = 8081;
    //服务器ip地址
//    private static final String SERVICE_IP = "123.207.47.61";
    private static final String SERVICE_IP = "192.168.3.3";
    //
    private PrintWriter printWriter = null;
    private Socket socket = null;

    public static TcpClient getInstance() {
        if (instance == null){
            instance = new TcpClient();
        }
        Log.d(TAG, "getInstance: init ..");
        return instance;
    }


    /**
     * socket 是否连接
     * @return
     */
    public boolean isConnect(){
        if(socket != null){
            return socket.isConnected();
        }else {
            return false;
        }
    }

    public void startConnectSocketService(final String msg){
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(SERVICE_IP,SERVICE_PORT);
                    OutputStream outputStream = socket.getOutputStream();
                    //注意第二个参数为true将自动flush,否则需要手动操作out.flush()
                    printWriter = new PrintWriter(outputStream,true);
                    printWriter.println(msg);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = input.readLine();
                    Log.d(TAG, "startConnectSocketService: message from Service:"+message);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "startConnectSocketService: 连接失败.");
                }
            }
        });
    }
}
