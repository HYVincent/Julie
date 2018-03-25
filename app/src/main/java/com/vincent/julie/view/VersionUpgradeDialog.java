package com.vincent.julie.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vincent.mylibrary.R;
import com.vincent.mylibrary.adapter.VersionUpgradeAdapter;
import com.vincent.mylibrary.dialog.BaseDialog;

import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name FoodMeterial
 * @page com.toncentsoft.food_material.view
 * @class describe
 * @date 2018/3/21 17:53
 */

public class VersionUpgradeDialog extends BaseDialog {

    private RecyclerView recyclerView;
    private TextView tvDo;
    private TextView cancel;
    private View vLine;
    private TextView tvTitle;
    private UpgradeClickListener exitLoginClickListener;
    private static final String TAG = VersionUpgradeDialog.class.getSimpleName();
    private VersionUpgradeAdapter adapter;

    public VersionUpgradeDialog setUpgradeClickListener(UpgradeClickListener exitLoginClickListener) {
        this.exitLoginClickListener = exitLoginClickListener;
        return this;
    }

    private boolean isMustUpgrade;

    public VersionUpgradeDialog(@NonNull Context context, boolean isMustUpgrade, String strTitle, final List<String> upgradeDesc) {
        super(context);
        setContentView(R.layout.dialog_layout_version_upgrade);
        recyclerView = findViewById(R.id.dialog_upgrade_tv_desc);
        tvDo = findViewById(R.id.dialog_upgrade_tv_action);
        cancel = findViewById(R.id.dialog_upgrade_tv_cancel);
        vLine = findViewById(R.id.dialog_upgrade_v_line);
        tvTitle = findViewById(R.id.dialog_upgrade_tv_title);
        if(isMustUpgrade){
            Log.d(TAG, "VersionUpgradeDialog: 强制升级。。");
            cancel.setVisibility(View.GONE);
            vLine.setVisibility(View.GONE);
            //以下两行代码这里设置没有效果，需要在外层设置
//            setCancelable(false);
//            setCanceledOnTouchOutside(false);
        }else {
            cancel.setVisibility(View.VISIBLE);
            vLine.setVisibility(View.VISIBLE);
        }
        if(TextUtils.isEmpty(strTitle)){
            strTitle = "发现新版本啦";
        }
        tvTitle.setText(strTitle);
        setMargin(30);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        initRecycleView(context,upgradeDesc);
        tvDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ...................");
                if(exitLoginClickListener != null){
                    exitLoginClickListener.onUpgrade();
                }else {
                    Log.d(TAG, "onClick: exitLoginClickListener is null.");
                }
                dismiss();
            }
        });
    }


    private void initRecycleView(Context context, List<String> upgradeDesc) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new VersionUpgradeAdapter(context);
        adapter.setData(upgradeDesc);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        handler.removeCallbacksAndMessages(null);
    }

    public interface UpgradeClickListener{

        void onUpgrade();

    }

}
