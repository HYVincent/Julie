package com.vincent.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.mylibrary.R;
import com.vincent.mylibrary.adapter.CheckBoxOnChangeListener;
import com.vincent.mylibrary.adapter.SingleSelectAdapter;
import com.vincent.mylibrary.entity.SelectEntity;
import com.vincent.mylibrary.util.ScreenUtils;
import com.vincent.mylibrary.view.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog
 * @class describe 单选dialog
 * @date 2017/12/26 9:59
 */

public class SingleSelectDialog extends BaseDialog {

    private RecyclerView rlv;
    private LinearLayout llCancel,llOk;
    private TextView tvCancel,tvTitle,tvOk;
    private String cancelText = "取消";
    private String titleText;
    private String okText= "确定";
    private SingleSelectAdapter adapter;
    private List<SelectEntity> data = new ArrayList<>();
    private String resultStr;//选择的结果
    private SelectResultOnListener selectResultOnListener;
    private static final String TAG = SingleSelectDialog.class.getSimpleName();
    private View view;

    public SingleSelectDialog(@NonNull Context context) {
        super(context);
    }

    public SingleSelectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_single_select);
        rlv = findViewById(R.id.single_select_rlv);
        tvCancel = findViewById(R.id.dialog_list_buttom_tv_left);
        tvCancel.setText(cancelText);
        tvTitle = findViewById(R.id.dialog_list_buttom_tv_center);
        tvTitle.setText(titleText);
        tvOk = findViewById(R.id.dialog_list_buttom_tv_right);
        tvOk.setText(okText);
        llCancel = findViewById(R.id.dialog_list_buttom_ll_left);
        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        llOk = findViewById(R.id.dialog_list_buttom_ll_right);
        llOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+resultStr);
                if(TextUtils.isEmpty(resultStr)){
                    for (int i = 0;i<data.size();i++){
                        if(data.get(i).isSelect()){
                            resultStr = data.get(i).getItemContent();
                        }
                    }
                }
                selectResultOnListener.onResult(resultStr);
                dismiss();
            }
        });
        setShowBottom(true);
//        setMargin(15);
        setHeight(ScreenUtils.getScreenHeight(getContext())/2);
        initRecycleView();
    }

    private void initRecycleView() {
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
        adapter = new SingleSelectAdapter(getContext());
        adapter.setData(data);
//        adapter.setDefaultSelect(true);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new CheckBoxOnChangeListener() {
            @Override
            public void onChange(View view, boolean status, int position) {
                Log.d(TAG, "onChange: position-->"+String.valueOf(position));
                resultStr = data.get(position).getItemContent();
            }
        });
    }

    /**
     * 测量这个view
     * 最后通过getMeasuredWidth()获取宽度和高度.
     *
     * @param view 要测量的view
     * @return 测量过的view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    @Override
    public void show() {
        super.show();
        /*if(getViewHeight(getLayoutInflater())> ScreenUtils.getScreenHeight(getContext())/2){
            setHeight(ScreenUtils.getScreenHeight(getContext())/2);
        }else {
            Log.d(TAG, "show: 没有获取到view高度");
        }*/
    }


    /**
     * 获得这个View的宽度
     * 测量这个view，最后通过getMeasuredWidth()获取宽度.
     *
     * @param view 要测量的view
     * @return 测量过的view的宽度
     */
    protected int getViewWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }

    /**
     * 获得这个View的高度
     * 测量这个view，最后通过getMeasuredHeight()获取高度.
     *
     * @param view 要测量的view
     * @return 测量过的view的高度
     */
    protected int getViewHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }

    public SingleSelectDialog setData(List<SelectEntity> data) {
        this.data.clear();
        this.data.addAll(data);
        return this;
    }

    public SingleSelectDialog setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public SingleSelectDialog setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    public SingleSelectDialog setOkText(String okText) {
        this.okText = okText;
        return this;
    }

    public interface SelectResultOnListener{
        void onResult(String result);
    }

    public SingleSelectDialog setSelectResultOnListener(SelectResultOnListener selectResultOnListener) {
        this.selectResultOnListener = selectResultOnListener;
        return this;
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        data.clear();
    }
}
