package com.then.atry.application;

import android.support.multidex.MultiDexApplication;

import com.kymjs.themvp.ViewListenerManager;
import com.then.atry.activity.main.MainActivity;
import com.then.atry.data.viewmodel.MessageHubViewModel;
import com.then.atry.fragment.messagehub.MessageHubFragment;
import com.then.atry.internal.di.components.ApplicationComponent;
import com.then.atry.internal.di.components.DaggerApplicationComponent;
import com.then.atry.internal.di.modules.ApplicationModule;

import io.realm.Realm;


/**
 * Created by then on 2016/12/6.
 */

public class App extends MultiDexApplication {


    private ApplicationComponent mAppComponent;


    private ViewListenerManager viewListenerManager;


    @Override
    public void onCreate() {
        super.onCreate();
        viewListenerManager = ViewListenerManager.getInstance();
        initializeInjector();
        initViewModelActionMapping();
        Realm.init(getApplicationContext());
//        initPrefManager();
    }
   /* @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginLoader.initLoader(this);
    }

    @Override
    public Context getBaseContext() {
        return PluginLoader.fixBaseContextForReceiver(super.getBaseContext());
    }*/

    private void initializeInjector() {
        this.mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initViewModelActionMapping() {
        viewListenerManager.initViewModelMap(MessageHubViewModel.class, MessageHubFragment.class, MainActivity.class);
    }


    public ApplicationComponent getmAppComponent() {
        return mAppComponent;
    }


}
