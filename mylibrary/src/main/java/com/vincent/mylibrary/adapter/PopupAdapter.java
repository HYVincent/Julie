package com.vincent.mylibrary.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.vincent.mylibrary.R;

import java.util.List;

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupHolder> {

    private Context myContext;
    private List<String> data;
    private CommonItemOnClickListener itemOnClickListener;
    private int colorId = 0;

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public PopupAdapter(Context context) {
        this.myContext = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemOnClickListener(CommonItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }


    @Override
    public PopupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(myContext == null){
            myContext = parent.getContext();
        }
        return new PopupHolder(LayoutInflater.from(myContext).inflate(R.layout.library_item_layout_pop,parent,false));
    }

    @Override
    public void onBindViewHolder(final PopupHolder holder, final int position) {
        holder.itemNameTv.setText(data.get(position));
        if(position == data.size() -1 ){
            holder.tvLine.setVisibility(View.GONE);
        }else {
            holder.tvLine.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorId == 0){
                    colorId = ContextCompat.getColor(myContext,R.color.color_white_ffffff);
                }else {
                    holder.itemView.setBackgroundColor(colorId);
                }
                itemOnClickListener.onItemClick(v,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data == null ?0:data.size();
    }


     class PopupHolder extends RecyclerView.ViewHolder{

        private TextView itemNameTv,tvLine;

         public PopupHolder(View itemView) {
             super(itemView);
             itemNameTv = itemView.findViewById(R.id.popup_tv);
             tvLine = itemView.findViewById(R.id.item_tv_line);
         }
     }

}