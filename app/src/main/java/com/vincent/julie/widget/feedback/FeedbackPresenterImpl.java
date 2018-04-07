package com.vincent.julie.widget.feedback;

import android.content.Context;
import android.text.TextUtils;

import com.vincent.julie.R;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.util.ResultUtil;
import com.vincent.mylibrary.util.TextViewUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.feedback
 * @class describe
 * @date 2018/4/4 23:26
 */
public class FeedbackPresenterImpl implements IFeedbackPresenter{

    private IFeedbackView view;
    private IFeedbackModel model;

    public FeedbackPresenterImpl(IFeedbackView view) {
        this.view = view;
        model = new FeedbackModelImpl();
    }

    @Override
    public void commitFeedback(Context mContext, Integer user_id, String feedback_title, int feedback_type, String feedback_content) {
        if(TextUtils.isEmpty(feedback_title)){
            view.toastMsg(mContext.getString(R.string.toast_msg_feedback_title_is_not_null));
            return;
        }
        if(TextUtils.isEmpty(feedback_content)){
            view.toastMsg(mContext.getString(R.string.toast_msg_feedback_content_is_not_null));
            return;
        }
       view.showLoadingDialog(mContext.getString(R.string.loading_msg_commit));
        model.commitFeedback(mContext, user_id, feedback_title, feedback_type, feedback_content, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                if(ResultUtil.success(view,resultEntity)){
                    view.commitSuccess();
                }
            }

            @Override
            public void responseError(Throwable throwable) {
                ResultUtil.error(view);
            }

            @Override
            public void responseIsNull() {
                ResultUtil.error(view);
            }
        });
    }
}
