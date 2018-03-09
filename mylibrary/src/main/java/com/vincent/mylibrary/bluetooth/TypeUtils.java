package com.vincent.mylibrary.bluetooth;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical_Android
 * @page com.vincent.mylibrary.bluetooth
 * @class describe
 * @date 2018/1/25 17:34
 */

public class TypeUtils {

    /**
     * String转为byte[]
     * @param str
     * @return
     */
    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

}
