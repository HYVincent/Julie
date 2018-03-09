package com.vincent.mylibrary.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.mylibrary.util
 * @class describe 通知工具类
 * @date 2018/2/7 1:01
 */

public class NotificationUtil {

    /**
     * 发送通知提示
     * @param context 上下文
     * @param imgId 通知栏显示的图标id
     * @param title 通知title
     * @param msg 消息
     * @param activity 点击通知需要跳转的Activity，Activity需要完整路径，包含包名
     */
    public static void sendNotification(Context context, String activity, int imgId, String title, String msg) {
        try {
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            Intent resultIntent = new Intent(context, SystemUtil.getReflectInstance(context,activity).getClass());
            resultIntent.putExtra("title",title);
            resultIntent.putExtra("msg",msg);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(imgId)
                    .setTicker("您有新的消息")
                    .setContentTitle(title)//通知的标题
                    .setContentText(msg)//显示在界面上的内容
                    .setContentIntent(pendingIntent);
            Notification mNotification = mBuilder.build();
            mNotification.iconLevel=imgId;
            //在通知栏上点击此通知后自动清除此通知
            mNotification.flags = Notification.FLAG_ONGOING_EVENT;//FLAG_ONGOING_EVENT 在顶部常驻，可以调用下面的清除方法去除  FLAG_AUTO_CANCEL  点击和清理可以去调
            mNotification.flags = Notification.COLOR_DEFAULT;
            mNotification.defaults = Notification.DEFAULT_SOUND;//声音效果，不震动
            mNotification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
            //设置发出消息的内容
            mNotification.tickerText = "您有新的消息";//通知产生的时候发出的内容
            //设置发出通知的时间
            mNotification.when = System.currentTimeMillis();
            mNotificationManager.notify((int) System.currentTimeMillis(),mNotification);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static PendingIntent getDefalutIntent(Context context,int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(context, 1, new Intent(), flags);
        return pendingIntent;
    }

}
