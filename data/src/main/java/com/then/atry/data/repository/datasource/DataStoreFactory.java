package com.then.atry.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.then.atry.data.cache.ehome.DataCache;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by then on 2017/1/24.
 */

@Singleton
public class DataStoreFactory {

    private final Context context;

    private final DataCache dataCache;

    @Inject
    public DataStoreFactory(@NonNull Context context, @NonNull DataCache dataCache) {
        this.context = context.getApplicationContext();
        this.dataCache = dataCache;
    }



}
