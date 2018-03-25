package com.vincent.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.vincent.mylibrary.R;

import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name FoodMeterial
 * @page com.toncentsoft.food_material.function.adapter
 * @class describe
 * @date 2018/3/21 18:38
 */

public class VersionUpgradeAdapter extends RecyclerView.Adapter<VersionUpgradeAdapter.VersionUpgradeViewHolder> {

    private Context mContext;
    private List<String> data;

    public VersionUpgradeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public VersionUpgradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new VersionUpgradeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_upgrade,parent,false));
    }

    @Override
    public void onBindViewHolder(VersionUpgradeViewHolder holder, int position) {
        if(data.size()>1){
            holder.textView.setText(String.valueOf(position+1)+". "+data.get(position));
        }else {
            holder.textView.setText(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class VersionUpgradeViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public VersionUpgradeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_upgrade_content);
        }
    }
}
