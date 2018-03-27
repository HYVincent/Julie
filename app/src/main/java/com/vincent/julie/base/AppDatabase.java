package com.vincent.julie.base;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.base
 * @class describe
 * @date 2018/3/27 23:56
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    /**
     * 数据库名称
     */
    public static final String NAME = "Julie";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;


}


