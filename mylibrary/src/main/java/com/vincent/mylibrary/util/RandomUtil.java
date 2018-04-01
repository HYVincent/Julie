package com.vincent.mylibrary.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/3/31 1:46
 */
public class RandomUtil {

    /**
     *  创建指定个数，指定最小值，最大值的随机数
     * @author ztd
     * @param count 随机数个数
     * @param minVal 随机数最小值
     * @param maxVal 随机数最大值
     * @return
     */
    public static List<Integer> createRandomList(int count, Integer minVal, Integer maxVal) {
        List<Integer> integers = new ArrayList<Integer>();
        for(int i =0; i < count; i ++) {
            integers.add(createNewRandomKey(integers, minVal, maxVal));
        }
        return integers;
    }

    /**
     *  创建一个不重复的随机数
     * @author ztd
     * @param keys
     * @param minVal
     * @param maxVal
     * @return
     */
    private static Integer createNewRandomKey(List<Integer> keys, Integer minVal, Integer maxVal) {
        Integer v = createRandomKey(minVal, maxVal);
        while(keys.contains(v)) {
            v = createRandomKey(minVal, maxVal);
        }
        return v;
    }

    /**
     *  创建一个在范围内的随机数
     * @author ztd
     * @param minVal
     * @param maxVal
     * @return
     */
    private static Integer createRandomKey(Integer minVal, Integer maxVal) {
        Integer v  = new Random().nextInt(maxVal);
        if(v <= minVal) {
            v = v +minVal;
        }
        return v;
    }

}
