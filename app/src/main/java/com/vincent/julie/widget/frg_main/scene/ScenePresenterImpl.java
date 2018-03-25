package com.vincent.julie.widget.frg_main.scene;

import com.vincent.julie.R;
import com.vincent.julie.bean.SceneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main.scene
 * @class describe
 * @date 2018/3/25 20:57
 */

public class ScenePresenterImpl implements IScenePresenter {

    private ISceneView view;
    private ISceneModel model;

    public ScenePresenterImpl(ISceneView view) {
        this.view = view;
        model = new SceneModelImpl();
    }

    @Override
    public void initSceneData() {
        List<SceneBean> data = new ArrayList<>();
        SceneBean goHome = new SceneBean();
        goHome.setName("回家");
        goHome.setStatus(false);
        goHome.setImgId(R.mipmap.scene_icon_return_home);

        SceneBean leave = new SceneBean();
        leave.setName("离家");
        leave.setStatus(false);
        leave.setImgId(R.mipmap.scene_icon_leave);

        SceneBean meeting = new SceneBean();
        meeting.setName("会客");
        meeting.setStatus(false);
        meeting.setImgId(R.mipmap.scene_icon_return_home);

        SceneBean play = new SceneBean();
        play.setName("娱乐");
        play.setStatus(false);
        play.setImgId(R.mipmap.scene_icon_play);

        SceneBean notAuto = new SceneBean();
        notAuto.setName("手控");
        notAuto.setStatus(false);
        notAuto.setImgId(R.mipmap.scene_icon_manual_control);

        SceneBean auto = new SceneBean();
        auto.setName("自控");
        auto.setStatus(false);
        auto.setImgId(R.mipmap.scene_icon_auto_run);

        data.add(goHome);
        data.add(leave);
        data.add(meeting);
        data.add(play);
        data.add(notAuto);
        data.add(auto);

        view.refreshScene(data);
    }
}
