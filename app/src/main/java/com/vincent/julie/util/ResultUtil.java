package com.vincent.julie.util;

import com.vincent.julie.R;
import com.vincent.julie.base.MyApp;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.mylibrary.IBaseView;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.util
 * @class describe
 * @date 2018/3/11 10:54
 */

public class ResultUtil {

    /**
     * 对结果进行处理
     * @param view
     * @param responseEntity
     * @return
     */
    public static boolean success(IBaseView view, ResponseEntity responseEntity){
        view.hideLoadingDialog();
        if(!responseEntity.isSuccess()){
            view.loadFail();
            view.toastMsg(responseEntity.getMsg());
        }
        return responseEntity.isSuccess();
    }

    public static void error(IBaseView view){
        view.loadFail();
        view.hideLoadingDialog();
        view.toastMsg(MyApp.getMyApp().getString(R.string.toast_msg_service_error));
    }

}
