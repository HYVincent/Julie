package com.vincent.mylibrary.entity;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog.entity
 * @class describe
 * @date 2017/12/26 10:29
 */

public class SelectEntity {
    //是否选中
    private boolean isSelect;
    //item的内容
    private String itemContent;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}
