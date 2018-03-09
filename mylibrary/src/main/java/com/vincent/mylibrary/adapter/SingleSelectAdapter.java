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
 * @date 2017/12/26 10:28
 */

public class SingleSelectAdapter extends RecyclerView.Adapter<SingleSelectAdapter.SingleSelectViewHolder> {

    private Context mContext;
    private List<SelectEntity> data;
    private CheckBoxOnChangeListener onItemClickListener;

    //默认是否选中第一个
//    private boolean isDefaultSelect = true;
    private int mCurrentSelectPosition;

    /*public void setDefaultSelect(boolean defaultSelect) {
        isDefaultSelect = defaultSelect;
    }*/

    public SingleSelectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SelectEntity> data) {
        this.data = data;
        //设置第一个为选中状态
        /*if(isDefaultSelect){
            data.get(0).setSelect(true);
            mCurrentSelectPosition = 0;
        }else {
            for (int i=0;i<data.size();i++){
                if(data.get(i).isSelect()){
                    mCurrentSelectPosition = i;
                }
            }
        }*/
        for (int i=0;i<data.size();i++) {
            if (data.get(i).isSelect()) {
                mCurrentSelectPosition = i;
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(CheckBoxOnChangeListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SingleSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null) {
            mContext = parent.getContext();
        }
        return new SingleSelectViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_single_select,parent,false));
    }

    @Override
    public void onBindViewHolder(final SingleSelectViewHolder holder, final int position) {
        SelectEntity selectEntity = data.get(position);
        holder.tvContent.setText(selectEntity.getItemContent());
        for (int i=0;i<data.size();i++){
            if(data.get(i).isSelect()){
                //获取上一个选中的position
                mCurrentSelectPosition = i;
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.get(mCurrentSelectPosition).setSelect(false);
                notifyItemChanged(mCurrentSelectPosition);
                mCurrentSelectPosition = position;
                data.get(mCurrentSelectPosition).setSelect(true);
                notifyItemChanged(mCurrentSelectPosition);
                onItemClickListener.onChange(view,holder.cb.isChecked(),mCurrentSelectPosition);
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
        return data == null ?0:data.size();
    }

    class SingleSelectViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cb;
        private TextView tvContent;
        private View line;

        public SingleSelectViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.item_single_select_checkbox);
            tvContent = itemView.findViewById(R.id.item_single_select_content);
            line = itemView.findViewById(R.id.item_single_select_line);
        }
    }
}
