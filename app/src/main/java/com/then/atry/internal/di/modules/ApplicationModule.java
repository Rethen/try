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
package com.then.atry.internal.di.modules;

import android.content.Context;

import com.then.atry.data.EhomeReqHelper;
import com.then.atry.data.EhomeRequestFilter;
import com.then.atry.data.cache.sample.UserCache;
import com.then.atry.data.cache.sample.UserCacheImpl;
import com.then.atry.data.process.converter.json.factory.EhomeJsonConverterFactory;
import com.then.atry.data.executor.JobExecutor;
import com.then.atry.data.preferences.IPreferencesPrefs;
import com.then.atry.data.repository.UserDataRepository;
import com.then.atry.domain.net.HttpApiManager;
import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.repository.sample.UserRepository;
import com.then.atry.UIThread;
import com.then.atry.application.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    IPreferencesPrefs providePreferences() {
        return IPreferencesPrefs.create(application);
    }

    @Provides
    @Singleton
    EhomeReqHelper provideEhomeReqHelper(IPreferencesPrefs preferences) {
        return new EhomeReqHelper(preferences);
    }

    @Provides
    @Singleton
    HttpApiManager provideHttpApiManager(EhomeJsonConverterFactory factory) {
        return new HttpApiManager(factory);
    }

    @Provides
    @Singleton
    EhomeJsonConverterFactory provideSimpleJsonConverterFactory(EhomeReqHelper ehomeReqHelper, IPreferencesPrefs preferencesPrefs) {
        return EhomeJsonConverterFactory.create(ehomeReqHelper, preferencesPrefs);
    }

    @Provides
    @Singleton
    RequestFilter provideRequestFactory(EhomeReqHelper ehomeReqHelper) {
        return new EhomeRequestFilter(ehomeReqHelper);
    }

}
