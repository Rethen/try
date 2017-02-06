package com.then.atry.data.viewmodel;

import android.databinding.Bindable;

import com.then.atry.data.BR;
import com.kymjs.themvp.viewmodel.BaseViewModel;

/**
 * Created by then on 2016/12/14.
 */

public class MessageHubViewModel extends BaseViewModel {


    @Bindable
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

}
