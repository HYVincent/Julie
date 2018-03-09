package com.vincent.mylibrary.util;

import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/2/2 16:05
 */


public class VibratorUtils {

    private Vibrator vibrator;
    private static VibratorUtils instance;

    private VibratorUtils(Context mContext){
        vibrator = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
    }

    public static VibratorUtils getInstance(Context mContext) {
        if(instance == null){
            instance = new VibratorUtils(mContext);
        }
        return instance;
    }

    /**
     * 开始振动
     * @param vibratorTime 持续时间
     * @param amplitude 震动强度  范围值为0-255之间
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startVibrator(long vibratorTime, int amplitude){
        if(Build.VERSION.SDK_INT >25){
            VibrationEffect vibrationEffect = VibrationEffect.createOneShot(vibratorTime,amplitude);
            vibrator.vibrate(vibrationEffect);
        }else {
            vibrator.vibrate(vibratorTime);
        }
    }

}
