package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.bean.ManagerNotifyBean;
import com.vincent.mylibrary.util.DateUtils;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/5/3 23:18
 */

public class ManagerNotifyAdapter extends RecyclerView.Adapter<ManagerNotifyAdapter.ManagerNotifyViewHolder> {
    private Context mContext;
    private List<ManagerNotifyBean> data;

    public ManagerNotifyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<ManagerNotifyBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ManagerNotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null)
            mContext = parent.getContext();
        return new ManagerNotifyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_manager_notify,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerNotifyViewHolder holder, int position) {
        ManagerNotifyBean bean = data.get(position);
        holder.tvTime.setText(DateUtils.getDateString(DateUtils.DATE_FORMAT_YEAR_MONTH_DAY,bean.getNotifyTime()));
        holder.tvTitle.setText(bean.getTitle());
        holder.tvDesc.setText(bean.getDesc());
    }

    @Override
    public int getItemCount() {
        return data == null ?0:data.size();
    }

    class ManagerNotifyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle,tvDesc,tvTime;
        public ManagerNotifyViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.item_manager_notify_time);
            tvTitle = itemView.findViewById(R.id.item_manager_notify_title);
            tvDesc = itemView.findViewById(R.id.item_manager_notify_desc);
        }
    }
}
