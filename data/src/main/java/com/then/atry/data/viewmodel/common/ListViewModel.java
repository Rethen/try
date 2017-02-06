package com.then.atry.data.viewmodel.common;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableList;

import com.then.atry.data.BR;
import com.kymjs.themvp.viewmodel.BaseViewModel;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

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
    private ItemViewArg itemView;


    @Bindable
    private  int gridSpanCount=1;



    public ListViewModel(ObservableList<? extends Observable> items, int layoutId,int BRId) {
        itemView = ItemViewArg.of(ItemView.of(BRId, layoutId));
        this.items = items;
    }

    public ListViewModel(ObservableList<? extends Observable> items, ItemViewSelector selector) {
        itemView = ItemViewArg.of(selector);
        this.items = items;
    }

    public void setItems(ObservableList<? extends Observable> items) {
        this.items = items;
        notifyPropertyChanged(BR.items);
    }

    public ObservableList<? extends Observable> getItems() {
        return items;
    }

    public ItemViewArg getItemView() {
        return itemView;
    }


    public int getGridSpanCount() {
        return gridSpanCount;
    }

    public void setGridSpanCount(int gridSpanCount) {
        this.gridSpanCount = gridSpanCount;
        notifyPropertyChanged(BR.gridSpanCount);
    }


}
