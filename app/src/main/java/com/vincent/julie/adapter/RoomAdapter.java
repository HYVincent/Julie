package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.bean.RoomBean;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/26 0:08
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context mContext;
    private List<RoomBean> data;
    private RoomOnClickListener itemOnClickListener;

    public RoomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<RoomBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemOnClickListener(RoomOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new RoomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_room,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, final int position) {
        RoomBean roomBean = data.get(position);
        holder.tvRoomName.setText(roomBean.getRoomName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOnClickListener != null){
                    itemOnClickListener.onClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }


    class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvRoomName;
        public RoomViewHolder(View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.item_room_name);
        }
    }

    public interface RoomOnClickListener{
        void onClick(View view,int position);
    }

}
