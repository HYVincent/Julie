package com.vincent.mylibrary.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/1/17 18:08
 */

public class NumberUtils {

    /**
     * 得到一个随机数 并制定范围
     * @param min 最小范围
     * @param max 最大范围
     * @return
     */
    public static Integer getOneRandomNumber(int min,int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

    /**
     * 得到多个随机数
     * @param count
     * @param min
     * @param max
     * @return
     */
    public static List<Integer> getMoreRandomNumbers(int count,int min,int max){
        List<Integer> datas = new ArrayList<>();
        for (int i = 0;i<count;i++){
            datas.add(getOneRandomNumber(min,max));
        }
        return datas;
    }

}
