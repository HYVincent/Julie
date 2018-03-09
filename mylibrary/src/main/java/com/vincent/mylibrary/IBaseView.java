package com.vincent.mylibrary;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary
 * @class describe
 * @date 2017/12/12 11:57
 */

public interface IBaseView {

    /**
     * 数据加载失败..
     */
    void loadFail(String errorMsg);

    /**
     * 显示加载效果
     * @param msg
     */
    void showLoadingDialog(String msg);

    /**
     * 隐藏加载效果
     */
    void hideLoadingDialog();

    /**
     * 弹出toast
     * @param msg 消息内容
     */
    void toastMsg(String msg);

}
