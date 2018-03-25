package com.vincent.julie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.bean.SceneBean;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/25 21:25
 */

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.SceneViewHolder>{
    private static final String TAG = SceneAdapter.class.getSimpleName();
    private Context mContext;
    private List<SceneBean> data;
    private SceneOnClickListener OnItemClickListener;

    public SceneAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SceneBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(SceneOnClickListener onItemClickListener) {
        OnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SceneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        return new SceneViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_scene,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SceneViewHolder holder, final int position) {
        SceneBean bean  = data.get(position);
        holder.tvName.setText(bean.getName());
        holder.ivTag.setImageDrawable(ContextCompat.getDrawable(mContext,bean.getImgId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OnItemClickListener != null){
                    OnItemClickListener.onClickItem(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class SceneViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private ImageView ivTag;

        public SceneViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_scene_tv_name);
            ivTag = itemView.findViewById(R.id.item_scene_iv_icon);
        }
    }

    public interface SceneOnClickListener{

        void onClickItem(View view,int position);

    }

}
