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

import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.net.HttpApiManager;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.BusinessUseCase;
import com.then.atry.domain.interactor.ehome.CpfUseCase;
import com.then.atry.domain.interactor.ehome.OauthUseCase;
import com.then.atry.domain.interactor.sample.GetUserDetails;
import com.then.atry.domain.interactor.sample.GetUserList;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.sample.UserRepository;
import com.then.atry.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

    private int userId = -1;

    public UserModule() {
    }

    public UserModule(int userId) {
        this.userId = userId;
    }

    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetUserListUseCase(GetUserList getUserList) {
        return getUserList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(
            UserRepository userRepository, ThreadExecutor threadExecutor, RequestFilter requestFilter,
            PostExecutionThread postExecutionThread) {
        return new GetUserDetails(userId, userRepository, threadExecutor, postExecutionThread, requestFilter);
    }


    @Provides
    @PerActivity
    @Named("oauthCase")
    UseCase provideOauthCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RequestFilter requestFilter, HttpApiManager httpApiManager) {
        return new OauthUseCase(threadExecutor, postExecutionThread, requestFilter, httpApiManager);
    }

    @Provides
    @PerActivity
    @Named("cpfCase")
    UseCase provideCpfCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RequestFilter requestFilter, HttpApiManager httpApiManager) {
        return new CpfUseCase(threadExecutor, postExecutionThread, requestFilter, httpApiManager);
    }

    @Provides
    @PerActivity
    BusinessUseCase provideBusinessCase(@Named("cpfCase") UseCase cpfCase, @Named("oauthCase") UseCase oauthCase, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RequestFilter requestFilter) {
        return new BusinessUseCase(cpfCase, oauthCase, threadExecutor, postExecutionThread, requestFilter);
    }

}