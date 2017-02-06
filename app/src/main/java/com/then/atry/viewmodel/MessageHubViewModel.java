package com.then.atry.viewmodel;

import android.databinding.Bindable;
import com.then.atry.BR;
import com.kymjs.themvp.viewmodel.BaseViewModel;

/**
 * Created by then on 2017/2/6.
 */

public class MessageHubViewModel extends BaseViewModel {

    @Bindable
    private  String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
