package com.then.atry.viewmodel.listitem;

import android.databinding.Bindable;
import com.kymjs.themvp.viewmodel.BaseViewModel;
import com.then.atry.BR;

/**
 * Created by then on 2017/2/6.
 */

public class MessageHubViewModel extends BaseViewModel {

    @Bindable
    private  String title;

    @Bindable
    private int  countdown;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
        notifyPropertyChanged(BR.countdown);
    }
}
