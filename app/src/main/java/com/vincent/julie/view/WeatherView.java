package com.vincent.julie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.vincent.mylibrary.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name CustomView
 * @page com.vincent.custom.custom_view
 * @class describe
 * @date 2018/3/31 0:08
 */
public class WeatherView extends View {
    private static final String TAG = WeatherView.class.getSimpleName();
    private Context mContext;
    //画布背景颜色
    private int backgroundColor = Color.parseColor("#698B22");
    //存放时间点，从0:00 到 23:00
    private List<String> timeDots = new ArrayList<>();
    //除了数据的颜色别的颜色值
    private int baseColor = Color.parseColor("#474747");
    //画笔
    private Paint mPaint;
    //整个一天的温度，从0~到24点  所以dataTemp里面有24个值
    private List<Float> dataTemp = new ArrayList<>();
    //温度最大值为60度
    private float mMaxTempValue = 60;
    //温度最小值为零下20度
    private float mMinTempValue = -20;
    //温度之间的间距
    private float interval = 10;
    //距离底部的边距的间距为10
    private float marginButtom = 10;
    //距离左边边界的间距为10
    private float marginLeft = 10;
    private float marginRigth = 10;
    //View的高度
    private float mViewHeight = 30;
    //View的宽度
    private float mViewWidth;
    //当前时间 点 比如 21点
    private int currentTime ;
    //横向的线条 和标记
    private Path mLinePath;
    private Path mDataPath;
    // startX startY 表示的是表两条线的交点的x,y坐标
    private float startX = 40;
    private float startY;
    private float marginTop = 15;
    //表示时间节点之间的间距
    private float timeDotsInterval = 15;
    private int timeDotsTextSize = 8;
    //手指在屏幕滑动的时候的偏移量
    private float offsetX = 0f;
    //手指按下的x坐标
    private float downX = 0f;
    //实际数据的宽度
    private float dataWidth= 0f;

    //绘制矩形的右下角坐标Y
    private float endY = 0;
    //手指触摸View时的X坐标
    private float touchX = 0;
    //x轴偏移量
    private float offset_x;
    //总的偏移量
    private float offset_x_d;
    //X轴的最大偏移量
    private float offset_x_max = 0f;
    private List<Float> data = new ArrayList<>();

