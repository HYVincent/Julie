package com.vincent.mylibrary.util.apk_down;

/**
 * @name MyUtils
 * @class name：com.vincent.example
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017年9月2日11:32:55
 * @change
 * @chang time
 * @class describe
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();

}
