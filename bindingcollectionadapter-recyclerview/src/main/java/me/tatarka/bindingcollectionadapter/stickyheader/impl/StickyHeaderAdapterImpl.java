package me.tatarka.bindingcollectionadapter.stickyheader.impl;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.stickyheader.StickyHeaderAdapter;

/**
 * Created by then on 2016/11/24.
 */

public class StickyHeaderAdapterImpl<T> extends BindingRecyclerViewAdapter<T> implements StickyHeaderAdapter {


    private LayoutInflater inflater;

    private ViewHolderFactory viewHolderFactory;

    @LayoutRes
    private int headerLayoutRes;

    public StickyHeaderAdapterImpl(@NonNull ItemViewArg<T> arg) {
        super(arg);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup viewGroup) {
        return super.onCreateBinding(inflater, layoutId, viewGroup);
    }

    @Override
    public void onBindBinding(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes, int position, T item) {
        super.onBindBinding(binding, bindingVariable, layoutRes, position, item);
    }

    @Override
    public long getHeaderId(int position) {
        return getAdapterItem(position).toString().hashCode();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerLayoutRes) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        this.headerLayoutRes=headerLayoutRes;
        ViewDataBinding binding = onHeaderCreateBinding(inflater, headerLayoutRes, parent);
        final RecyclerView.ViewHolder viewHolder = onCreateViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position,int bindingVariable) {
        T item = getAdapterItem(position);
        ViewDataBinding binding = DataBindingUtil.getBinding(viewHolder.itemView);
        onBindBinding(binding, bindingVariable, headerLayoutRes, position, item);
    }

    @Override
    public ViewDataBinding onHeaderCreateBinding(LayoutInflater inflater, @LayoutRes int layoutRes, ViewGroup viewGroup) {
        return DataBindingUtil.inflate(inflater, layoutRes, viewGroup, false);
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewDataBinding binding) {
        if (viewHolderFactory != null) {
            return viewHolderFactory.createViewHolder(binding);
        } else {
            return new BindingStickyHeaderViewHolder(binding);
        }
    }

    @Override
    public void setViewHolderFactory(ViewHolderFactory viewHolderFactory) {
        this.viewHolderFactory = viewHolderFactory;
    }

    private static class BindingStickyHeaderViewHolder extends RecyclerView.ViewHolder {
        public BindingStickyHeaderViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }
    }

}
