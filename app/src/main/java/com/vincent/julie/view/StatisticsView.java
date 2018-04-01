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
import android.view.View;

import com.vincent.mylibrary.util.DensityUtil;
import com.vincent.mylibrary.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name CustomView
 * @page com.vincent.custom.custom_view
 * @class describe 折线图 每月电费
 * @date 2018/3/29 22:39
 */
public class StatisticsView extends View {
    private static final String TAG = StatisticsView.class.getSimpleName();
    private Context mContext;
    private float viewWidth;
    private float viewHeight;
    private int mBackgroundColor = Color.parseColor("#7A7A7A");
    //左边内边距 这里单位按照dp来算，注意转换
    private float mMarginLeft = 10;
    //底部内边距 这里单位按照dp来算，注意转换
    private float mMarginButtom = 10;
    //这是左边的标签值的宽度
    private float mTagValueWidth = 0;
    //这个图标的原点左边 两条线交汇的点
    private float mStartX = 0;
    //maxValue 最大值为100
    private float maxValue = 100;
    //间隔值
    private float intervalValue = 10;
    //每次的递增值
    private float addValue = maxValue/intervalValue;
    //View的顶部留一部分控件不绘制
    private float mTopUnused = 30;
    private float mRightUnused = 10;

    private Paint mPaint;
    //左边文字的宽度
    private float mTagValueTextWidth = 0;
    private int mTagValueTextColor = Color.parseColor("#f0f0f0");
    private int mTagValueTextSize = 14;
    private int mButtomTextSize = 12;
    //默认数据的个数
    private int dataNum = 10;
    private List<Float> data = new ArrayList<>();
    //这是 x 方向 各个点的间距
    private float itemDataIntervalX = 0;
    //这是Y 方向的 间距
    private float intervalView = 0;
    //数据起点x
    private float mDataStartX = 0;
    //数据起点y
    private float mDataStartY = 0;
    //路径
    private Path mDataPath;
    //左边竖线的X坐标
    private float mLeftLineStartX = 0;

    /**
     * 添加数据
     * @param data
     */
    public void addListData(List<Float> data){
        this.data  = data;
        if(this.data.size() >dataNum){
            dataNum = this.data.size();
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mBackgroundColor);
        //绘制左边的
        drawLeftValue(canvas);
        drawDatta(canvas);
    }

    /**
     * 数据和坐标值的转换
     * @param values
     * @return
     */
    private float dataToY(float values){
        return viewHeight - values/100 * ((viewHeight - DisplayUtils.dp2px(mContext,30))-mTopUnused)-DisplayUtils.dp2px(mContext,30);
    }

    /**
     * 绘制数据
     * @param canvas
     */
    private void drawDatta(Canvas canvas) {
        mPaint.setStrokeWidth(3f);
        mPaint.setColor(Color.parseColor("#FFC125"));
        float startX = mLeftLineStartX + DisplayUtils.dp2px(mContext,5);
        if(data.size() == 0){
            return;
        }
        mDataPath.moveTo(startX,dataToY(data.get(0)));
        //描边和填充内部
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0;i<data.size();i++){
            mDataPath.lineTo((mLeftLineStartX + DisplayUtils.dp2px(mContext,5))+ i * itemDataIntervalX,dataToY(data.get(i)));
            canvas.drawCircle((mLeftLineStartX + DisplayUtils.dp2px(mContext,5))+ i * itemDataIntervalX,dataToY(data.get(i)),DisplayUtils.dp2px(mContext,3),mPaint);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mDataPath,mPaint);
    }




    //绘制左边的标签值
    private void drawLeftValue(Canvas canvas) {
        float mStartX = mMarginLeft;
        float mStartY = viewHeight - DisplayUtils.dp2px(mContext,30);
        mPaint.setTextSize(mTagValueTextSize);
        //定义的View的间隔
        intervalView = (mStartY - mTopUnused)/addValue;
        for(int i = 0;i<addValue;i++){
            if(i == 0){
                Rect rectText = new Rect();
                mPaint.getTextBounds(String.valueOf(0), 0, String.valueOf(0).length(), rectText);
                mStartX = mStartX + DisplayUtils.dp2px(mContext,rectText.width()/4);
            }else {
                mStartX = mMarginLeft;
            }
            canvas.drawText(String.valueOf((int) (i * addValue))+"°",mStartX,mStartY - intervalView * i,mPaint);
            if(i == addValue - 1){
                mTagValueTextWidth =  mPaint.measureText(String.valueOf((int) (i * addValue)),0,String.valueOf((int) (i * addValue)).length());
            }
        }
        mPaint.setColor(Color.parseColor("#686868"));
        //左边的竖线的x坐标
        mLeftLineStartX = mStartX + mTagValueTextWidth+DisplayUtils.dp2px(mContext,5);
        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext,1));
        //绘制左边的竖线
        canvas.drawLine(mLeftLineStartX,mStartY,mStartX + mTagValueTextWidth+DisplayUtils.dp2px(mContext,5),
                mTopUnused,mPaint);
        //设置横线的颜色
        mPaint.setColor(Color.parseColor("#686868"));
        mPaint.setTextSize(mButtomTextSize);
        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext,1));
//        canvas.drawLine(mLeftLineStartX,mStartY,viewWidth-mRightUnused,mStartY,mPaint);
        for (int i = 0;i< addValue;i++){
            canvas.drawLine(mLeftLineStartX,mStartY - intervalView * i,viewWidth - mRightUnused,mStartY - intervalView * i,mPaint);
        }
        mPaint.setTextSize(DisplayUtils.dp2px(mContext,8));
        // 留出10dp空白 contentWidth 内容宽度
        float contentWidth = viewWidth -mLeftLineStartX- mRightUnused - DisplayUtils.dp2px(mContext,5);
        //各个数据之间的间隔
        itemDataIntervalX =  contentWidth / dataNum;
        mDataStartX = (mLeftLineStartX + DisplayUtils.dp2px(mContext,5));
        for ( int i = 0;i< dataNum;i++){
            canvas.drawText(String.valueOf(i+1),(mLeftLineStartX + DisplayUtils.dp2px(mContext,5))+itemDataIntervalX * i,
                    mStartY +DisplayUtils.dp2px(mContext,15),mPaint);
        }
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        //把 dp转为px
        mMarginButtom = DisplayUtils.dp2px(context,mMarginButtom);
        mMarginLeft = DisplayUtils.dp2px(context,mMarginLeft);
        Log.d(TAG, "init: mMarginButtom->"+mMarginButtom+",mMarginLeft->"+mMarginLeft);
        mTagValueTextSize = DisplayUtils.dp2px(context,mTagValueTextSize);
        mTopUnused = DisplayUtils.dp2px(context,mTopUnused);
        mButtomTextSize = DisplayUtils.dp2px(mContext,mButtomTextSize);
        mRightUnused = DisplayUtils.dp2px(mContext,mRightUnused);

        mPaint = new Paint();
        mPaint.setStrokeWidth(4f);
        mPaint.setAntiAlias(true);

        mDataPath = new Path();
    }

    public StatisticsView(Context context) {
        super(context);
        init(context);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

}
