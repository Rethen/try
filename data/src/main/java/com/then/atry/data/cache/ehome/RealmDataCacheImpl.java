package com.then.atry.data.cache.ehome;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmModel;

/**
 * @类名: RealmDataCacheImpl
 * @描述: 数据缓存的Realm实现
 * @作者 then
 * @创建日期 2017/1/24 16:02
 */
@Singleton
public class RealmDataCacheImpl implements DataCache {

    private Realm realm;

    @Inject
    public RealmDataCacheImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }

    @Override
    public void put(RealmModel realmModel) {

    }


}
