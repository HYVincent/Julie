package com.vincent.julie.widget.frg_mine;

import com.vincent.julie.bean.VersionBean;
import com.vincent.mylibrary.IBaseView;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_mine
 * @class describe
 * @date 2018/3/18 23:57
 */

public interface IFrgMineView extends IBaseView {

    /**
     * 新版本
     * @param versionBean
     */
    void newVersion(VersionBean versionBean);



}
