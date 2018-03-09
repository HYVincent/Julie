package com.vincent.mylibrary.adapter;

import android.view.View;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog.adapter
 * @class describe
 * @date 2017/12/26 10:32
 */

public interface OnItemClickListener {

    /**
     * RecycleView's item click listener
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

}
