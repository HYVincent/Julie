
package com.vincent.mylibrary.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
  *引导页适配器
  *@author Vincent QQ1032006226
  *created at 2016/9/26 8:58
  */
public class ViewPagerAdapter extends PagerAdapter {

    private final ArrayList<View> mViews;

    public ViewPagerAdapter(ArrayList<View> views) {
        mViews = views;
    }

    // 返回页面数目
    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        }
        return 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    // 初始化position位置的页面
    @Override
    public Object instantiateItem(View view, int position) {
        ((ViewPager)view).addView(mViews.get(position), 0);
        return mViews.get(position);
    }

    // 判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    // 销毁position位置的界面
    @Override
    public void destroyItem(View view, int position, Object arg2) {
        ((ViewPager)view).removeView(mViews.get(position));
    }
}
