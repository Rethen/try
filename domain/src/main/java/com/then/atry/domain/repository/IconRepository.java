package com.then.atry.domain.repository;

import com.then.atry.domain.Icon;
import com.then.atry.domain.IconSort;
import com.then.atry.domain.interactor.atom.cpf.icon.GetIconInfo;
import com.then.atry.domain.interactor.atom.cpf.icon.GetIconSortList;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/28.
 */

public interface IconRepository {

    Observable<List<IconSort>> getIcons(GetIconSortList.GetIconSortListParams params);

    Observable<Icon> getIconInfo(GetIconInfo.GetIconInfoParams params);


}
