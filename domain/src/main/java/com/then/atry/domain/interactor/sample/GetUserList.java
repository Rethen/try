package com.then.atry.domain.interactor.sample; /**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.sample.UserRepository;

import javax.inject.Inject;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 */
public class GetUserList extends UseCase {

  private final UserRepository userRepository;

  @Inject
  public GetUserList(UserRepository userRepository, ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread, RequestFilter requestFilter) {
    super(threadExecutor, postExecutionThread, requestFilter);
    this.userRepository = userRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.userRepository.users();
  }

  @Override
  protected Observable buildUseCaseObservable(String header, RequestBody content) {
    return null;
  }
}
