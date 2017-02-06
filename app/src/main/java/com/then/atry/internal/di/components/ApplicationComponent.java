/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.then.atry.internal.di.components;

import android.app.Activity;
import android.content.Context;

import com.then.atry.data.EhomeReqHelper;
import com.then.atry.data.preferences.IPreferencesPrefs;
import com.then.atry.domain.net.HttpApiManager;
import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.repository.sample.UserRepository;
import com.then.atry.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(Activity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();

    IPreferencesPrefs iPreferences();

    EhomeReqHelper ehomeReqHelper();

    HttpApiManager httpApiManager();

    RequestFilter requestFilter();
}
