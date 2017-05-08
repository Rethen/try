package com.then.atry.viewmodel;


import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableList;

import com.kymjs.themvp.viewmodel.BaseViewModel;
import com.then.atry.data.BR;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;


/**
 * Created by evan on 5/31/15.
 */
public class ListViewModel extends BaseViewModel  {


    /**
     * 列表数据
     */
    @Bindable
    private ObservableList<? extends Observable> items;

    /**
     * 列表子项布局
     */
    @Bindable
    private ItemBinding itemBinding;


    @Bindable
    private  int gridSpanCount=1;



    public ListViewModel(ObservableList<? extends Observable> items, int layoutId,int BRId) {
        itemBinding = ItemBinding.of(BRId, layoutId);
        this.items = items;
    }

    public ListViewModel(ObservableList<? extends Observable> items, OnItemBind onItemBind) {
        itemBinding = ItemBinding.of(onItemBind);
        this.items = items;
    }

    public void setItems(ObservableList<? extends Observable> items) {
        this.items = items;
        notifyPropertyChanged(BR.items);
    }

    public ObservableList<? extends Observable> getItems() {
        return items;
    }




    public int getGridSpanCount() {
        return gridSpanCount;
    }

    public void setGridSpanCount(int gridSpanCount) {
        this.gridSpanCount = gridSpanCount;
        notifyPropertyChanged(BR.gridSpanCount);
    }


    public ItemBinding getItemBinding() {
        return itemBinding;
    }

    public void setItemBinding(ItemBinding itemBinding) {
        this.itemBinding = itemBinding;
        notifyPropertyChanged(BR.itemBinding);
    }
}