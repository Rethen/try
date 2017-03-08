package com.then.atry.data.repository;

import com.then.atry.data.net.HttpApiManager;
import com.then.atry.domain.Icon;
import com.then.atry.domain.IconSort;
import com.then.atry.domain.interactor.atom.cpf.icon.GetIconInfo;
import com.then.atry.domain.interactor.atom.cpf.icon.GetIconSortList;
import com.then.atry.domain.repository.IconRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/28.
 */

public class IconDataRepository implements IconRepository {

    private final HttpApiManager httpApiManager;


    @Inject
    IconDataRepository(HttpApiManager httpApiManager) {
        this.httpApiManager = httpApiManager;
    }

    @Override
    public Observable<List<IconSort>> getIcons(GetIconSortList.GetIconSortListParams params) {
        return httpApiManager.request(params);
    }

    @Override
    public Observable<Icon> getIconInfo(GetIconInfo.GetIconInfoParams params) {
        return httpApiManager.request(params);
    }

}
