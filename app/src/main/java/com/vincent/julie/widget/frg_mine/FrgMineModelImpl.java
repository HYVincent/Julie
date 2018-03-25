package com.vincent.julie.widget.frg_mine;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.elvishew.xlog.XLog;
import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NovateUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_mine
 * @class describe
 * @date 2018/3/18 23:55
 */

public class FrgMineModelImpl implements IFrgMineModel {
    @Override
    public void checkAppUpgrade(Context mContext, int version, final INetworkResponseListener iNetworkResponseListener) {
        NovateUtils.getNovate().call(NovateUtils.getMyApi().checkNewVersion(version), new MySubscriber<ResponseEntity>(mContext) {
            @Override
            public void onError(Throwable e) {
                iNetworkResponseListener.responseError(null);
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                if(responseEntity != null){
                    iNetworkResponseListener.responseResult(responseEntity);
                }else {
                    iNetworkResponseListener.responseIsNull();
                }
            }
        });
    }
}
