package com.then.atry.data.cache.ehome;

import io.realm.RealmModel;

/**
 * @类名: DataCache
 * @描述: 数据缓存
 * @作者 then
 * @创建日期 2017/1/24 14:56
 */
public interface DataCache<E extends RealmModel> {

    boolean isExpired();

    void evictAll();

    void put(E e);
}
