package com.vincent.mylibrary.util;

import android.os.Message;
import android.util.Log;

import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/1/3 15:59
 */


public class TimeUtils {

    private static  ScheduledExecutorService scheduledExecutorService;
    private static final String TAG = TimeUtils.class.getSimpleName();
    /**
     * 计时器，定时任务 这个比较完美，并且在任务时间大于定时时间的时候也能正常执行
     * @param initValues 初始值，
     * @param delayTime 延迟时间  延迟多少毫秒开始执行
     * @param interval 间隔时间，间隔多少时间执行一次
     * @param threadNum 线程池的数量
     * @param timeListener 监听器
     */
    public static void startTime(int initValues,long delayTime,long interval,  int threadNum, final TimeListener timeListener) {
        Log.d(TAG, "startTime: 开始计时..");
        final AtomicInteger atomicInteger = new AtomicInteger(initValues);
        if (threadNum == 0) {
            threadNum = 1;
        }
        scheduledExecutorService = new ScheduledThreadPoolExecutor(threadNum);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                timeListener.doAction(atomicInteger.incrementAndGet());
//                timeListener.doAction(1);
            }
        }, delayTime, interval, TimeUnit.MILLISECONDS);
    }

    public static void cancelTimeTask(){
        if(scheduledExecutorService!= null){
            scheduledExecutorService.shutdownNow();
        }
    }

    public static void onPause(){
    }

    public interface TimeListener{
        void doAction(int index);
    }
    //另一种实现。。。。。。
    private static Timer timer;
    private static TimerTask task;

    public static void timerStart(long delay,long period,final TimerListener listener){
        timer = new Timer(true);
        // 注意，javax.swing包中也有一个Timer类，如果import中用到swing包,要注意名字的冲突。

        task = new TimerTask() {
            @Override
            public void run() {
                listener.doAction();
            }
        };
        //以下是几种常用调度task的方法：
//        timer.schedule(task, times);
            // time为Date类型：在指定时间执行一次。
//        timer.schedule(task, firstTime, period);
        // firstTime为Date类型,period为long
        // 从firstTime时刻开始，每隔period毫秒执行一次。

//        timer.schedule(task, delay)
    // delay 为long类型：从现在起过delay毫秒执行一次
        timer.schedule(task, delay, period);
        // delay为long,period为long：从现在起过delay毫秒以后，每隔period
    // 毫秒执行一次。
    }

    public static void timerCancel(){
        if(task != null){
            task.cancel();
        }
    }

    public interface TimerListener{
        void  doAction();
    }

}
