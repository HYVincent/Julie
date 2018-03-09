package com.vincent.mylibrary.util;

import android.os.CountDownTimer;

/**
 * @name HUDHelp
 * @class name：com.vincent.library.util
 * @class describe
 * @author Vincent QQ:1032006226
 * @time 2017/10/11 17:55
 * @change
 * @chang time
 * @class describe
 */

public class TimeCount extends CountDownTimer {

    private TimeOnListener listener;



    /**
     * @param s 总的时间 秒
     */
    public TimeCount(long s, TimeOnListener listener) {
        //转为毫秒数 另外设置为1s中执行一次
        super(s * 1000, 1000);
        this.listener = listener;
    }

    @Override
    public void onTick(long l) {
        //转化为秒
        listener.everyAction((int) l/1000);
    }


    @Override
    public void onFinish() {
        listener.finishAction();
    }

    public interface TimeOnListener{

        void finishAction();

        void everyAction(int s);
    }

}
