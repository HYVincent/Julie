package com.vincent.mylibrary.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.vincent.mylibrary.R;
import com.vincent.mylibrary.entity.SelectEntity;

import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog.adapter
 * @class describe
 * @date 2017/12/26 14:15
 */

public class MultipleSelectAdapter extends RecyclerView.Adapter<MultipleSelectAdapter.MultipleSelectViewHolder> {

    private Context mContext;
    private List<SelectEntity> data;
    private OnItemClickListener onItemClickListener;

    public MultipleSelectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SelectEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MultipleSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new MultipleSelectViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_multiple_select,parent,false));
    }

    @Override
    public void onBindViewHolder(final MultipleSelectViewHolder holder, final int position) {
        final SelectEntity selectEntity = data.get(position);
        holder.tvContent.setText(selectEntity.getItemContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectEntity.isSelect()){
                    selectEntity.setSelect(false);
                    holder.cb.setChecked(false);
                }else {
                    selectEntity.setSelect(true);
                    holder.cb.setChecked(true);
                }
                onItemClickListener.onItemClick(view,position);
            }
        });
        if(selectEntity.isSelect()){
            holder.cb.setChecked(true);
            holder.tvContent.setTextColor(ContextCompat.getColor(mContext,R.color.color_black_333333));
        }else {
            holder.cb.setChecked(false);
            holder.tvContent.setTextColor(ContextCompat.getColor(mContext,R.color.color_gray_999999));
        }
        if(position == data.size() -1){
            holder.line.setVisibility(View.GONE);
        }else {
            holder.line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }


    class MultipleSelectViewHolder extends RecyclerView.ViewHolder{

        private View line;
        private TextView tvContent;
        private CheckBox cb;

        public MultipleSelectViewHolder(View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.item_multiple_select_line);
            tvContent = itemView.findViewById(R.id.item_multiple_select_content);
            cb = itemView.findViewById(R.id.item_multiple_select_checkbox);
        }
    }
}
