package com.vincent.julie.bean;

import android.widget.CompoundButton;
import android.widget.Switch;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.vincent.julie.base.AppDatabase;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.base
 * @class describe https://www.jianshu.com/p/3dfeca6434b3
 * @date 2018/3/26 0:05
 */
@Table(database = AppDatabase.class)
public class RoomBean extends BaseModel{

    @PrimaryKey(autoincrement = true)//ID自增
    public long id;
    @Column
    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
