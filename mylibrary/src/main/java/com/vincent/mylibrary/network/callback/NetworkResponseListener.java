package com.vincent.mylibrary.network.callback;

import com.vincent.mylibrary.entity.NetworkResponseEntity;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.mylibrary.network.callback
 * @class describe
 * @date 2018/3/1 22:51
 */

public interface NetworkResponseListener {

    /**
     * 响应成功
     * @param networkResponseEntity
     */
    void onSuccess(NetworkResponseEntity networkResponseEntity);

    /**
     *
     * @param errorMsg
     */
    void onFail(String errorMsg);

}
