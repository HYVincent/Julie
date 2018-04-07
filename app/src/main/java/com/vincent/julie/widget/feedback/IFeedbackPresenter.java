package com.vincent.julie.widget.feedback;

import android.content.Context;

import com.vincent.julie.bean.INetworkResponseListener;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.feedback
 * @class describe
 * @date 2018/4/4 21:26
 */

public interface IFeedbackPresenter {
//http://123.207.47.61:8080/MyWeb/user/feedback/addFeedback?user_id=10000&feedback_title=捉住了一只bug&feedback_type=2&feedback_content=我不认识它&feedback_time=1522852284

    /**
     * 提交反馈或者bug
     * @param mContext
     * @param user_id 用户user_id
     * @param feedback_title title
     * @param feedback_type 1 反馈  2 bug
     * @param feedback_content 内容
     */
    void  commitFeedback(Context mContext, Integer user_id, String feedback_title, int feedback_type, String feedback_content);

}
