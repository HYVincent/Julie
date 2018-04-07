package com.vincent.julie.widget.feedback;

import android.content.Context;

import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NovateUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.feedback
 * @class describe
 * @date 2018/4/4 23:22
 */
public class FeedbackModelImpl implements IFeedbackModel {
    @Override
    public void commitFeedback(Context mContext, Integer user_id, String feedback_title,
                               int feedback_type, String feedback_content, final INetworkResponseListener iNetworkResponseListener1) {
        NovateUtils.getNovate().call(NovateUtils.getMyApi().commitFeedback(user_id, feedback_title, feedback_type, feedback_content), new MySubscriber<ResponseEntity>(mContext) {
            @Override
            public void onError(Throwable e) {
                iNetworkResponseListener1.responseError(e);
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                if(responseEntity != null){
                    iNetworkResponseListener1.responseResult(responseEntity);
                }else {
                    iNetworkResponseListener1.responseIsNull();
                }
            }
        });
    }
}
