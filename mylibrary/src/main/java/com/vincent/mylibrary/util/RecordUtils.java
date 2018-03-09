package com.vincent.mylibrary.util;

import android.content.Context;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.IOException;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe 录音工具类 注意权限问题
 * @date 2018/1/19 9:09
 */

public class RecordUtils {

    private static final String TAG = RecordUtils.class.getSimpleName();
    //语音操作对象
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;
    //保存的文件完整路径
    private String filePath = null;
    private String fileDir = null;
    private String fileName = "Record"+System.currentTimeMillis();
    //root 根目录
    private String folders = "StartKangMedical";
    //子目录
    private String childFolders = "record";
    private static RecordUtils recordUtils;

    private RecordUtils(){
        //设置sdcard的路径
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileDir = filePath + "/" + folders + "/"+childFolders+"/";
        filePath = fileDir + fileName+".wav";//会存放到手机外部存储最外层目录
        FileUtil.createOrExistsDir(fileDir);
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        Log.d(TAG, "RecordUtils: filePath = "+filePath);
        mRecorder.setOutputFile(filePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }

    public static RecordUtils getRecordUtils() {
        if(recordUtils == null){
            recordUtils = new RecordUtils();
        }
        return recordUtils;
    }


    /**
     * 开始录音
     * @return
     */
    public boolean startRecord(){
        try {
            mRecorder.prepare();
            mRecorder.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch (IllegalStateException e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 停止录音
     */
    public void stopRecord(){
        if (mRecorder != null) {
            //added by ouyang start
            try {
                //下面三个参数必须加，不加的话会奔溃，在mediarecorder.stop();
                //报错为：RuntimeException:stop failed
                mRecorder.setOnErrorListener(null);
                mRecorder.setOnInfoListener(null);
                mRecorder.setPreviewDisplay(null);
                mRecorder.stop();
            } catch (IllegalStateException e) {
                // TODO: handle exception
                Log.i("Exception", Log.getStackTraceString(e));
            }catch (RuntimeException e) {
                // TODO: handle exception
                Log.i("Exception", Log.getStackTraceString(e));
            }catch (Exception e) {
                // TODO: handle exception
                Log.i("Exception", Log.getStackTraceString(e));
            }
            //added by ouyang end

            mRecorder.release();
            mRecorder = null;
        }
    }

    /**
     * 播放录音
     * @param filePath 完整的文件路径
     * @return
     */
    public boolean playRecord(String filePath){
        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(filePath);
            mPlayer.prepare();
            mPlayer.start();
            return true;
        }catch(IOException e){
            Log.e(TAG,"播放失败");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 播放声音文件
     * @param mContext
     * @param resourceId
     * @return
     */
    public boolean playBell(Context mContext,int resourceId){
        try {
            if(resourceId != 0){
                mPlayer= MediaPlayer.create(mContext, resourceId);
            }else {
                mPlayer= MediaPlayer.create(mContext, getSystemDefultRingtoneUri(mContext));
            }
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
//                    mediaPlayer.start();
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


    /**
     * 停止播放录音
     */
    public void stopPlayRecord(){
        if(mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }

}
