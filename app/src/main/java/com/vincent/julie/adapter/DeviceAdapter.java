package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.bean.DeviceBean;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/25 0:48
 */

public class DeviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DeviceBean> data;
    //设备类型   类型 0 灯光 1 冰箱 2 插座开关 3 电源开关状态
    private int deviceType;

    public DeviceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<DeviceBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        RecyclerView.ViewHolder holder = null;
        if(viewType == 0){
            holder = new LightViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_light,parent,false));
        }else  if(viewType == 1){
            holder = new RefrigeratorViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_refrigerator,parent,false));
        } if(viewType == 2){
            holder = new SocketViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_socket,parent,false));
        } if(viewType == 3){
            holder = new HousPowerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_power,parent,false));
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeviceBean deviceBean = data.get(position);
        if(deviceBean.getType() == 0){
            setLightData((LightViewHolder)holder,data);
        }else if(deviceBean.getType() == 1){
            setRefrigeratorData((RefrigeratorViewHolder)holder,data);
        }else if(deviceBean.getType() == 2){
            setSocketData((SocketViewHolder)holder,data);
        }else if(deviceBean.getType() == 3){
            setHousData((HousPowerViewHolder)holder,data);
        }
    }

    /**
     * 设置总电源开关
     * @param holder
     * @param data
     */
    private void setHousData(HousPowerViewHolder holder, List<DeviceBean> data) {

    }

    /**
     * 设置插座开关
     * @param holder
     * @param data
     */
    private void setSocketData(SocketViewHolder holder, List<DeviceBean> data) {

    }

    /**
     * 设置冰箱温度
     * @param holder
     * @param data
     */
    private void setRefrigeratorData(RefrigeratorViewHolder holder, List<DeviceBean> data) {

    }

    /**
     * 开关灯光
     * @param holder
     * @param data
     */
    private void setLightData(LightViewHolder holder, List<DeviceBean> data) {

    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    /**
     * 灯光类 展示图标不同
     */
    class LightViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivTag;
        private TextView tvTag;
        private Switch mSwitch;

        public LightViewHolder(View itemView) {
            super(itemView);
            ivTag = itemView.findViewById(R.id.item_light_tag_iv);
            tvTag = itemView.findViewById(R.id.item_light_title_tv);
            mSwitch = itemView.findViewById(R.id.item_light_switch);
        }
    }

    /**
     * 冰箱
     */
    class RefrigeratorViewHolder extends RecyclerView.ViewHolder{

        public RefrigeratorViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 插座开关
     */
    class SocketViewHolder extends RecyclerView.ViewHolder{

        public SocketViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 总电源开关
     */
    class HousPowerViewHolder extends RecyclerView.ViewHolder{

        public HousPowerViewHolder(View itemView) {
            super(itemView);
        }
    }


}
