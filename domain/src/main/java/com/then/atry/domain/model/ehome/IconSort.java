package com.then.atry.domain.model.ehome;

import com.google.gson.annotations.Expose;

/**
 * Created by then on 2017/1/16.
 */

public class IconSort {

    @Expose
    private  String iconId;

    @Expose
    private int sort;

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }


    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
