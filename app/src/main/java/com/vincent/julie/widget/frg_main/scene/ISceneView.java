package com.vincent.julie.widget.frg_main.scene;

import com.vincent.julie.bean.SceneBean;
import com.vincent.mylibrary.IBaseView;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main.scene
 * @class describe
 * @date 2018/3/25 20:58
 */

public interface ISceneView extends IBaseView {

    /**
     * 刷新数据
     * @param data
     */
    void refreshScene(List<SceneBean> data);

}
