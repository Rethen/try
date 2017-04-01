package com.then.atry.data.repository.datasource;

/**
 * Created by 42524 on 2017/4/1.
 */

public interface DataStoreFactory<T extends DataStore> {

    T createDataStore();

}
