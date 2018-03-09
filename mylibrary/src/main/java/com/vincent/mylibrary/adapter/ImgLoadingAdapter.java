package com.vincent.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.vincent.mylibrary.R;
import com.vincent.mylibrary.util.ImageLoadingUtils;

import java.util.List;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name KangDaEr
 * @class name：com.toncentsoft.kangdaer.ui.adapter
 * @class describe
 * @time 2017/11/19 21:48
 * @change
 * @chang time
 * @class describe
 */

public class ImgLoadingAdapter extends RecyclerView.Adapter<ImgLoadingAdapter.ImgLoadingViewHolder> {

    private List<String> data;
    private Context mContext;

    private CommonItemOnClickListener listener;


    public void setOnItemClickListener(CommonItemOnClickListener listener) {
        this.listener = listener;
    }

    public ImgLoadingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ImgLoadingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new ImgLoadingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.library_item_layout_img_loading,parent,false));
    }

    @Override
    public void onBindViewHolder(ImgLoadingViewHolder holder, final int position) {
//            ImageLoadingUtils.loadIntoUseFitWidth(mContext,data.get(position),0,holder.iv);
        ImageLoadingUtils.loadingImg(mContext, data.get(position),holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ?0:data.size();
    }

    class ImgLoadingViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;

        public ImgLoadingViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView15);
        }
    }

    public interface OnItemClickListener{
        void action();
    }

}
