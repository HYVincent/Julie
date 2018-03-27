package com.vincent.mylibrary.util.apk_down;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * description ：
 * project name：Hss
 * author : Vincent
 * creation date: 2017/3/16 22:05
 *
 * @version 1.0
 */

public class ApkUtils {

    /**
     * 安装 apk 文件
     *
     * @param apkFile
     */
    //apk文件路径
    public static void installApk(Context context,File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断当前系统版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置flags标签，可读取私有信息
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //包装apk文件地址为url对象
            Uri contentUri = FileProvider.getUriForFile(context, "com.vincent.julie", apkFile);
            //设置文件数据类型
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            //设置标签打开新的activity栈
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        //非空验证
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            //启动安装apk
            context.startActivity(intent);
        }
    }
}