    /**
     * 设置数据
     * @param data
     */
    public void setData(List<Float> data) {
        this.data = data;
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取手指触摸屏幕的点
                touchX = event.getX();
                Log.d(TAG, "onTouchEvent: 按下-->"+touchX);
                break;
            case MotionEvent.ACTION_MOVE:
                offset_x = event.getX()-touchX;
                //降低滚动的速度
                offset_x = offset_x/5;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:break;
        }
        return true;
    }

    /**
     * 初始化
     * @param mContext
     */
    private void init(Context mContext){
        this.mContext = mContext;
        marginRigth = DisplayUtils.dp2px(mContext,marginRigth);
        marginLeft = DisplayUtils.dp2px(mContext,marginLeft);
        marginButtom = DisplayUtils.dp2px(mContext,marginButtom);
        startX = DisplayUtils.dp2px(mContext,startX);
        timeDotsInterval = DisplayUtils.dp2px(mContext,timeDotsInterval);
        timeDotsTextSize = DisplayUtils.dp2px(mContext,timeDotsTextSize);
        marginTop = DisplayUtils.dp2px(mContext,marginTop);
        initTimeDots(timeDots);
        mPaint = new Paint();
        mPaint.setStrokeWidth(4f);
        mPaint.setAntiAlias(true);
        //描边
        mPaint.setStyle(Paint.Style.STROKE);
        mLinePath = new Path();
        mDataPath = new Path();
    }

    private void initTimeDots(List<String> timeDots) {
        timeDots.add("00:00");
        timeDots.add("01:00");
        timeDots.add("02:00");
        timeDots.add("03:00");
        timeDots.add("04:00");
        timeDots.add("05:00");
        timeDots.add("06:00");
        timeDots.add("07:00");
        timeDots.add("08:00");
        timeDots.add("09:00");
        timeDots.add("10:00");
        timeDots.add("11:00");
        timeDots.add("12:00");
        timeDots.add("13:00");
        timeDots.add("14:00");
        timeDots.add("15:00");
        timeDots.add("16:00");
        timeDots.add("17:00");
        timeDots.add("18:00");
        timeDots.add("19:00");
        timeDots.add("20:00");
        timeDots.add("21:00");
        timeDots.add("22:00");
        timeDots.add("23:00");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化宽高值
        mViewHeight = h;
        mViewWidth = w;
        //表示 底部的横线距离View底部边界距离为30dp
        startY = mViewHeight - DisplayUtils.dp2px(mContext,30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(backgroundColor);
        drawBg(canvas);
    }


    /**
     * 绘制除了数据之外的
     * @param canvas
     */
    private void drawBg(Canvas canvas) {
        mPaint.setColor(baseColor);
        canvas.drawLine(startX,startY,startX,marginTop,mPaint);
        //绘制左边的文字
        float tagInterval = (mViewHeight - marginButtom - marginTop) / 5;
        mPaint.setTextSize(DisplayUtils.dp2px(mContext,12));
        mPaint.setStrokeWidth(2f);
        //绘制左边的文字
        mPaint.setColor(Color.parseColor("#EE9A00"));
        //描边和填充内部
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0;i< 5;i++){
            float x = DisplayUtils.dp2px(mContext,8);
            canvas.drawText(String.valueOf((-20 + i* 20)+"℃"),x,
                    startY - i * tagInterval,mPaint);
        }
        mPaint.setStrokeWidth(2f);
        mPaint.setTextSize(DisplayUtils.dp2px(mContext,12));
        //时间节点 第一个点的位置 x坐标
        float firstTimeDots = startX + DisplayUtils.dp2px(mContext,5);
        //移动 path 到第一个点的位置
        mLinePath.moveTo(startX,startY);
        mLinePath.lineTo(startX,startY);
        //下面的时间节点的宽度
        float timeDotsTextWidth = 0f;
        if(data.size() == 0){
            throw new NullPointerException("data size is 0.");
        }
        mDataPath.reset();
        mLinePath.reset();
        //绘制地下的文字
        offset_x_d +=offset_x;
        if(offset_x_d > 0){
            offset_x_d = 0;
        }
        Log.d(TAG, "drawBg: ------>"+offset_x_max+"   "+offset_x_d);
        if((-1)*offset_x_d >offset_x_max){
            offset_x_d = (-1)*offset_x_max;
        }
        mLinePath.moveTo(startX,startY);
        for (int i = 0;i<data.size();i++){
            float dotsX = firstTimeDots + DisplayUtils.dp2px(mContext,timeDotsInterval) * i+offset_x_d;
            String text = timeDots.get(i);
            Rect rect = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), rect);
            timeDotsTextWidth = rect.width();
            //描边和填充内部
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.parseColor("#EE9A00"));
            canvas.drawText(text,dotsX,startY + DisplayUtils.dp2px(mContext,15),mPaint);
            mLinePath.lineTo(dotsX + timeDotsTextWidth/2,startY);
            mLinePath.lineTo(dotsX + timeDotsTextWidth/2,startY-DisplayUtils.dp2px(mContext,5));
            mLinePath.moveTo(dotsX + timeDotsTextWidth/2,startY);
            if(i == 0){
                mDataPath.moveTo(dotsX + timeDotsTextWidth/2,data2value(data.get(i)));
            }
            mDataPath.lineTo(dotsX + timeDotsTextWidth/2,data2value(data.get(i)));
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.parseColor("#EE9A00"));
            canvas.drawCircle(dotsX + timeDotsTextWidth/2,data2value(data.get(i)),DisplayUtils.dp2px(mContext,3),mPaint);
            String value = String.valueOf(data.get(i))+"℃";
            Rect rect2 = new Rect();
            mPaint.getTextBounds(value, 0, value.length(), rect2);
            float valueWidth = rect2.width();
            canvas.drawText(value,dotsX + timeDotsTextWidth/2 - valueWidth/2,data2value(data.get(i))-DisplayUtils.dp2px(mContext,10),mPaint);
            if(i == data.size() - 1 && offset_x_max == 0){
                //初始化最大偏移量
                offset_x_max = dotsX + timeDotsTextWidth/2 - mViewWidth+startX;
                Log.d(TAG, "drawBg: 最大偏移量-->"+offset_x_max);
            }
        }
        mPaint.setStrokeWidth(4f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(baseColor);
        canvas.drawPath(mLinePath,mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setColor(Color.parseColor("#EE9A00"));
        canvas.drawPath(mDataPath,mPaint);
    }

    /**
     * 把实际的天气数据转为 对应的坐标数值
     * @param data
     * @return
     */
    private float data2value(float data){
        if(data > 60){
            data = 60;
        }
        if(data < - 20){
            data = -20;
        }
        //表示每一个数据纵向的长度
        float dataValue = (startY - marginTop) /80;
        if(data > 0){
            return  mViewHeight - (20+data) * dataValue;
        }else {
            return mViewHeight + data * dataValue;
        }
    }

    public WeatherView(Context context) {
        super(context);
        init(context);
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
