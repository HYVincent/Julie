package com.vincent.mylibrary.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/2/5 20:20
 */


public class MediaPlayerUtils {

    private static MediaPlayerUtils instance;
    private MediaPlayer mPlayer = null;
    private int playCount = 0;

    public static MediaPlayerUtils getInstance() {
        if(instance == null){
            instance = new MediaPlayerUtils();
        }
        return instance;
    }


    /**
     * 播放声音文件
     * @param mContext 上下文对象
     * @param resourceId 资源id 如果为0 则播放系统铃声
     * @param count 小于0 循环播放 大于0 为播放次数
     * @return
     */
    public boolean playBell(Context mContext, int resourceId, final int count ){
        try {
            if(resourceId != 0){
                mPlayer= MediaPlayer.create(mContext, resourceId);
            }else {
                mPlayer= MediaPlayer.create(mContext, getSystemDefultRingtoneUri(mContext));
            }
            mPlayer.setLooping(false);
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(count <= 0){
                        mp.start();
                    }else {
                       playCount ++;
                       if(playCount == count){
                           mPlayer.stop();
                           mPlayer.reset();
                       }
                    }
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //获取系统默认铃声的Uri
    private Uri getSystemDefultRingtoneUri(Context mContext) {
        return RingtoneManager.getActualDefaultRingtoneUri(mContext,
                RingtoneManager.TYPE_RINGTONE);
    }

}
