package com.then.atry.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.kymjs.themvp.databind.DataBindFragment;
import com.kymjs.themvp.view.IDelegate;
import com.then.atry.application.App;
import com.then.atry.internal.di.components.ApplicationComponent;

/**
 * Created by then on 2016/12/29.
 */

public abstract class BaseFragment<T extends IDelegate, D extends ViewDataBinding> extends DataBindFragment<T,D> {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    protected ApplicationComponent getAppComponment() {
        return ((App)getActivity(). getApplication()).getmAppComponent();
    }


}
