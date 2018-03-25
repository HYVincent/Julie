package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincent.julie.R;
import com.vincent.julie.bean.NotifyBean;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/24 17:06
 */

public class NotifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NotifyBean> data;
    private Context mContext;
    // 通知类型  0  物业通知  1 天气状况  2 紧急号码 设置
    private int TYPE_BUILDING_MANAGER = 0;
    private int TYPE_WEATHER = 1;
    private int TYPE_SOS_NUMBER = 2;

    public NotifyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<NotifyBean> data) {
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
        if(viewType == TYPE_BUILDING_MANAGER){
            holder = new BuildingManagerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_notify_build,parent,false));
        }else if(viewType == TYPE_WEATHER){
            holder = new WeatherViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_notify_weather,parent,false));
        }else if(viewType == TYPE_SOS_NUMBER){
            holder = new SosNumberViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_notify_sos,parent,false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotifyBean bean = data.get(position);
        if(bean.getType() == TYPE_BUILDING_MANAGER){
            setBuildingNotifyData((BuildingManagerViewHolder)holder,bean);
        }else if(bean.getType() == TYPE_WEATHER){
            setWeatherData((WeatherViewHolder)holder,bean);
        }else if(bean.getType() == TYPE_SOS_NUMBER){
            setSosData((SosNumberViewHolder)holder,bean);
        }
    }

    /**
     * 设置紧急号码
     * @param holder
     * @param bean
     */
    private void setSosData(SosNumberViewHolder holder, NotifyBean bean) {

    }

    /**
     * 设置天气数据
     * @param holder
     * @param bean
     */
    private void setWeatherData(WeatherViewHolder holder, NotifyBean bean) {

    }

    /**
     * 设置物业数据
     * @param holder
     * @param bean
     */
    private void setBuildingNotifyData(BuildingManagerViewHolder holder, NotifyBean bean) {

    }

    @Override
    public int getItemViewType(int position) {
        NotifyBean notifyBean = data.get(position);
        switch (notifyBean.getType()){
            case 0:
                return TYPE_BUILDING_MANAGER;
            case 1:
                return TYPE_WEATHER;
            case 2:
                return TYPE_SOS_NUMBER;
            default:break;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    /**
     * 物业
     */
    class BuildingManagerViewHolder extends RecyclerView.ViewHolder{

        public BuildingManagerViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 天气
     */
    class WeatherViewHolder extends RecyclerView.ViewHolder{

        public WeatherViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 紧急号码
     */
    class SosNumberViewHolder extends RecyclerView.ViewHolder{

        public SosNumberViewHolder(View itemView) {
            super(itemView);
        }
    }
}
