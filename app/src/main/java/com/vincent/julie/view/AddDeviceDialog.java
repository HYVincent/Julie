package com.vincent.julie.view;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vincent.julie.R;
import com.vincent.mylibrary.dialog.BaseDialog;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.view
 * @class describe
 * @date 2018/3/24 18:54
 */

public class AddDeviceDialog extends BaseDialog{

    public AddDeviceDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_layout_add_device);
    }
}
