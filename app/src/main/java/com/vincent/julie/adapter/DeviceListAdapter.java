package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincent.julie.R;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/26 1:03
 */

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceListViewHolder> {
    private Context mContext;
    private List<DeviceListBean> data;
    private DeviceItemOnClickListener onItemClickListener;

    public DeviceListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<DeviceListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(DeviceItemOnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DeviceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new DeviceListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_device_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DeviceListViewHolder holder, final int position) {
        DeviceListBean deviceListBean = data.get(holder.getAdapterPosition());
        holder.tvName.setText(deviceListBean.getName());
        holder.tvDesc.setText(String.valueOf(deviceListBean.getRunNum())+"/"+String.valueOf(deviceListBean.getTotalNum()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(v,holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class DeviceListViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvDesc;

        public DeviceListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.frg_device_name);
            tvDesc = itemView.findViewById(R.id.frg_device_desc);
        }
    }

    public interface DeviceItemOnClickListener{
        void onItemClick(View view,int position);
    }

}
