package com.vincent.julie.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptyRecyclerView extends RecyclerView {
  @Nullable View emptyView;

  public EmptyRecyclerView(Context context) { super(context); }

  public EmptyRecyclerView(Context context, AttributeSet attrs) { super(context, attrs); }

  public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  void checkIfEmpty() {
    if (emptyView != null) {
      emptyView.setVisibility(getAdapter().getItemCount() > 0 ? GONE : VISIBLE);
    }
  }

  final @NotNull
  RecyclerView.AdapterDataObserver observer = new AdapterDataObserver() {
    @Override public void onChanged() {
      super.onChanged();
      checkIfEmpty();
    }
  };

  @Override public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
    final Adapter oldAdapter = getAdapter();
    if (oldAdapter != null) {
      oldAdapter.unregisterAdapterDataObserver(observer);
    }
    super.setAdapter(adapter);
    if (adapter != null) {
      adapter.registerAdapterDataObserver(observer);
    }
  }

  /**
   * 获取某个控件
   * @param id
   * @return
   */
  public View findView(int id){
      return this.emptyView == null?null:this.emptyView.findViewById(id);
  }

  public void setEmptyView(@Nullable View emptyView) {
    this.emptyView = emptyView;
    checkIfEmpty();
  }
}
