package com.vincent.julie.widget.frg_mine;

import android.content.Context;

import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.INetworkResponseStringListener;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_mine
 * @class describe
 * @date 2018/3/18 23:54
 */

public interface IFrgMineModel {

    void checkAppUpgrade(Context mContext, int version, INetworkResponseListener iNetworkResponseListener);


}
