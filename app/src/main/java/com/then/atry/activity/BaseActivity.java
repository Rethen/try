package com.then.atry.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.kymjs.themvp.databind.DataBindActivity;
import com.kymjs.themvp.view.IDelegate;
import com.then.atry.application.App;
import com.then.atry.data.EhomeReqHelper;
import com.then.atry.data.preferences.IPreferencesPrefs;
import com.then.atry.internal.di.components.ApplicationComponent;

/**
 * Created by then on 2016/12/16.
 */

public abstract class BaseActivity<T extends IDelegate, D extends ViewDataBinding> extends DataBindActivity<T, D> {


    protected IPreferencesPrefs preferences;

    protected EhomeReqHelper ehomeReqHelper;

//    protected RequestFilter reequestFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getAppComponment().inject(this);
        preferences = getAppComponment().iPreferences();
        ehomeReqHelper = getAppComponment().ehomeReqHelper();
//        ehomeRequestFilter = getAppComponment().requestFilter();
    }

    protected ApplicationComponent getAppComponment() {
        return ((App) getApplication()).getmAppComponent();
    }


}
