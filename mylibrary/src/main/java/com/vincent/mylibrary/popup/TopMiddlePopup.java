package com.vincent.mylibrary.popup;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.vincent.mylibrary.R;
import com.vincent.mylibrary.adapter.CommonItemOnClickListener;
import com.vincent.mylibrary.adapter.PopupAdapter;
import com.vincent.mylibrary.util.ScreenUtils;
import com.vincent.mylibrary.view.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class TopMiddlePopup extends PopupWindow {

    private Context myContext;
    private RecyclerView rlv;
    private List<String> data = new ArrayList<>();
    private int myWidth;
    private int myHeight;

    //1 表示为左边 2表示为右边
    private int SHOW_ORIENTATION  = 1;


    // 判断是否需要添加或更新列表子类项
    private boolean myIsDirty = true;

    private LayoutInflater inflater = null;
    private View myMenuView;

    private LinearLayout popupLL;

    private PopupAdapter adapter;
    private OnItemClickListener onItemClickListener;

    private int item_click_background_color;
    private DismisOnClickListener dismisOnClickListener;

    public TopMiddlePopup setDismisOnClickListener(DismisOnClickListener dismisOnClickListener) {
        this.dismisOnClickListener = dismisOnClickListener;
        return this;
    }

    /**
     * 设置点击的时候item的背景颜色值
     * @param item_click_background_color
     * @return
     */
    public TopMiddlePopup setItemClickBackgroundColor(int item_click_background_color) {
        this.item_click_background_color = item_click_background_color;
        return this;
    }

    public TopMiddlePopup setBackground_color(int background_color) {
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(background_color);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        return this;
    }

    public TopMiddlePopup setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public TopMiddlePopup setData(List<String> data) {
        this.data = data;
        return this;
    }

    public TopMiddlePopup setOrientation(int SHOW_ORIENTATION) {
        this.SHOW_ORIENTATION = SHOW_ORIENTATION;
        return this;
    }

    public TopMiddlePopup(Context context) {
        // TODO Auto-generated constructor stub
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if(dismisOnClickListener != null){
            dismisOnClickListener.onDismis();
        }else {
            Log.d(getClass().getSimpleName(), "setOnDismissListener: dismisOnClickListener is null");
        }
    }


    public TopMiddlePopup(Context context, List<String> items, int orientation) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myMenuView = inflater.inflate(R.layout.library_pop_layout_top_middle, null);

        this.myContext = context;
        this.data = items;
        this.SHOW_ORIENTATION = orientation;

        this.myWidth = ScreenUtils.getScreenWidth(context);
        this.myHeight = ScreenUtils.getStatusHeight(context);

//        System.out.println("--myWidth--:" + myWidth + "--myHeight--:"
//                + myHeight);
        initWidget();
        setPopup();
    }


    //左边距
    private int leftMargins = 0;

    /**
     * 这里设置的需要转换一下
     * @param leftMargins
     * @return
     */
    public TopMiddlePopup setLeftMargins(int leftMargins) {
        this.leftMargins = leftMargins * 4;
        return this;
    }


    /**
     * 初始化控件
     */
    private void initWidget() {
        rlv =  myMenuView.findViewById(R.id.popup_rlv);
        popupLL =  myMenuView.findViewById(R.id.popup_layout);

        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(myContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
        adapter = new PopupAdapter(myContext);
        adapter.setData(data);
        rlv.setAdapter(adapter);
        adapter.setItemOnClickListener(new CommonItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                view.setBackgroundColor(ContextCompat.getColor(myContext,item_click_background_color));
                adapter.setColorId(item_click_background_color);
                if(onItemClickListener != null){
                    onItemClickListener.setOnItemClick(view,position);
                }
                dismiss();
            }
        });

        android.widget.RelativeLayout.LayoutParams lpPopup = (android.widget.RelativeLayout.LayoutParams) popupLL
                .getLayoutParams();
        //设置宽度
        lpPopup.width = (int) (myWidth / 2);
        //设置边距
//        XLogs.getLogger().d(SHOW_ORIENTATION);
        if(SHOW_ORIENTATION == 1){
            lpPopup.setMargins(leftMargins,0,ScreenUtils.getScreenWidth(myContext)/2,0);
        }else {
            lpPopup.setMargins(ScreenUtils.getScreenWidth(myContext)/2,0,leftMargins,0);
        }
        popupLL.setLayoutParams(lpPopup);
    }

    /**
     * 设置popup的样式
     */
    private void setPopup() {
        // 设置AccessoryPopup的view
        this.setContentView(myMenuView);
        // 设置AccessoryPopup弹出窗体的宽度
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体的高度
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体可点击
        this.setFocusable(true);
        // 设置AccessoryPopup弹出窗体的动画效果
        this.setAnimationStyle(R.style.PopWindowAnimStyle);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x33000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        myMenuView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = popupLL.getBottom();
                int left = popupLL.getLeft();
                int right = popupLL.getRight();
//                System.out.println("--popupLL.getBottom()--:"
//                        + popupLL.getBottom());
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height || x < left || x > right) {
//                        System.out.println("---点击位置在列表下方--");
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {
       /* if (myIsDirty) {
            myIsDirty = false;
            adapter = new PopupAdapter(myContext, myItems, myType);
            myLv.setAdapter(adapter);
        }*/

//        showAtLocation(view, Gravity.BOTTOM,0,0);

        showAsDropDown(view);
    }

    /**
     * 解决Android7.0显示不正常
     * @param anchor
     */
    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24){
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        initWidget();
        super.showAsDropDown(anchor);
    }


    public interface DismisOnClickListener{
        void onDismis();
    }

    public interface OnItemClickListener{
        void setOnItemClick(View view, int position);
    }
}
