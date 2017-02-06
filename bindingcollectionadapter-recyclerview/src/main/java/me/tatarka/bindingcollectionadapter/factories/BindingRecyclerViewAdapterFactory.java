package me.tatarka.bindingcollectionadapter.factories;

import android.support.v7.widget.RecyclerView;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.stickyheader.impl.StickyHeaderAdapterImpl;

public interface BindingRecyclerViewAdapterFactory {
    <T> BindingRecyclerViewAdapter<T> create(RecyclerView recyclerView, ItemViewArg<T> arg);

    BindingRecyclerViewAdapterFactory DEFAULT = new BindingRecyclerViewAdapterFactory() {
        @Override
        public <T> BindingRecyclerViewAdapter<T> create(RecyclerView recyclerView, ItemViewArg<T> arg) {
            return new BindingRecyclerViewAdapter<>(arg);
        }
    };

    BindingRecyclerViewAdapterFactory STICKY_HEADER = new BindingRecyclerViewAdapterFactory() {
        @Override
        public <T> BindingRecyclerViewAdapter<T> create(RecyclerView recyclerView, ItemViewArg<T> arg) {
            StickyHeaderAdapterImpl stickyHeaderAdapter= new StickyHeaderAdapterImpl<>(arg);
            return stickyHeaderAdapter;
        }
    };



}
