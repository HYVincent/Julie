package com.vincent.mylibrary.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/3/31 1:43
 */
public class FloatUtil {

    /**
     * 获取随机值
     * @param num
     * @param maxValue
     * @return
     */
    public static List<Float> getRandom(int num, int maxValue){
        List<Float> data = new ArrayList<Float>();
        Random rand = new Random();
        for(int i = 0;i<num;i++) {
            data.add(fomatFloat(rand.nextFloat()*maxValue));
        }
        return data;
    }

    /**
     * 格式化Float,取两位小数
     * @param value
     * @return
     */
    public static float fomatFloat(float value) {
        return Float.valueOf(fomatFloatStr(value));
    }

    /**
     * 格式化Float,取两位小数
     * @param value
     * @return
     */
    public static String fomatFloatStr(float value) {
        DecimalFormat fnum  =   new  DecimalFormat("##0.00");
        return fnum.format(value);
    }

}
