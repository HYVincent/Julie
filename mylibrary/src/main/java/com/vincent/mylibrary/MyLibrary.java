package com.vincent.mylibrary;

import android.content.Context;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.formatter.border.DefaultBorderFormatter;
import com.elvishew.xlog.formatter.message.json.DefaultJsonFormatter;
import com.elvishew.xlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.DefaultXmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.DefaultStackTraceFormatter;
import com.elvishew.xlog.formatter.thread.DefaultThreadFormatter;
import com.vincent.mylibrary.bluetooth.BleControl;
import com.vincent.mylibrary.util.SPUtil;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @project_name StarKangMedical
 * @page_name com.vincent.mylibrary
 * @class describe
 * @date 2017/12/8 11:53
 */


public class MyLibrary {

    private static Context mContext;
    private static SPUtil spUtil;
    private static final String defaultTag = MyLibrary.class.getSimpleName();

    public static void init(Context context){
        mContext = context;
        initXLogs(defaultTag);
        spUtil = SPUtil.getInstance(context,defaultTag);
    }

    public static void init(Context context,String configName){
        mContext = context;
        initXLogs(configName);
        spUtil = SPUtil.getInstance(context,configName);
        //初始化蓝牙
        BleControl.getInstance().initBle(context);
    }



    private static void initXLogs(String loggerTag) {
        // https://github.com/orhanobut/logger
       /* AndroidLogAdapter androidLogAdapter = new AndroidLogAdapter();
        androidLogAdapter.isLoggable(Logger.DEBUG,loggerTag);
        Logger.addLogAdapter(androidLogAdapter);*/


        LogConfiguration config = new LogConfiguration.Builder()
                .tag("MyLibrary")                                         // 指定 TAG，默认为 "X-LOG"
                .t()                                                   // 允许打印线程信息，默认禁止
                .st(2)                                                 // 允许打印深度为2的调用栈信息，默认禁止
                .b()                                                   // 允许打印日志边框，默认禁止
                .jsonFormatter(new DefaultJsonFormatter())                  // 指定 JSON 格式化器，默认为 DefaultJsonFormatter
                .xmlFormatter(new DefaultXmlFormatter())                    // 指定 XML 格式化器，默认为 DefaultXmlFormatter
                .throwableFormatter(new DefaultThrowableFormatter())        // 指定可抛出异常格式化器，默认为 DefaultThrowableFormatter
                .threadFormatter(new DefaultThreadFormatter())              // 指定线程信息格式化器，默认为 DefaultThreadFormatter
                .stackTraceFormatter(new DefaultStackTraceFormatter())      // 指定调用栈信息格式化器，默认为 DefaultStackTraceFormatter
                .borderFormatter(new DefaultBorderFormatter())               // 指定边框格式化器，默认为 DefaultBorderFormatter
//                .addObjectFormatter(AnyClass.class,                    // 为指定类添加格式化器
//                        new AnyClassObjectFormatter())                 // 默认使用 Object.toString()
                .build();

        XLog.init(config);
    }

    public static SPUtil getSpUtil() {
        checkInit();
        return spUtil;
    }

    public static Context getmContext() {
        checkInit();
        return mContext;
    }

    private static void checkInit(){
        if(mContext == null){
            throw new NullPointerException("Please in your Application's onCreate init it(init method)");
        }
    }

}
