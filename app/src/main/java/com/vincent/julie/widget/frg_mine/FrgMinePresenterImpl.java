package com.vincent.julie.widget.frg_mine;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.elvishew.xlog.XLog;
import com.vincent.julie.R;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.bean.VersionBean;
import com.vincent.julie.util.ResultUtil;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_mine
 * @class describe
 * @date 2018/3/18 23:57
 */

public class FrgMinePresenterImpl implements IFrgMinePresenter {

    private IFrgMineView view;
    private IFrgMineModel mineModel;

    public FrgMinePresenterImpl(IFrgMineView view) {
        this.view = view;
        mineModel = new FrgMineModelImpl();
    }

    @Override
    public void checkAppUpgrade(Context mContext, final int version) {
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_request));
        mineModel.checkAppUpgrade(mContext, version, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                if(ResultUtil.success(view,resultEntity)){
                    VersionBean bean = JSON.parseObject(JSON.toJSONString(resultEntity.getData()),VersionBean.class);
                    view.newVersion(bean);
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
