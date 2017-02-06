/**
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
package com.then.atry.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.then.atry.data.cache.sample.UserCache;
import com.then.atry.data.net.RestApiImpl;
import com.then.atry.data.entity.mapper.UserEntityJsonMapper;
import com.then.atry.data.net.RestApi;
import com.then.atry.data.repository.datasource.sample.CloudUserDataStore;
import com.then.atry.data.repository.datasource.sample.DiskUserDataStore;
import com.then.atry.data.repository.datasource.sample.UserDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

  private final Context context;
  private final UserCache userCache;

  @Inject
  public UserDataStoreFactory(@NonNull Context context, @NonNull UserCache userCache) {
    this.context = context.getApplicationContext();
    this.userCache = userCache;
  }

  /**
   * Create {@link UserDataStore} from a user id.
   */
  public UserDataStore create(int userId) {
    UserDataStore userDataStore;

    if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
      userDataStore = new DiskUserDataStore(this.userCache);
    } else {
      userDataStore = createCloudDataStore();
    }
    return userDataStore;
  }

  /**
   * Create {@link UserDataStore} to retrieve data from the Cloud.
   */
  public UserDataStore createCloudDataStore() {
    UserEntityJsonMapper entityJsonMapper = new UserEntityJsonMapper();
    RestApi restApi = new RestApiImpl(this.context, entityJsonMapper);
    return new CloudUserDataStore(restApi, this.userCache);
  }
}