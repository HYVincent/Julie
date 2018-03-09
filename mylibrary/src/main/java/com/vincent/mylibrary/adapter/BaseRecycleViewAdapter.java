package com.vincent.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.toncentsoft.starkangmedical_android.adapter
 * @class describe
 * @date 2018/1/4 17:23
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewAdapter.BaseRecycleViewHolder> {

    protected Context mContext;
    protected abstract int setView();

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new BaseRecycleViewHolder(LayoutInflater.from(mContext).inflate(setView(),parent,false));
    }

    class BaseRecycleViewHolder extends RecyclerView.ViewHolder{

        public BaseRecycleViewHolder(View itemView) {
            super(itemView);
            initWidget(itemView);
        }
    }

    protected abstract void initWidget(View view);

}
