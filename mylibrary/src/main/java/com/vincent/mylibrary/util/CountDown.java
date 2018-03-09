package com.vincent.mylibrary.util;

import android.os.CountDownTimer;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary.util
 * @class describe 倒计时  创建对象，然后调用其start函数启动
 * @date 2017/12/12 11:06
 */

public class CountDown extends CountDownTimer {

    private TimeFinishOnListener listener;

    /**
     * @param allS 总的时间 s为单位
     */
    public CountDown(int allS, TimeFinishOnListener listener) {
        super(allS * 1000, 1000);
        this.listener = listener;
    }

    @Override
    public void onTick(long l) {
        listener.everySecondAction();
    }

    @Override
    public void onFinish() {
        listener.finish();
    }

    public interface TimeFinishOnListener {

        void finish();

        void everySecondAction();
    }
}
