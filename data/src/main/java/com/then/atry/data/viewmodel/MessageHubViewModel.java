package com.then.atry.data.viewmodel;

import android.databinding.Bindable;
import com.kymjs.themvp.viewmodel.BaseViewModel;
import com.then.atry.data.BR;

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
