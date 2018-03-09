package com.vincent.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.mylibrary.R;
import com.vincent.mylibrary.adapter.MultipleSelectAdapter;
import com.vincent.mylibrary.adapter.OnItemClickListener;
import com.vincent.mylibrary.entity.SelectEntity;
import com.vincent.mylibrary.util.MyToastUtil;
import com.vincent.mylibrary.view.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog.util
 * @class describe 多选对话框
 * @date 2017/12/26 10:02
 */

public class MultipleSelectDialog extends BaseDialog {

    private RecyclerView rlv;
    private LinearLayout llCancel,llOk;
    private TextView tvCancel,tvTitle,tvOk;
    private String cancelText = "取消";
    private String titleText;
    private String okText= "确定";
    private List<SelectEntity> data = new ArrayList<>();
    private MultipleSelectAdapter adapter;
    private MultipleSelectResultOnClickListener multipleSelectResultOnClickListener;
    private List<String> resultData = new ArrayList<>();

    public MultipleSelectDialog(@NonNull Context context) {
        super(context);
    }

    public MultipleSelectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_multiple_select);
        rlv = findViewById(R.id.multiple_select_rlv);
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
                if(resultData.size()!=0){
                    multipleSelectResultOnClickListener.result(resultData);
                    dismiss();
                }else {
                    MyToastUtil.showMsg("请选择");
                }
            }
        });
        initRecycleView();
    }

    private void initRecycleView() {
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
        adapter = new MultipleSelectAdapter(getContext());
        adapter.setData(data);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SelectEntity selectEntity = data.get(position);
                if(selectEntity.isSelect()){
                    if(!resultData.contains(selectEntity.getItemContent())){
                        resultData.add(selectEntity.getItemContent());
                    }
                }else {
                    if(resultData.contains(selectEntity.getItemContent())){
                        resultData.remove(selectEntity.getItemContent());
                    }
                }
            }
        });
    }

    public MultipleSelectDialog setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public MultipleSelectDialog setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    public MultipleSelectDialog setOkText(String okText) {
        this.okText = okText;
        return this;
    }

    public MultipleSelectDialog setData(List<SelectEntity> data) {
        this.data = data;
        return this;
    }

    public MultipleSelectDialog setMultipleSelectResultOnClickListener(MultipleSelectResultOnClickListener multipleSelectResultOnClickListener) {
        this.multipleSelectResultOnClickListener = multipleSelectResultOnClickListener;
        return this;
    }

    public interface MultipleSelectResultOnClickListener{
        void result(List<String> data);
    }

}
