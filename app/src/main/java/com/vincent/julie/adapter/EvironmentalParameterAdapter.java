package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.bean.EvironmentalParameterBean;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/31 13:05
 */
public class EvironmentalParameterAdapter extends RecyclerView.Adapter<EvironmentalParameterAdapter.EvironmentalParameterViewHolder> {
    private Context mContext;
    private List<EvironmentalParameterBean> data;

    public EvironmentalParameterAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<EvironmentalParameterBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EvironmentalParameterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new EvironmentalParameterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_evironmental_parameter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EvironmentalParameterViewHolder holder, int position) {
        EvironmentalParameterBean bean = data.get(position);
        holder.tvRoomName.setText(bean.getRoomName());
        // 温度 0  湿度 2 光照 3 pm25 4 噪音 5
        switch (bean.getType()){
            case 0:
                holder.tvType.setText("温度");
                holder.tvValue.setText(String.valueOf(bean.getTemperature())+"°");
                holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.environmental_parameter_icon_temperature));
                break;
            case 1:
                holder.tvType.setText("湿度");
                holder.tvValue.setText(String.valueOf(bean.getHumidity())+"%");
                holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.environmental_parameter_icon_humidity));
                break;
            case 2:
                holder.tvType.setText("光照");
                if(bean.getIllumination()>50){
                    holder.tvValue.setText("明亮");
                }else {
                    holder.tvValue.setText("昏暗");
                }
                holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.environmental_parameter_icon_illumination));
                break;
            case 3:
                holder.tvType.setText("PM2.5");
                if(bean.getPm25()>50){
                    holder.tvValue.setText("污染");
                }else {
                    holder.tvValue.setText("良好");
                }
                holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.environmental_parameter_icon_pm25));
                break;
            case 4:
                holder.tvType.setText("噪音");
                if(bean.getNoise()>50){
                    holder.tvValue.setText("喧闹");
                }else {
                    holder.tvValue.setText("安静");
                }
                holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.environmental_parameter_icon_noise));
                break;
            default:break;
        }
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class EvironmentalParameterViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivIcon;
        private TextView tvRoomName,tvType,tvValue;

        public EvironmentalParameterViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.item_evironmental_parameter_tag);
            tvRoomName = itemView.findViewById(R.id.item_evironmental_parameter_room_type);
            tvType = itemView.findViewById(R.id.item_evironmental_parameter_type);
            tvValue = itemView.findViewById(R.id.item_evironmental_parameter_value);
        }
    }
}
