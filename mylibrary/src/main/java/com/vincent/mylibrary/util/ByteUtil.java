package com.vincent.mylibrary.util;

import java.util.Arrays;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name ByteUtil
 * @page com.vincent.mylibrary.util
 * @class describe 常量类
 * @date 2018/3/8 17:06
 */

public class ByteUtil {

    /**
     * 拆分byte数组
     *
     * @param bytes
     *            要拆分的数组
     * @param copies
     *            要按几个组成一份
     * @return
     */
    public static byte[][] split_bytes(byte[] bytes, int copies) {

        double split_length = Double.parseDouble(copies + "");

        int array_length = (int) Math.ceil(bytes.length / split_length);
        byte[][] result = new byte[array_length][];

        int from, to;

        for (int i = 0; i < array_length; i++) {

            from = (int) (i * split_length);
            to = (int) (from + split_length);

            if (to > bytes.length)
                to = bytes.length;

            result[i] = Arrays.copyOfRange(bytes, from, to);
        }

        return result;
    }

}
