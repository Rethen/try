package com.then.atry.domain.interactor.complex;

import com.then.atry.domain.Icon;
import com.then.atry.domain.IconSort;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.atom.cpf.icon.GetIconInfo;
import com.then.atry.domain.interactor.atom.cpf.icon.GetIconSortList;
import com.then.atry.domain.interactor.atom.cpf.org.GetOrgList;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysList;
import com.then.atry.domain.repository.IconRepository;
import com.then.atry.domain.repository.OrgRepository;
import com.then.atry.domain.repository.SysRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @类名: GetCommonApprovalIconList
 * @描述: 获取常用审批列表
 * @作者 then
 * @创建日期 2017/2/28 10:31
 */
public class GetCommonApprovalIconList extends UseCase<List<IconSort>, String> {


    private final SysRepository sysRepository;

    private final OrgRepository orgRepository;

    private final IconRepository iconRepository;

    String orgId = "";

    @Inject
    GetCommonApprovalIconList(SysRepository sysRepository, OrgRepository orgRepository, IconRepository iconRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.sysRepository = sysRepository;
        this.orgRepository = orgRepository;
        this.iconRepository = iconRepository;
    }


    @Override
    protected Observable<List<IconSort>> buildUseCaseObservable(String sysId) {

        return sysRepository.sysList(new GetSysList.SysListParams())
                .flatMap(syses -> Observable.just(syses.get(0)))
                .flatMap(sys -> orgRepository.orgs(new GetOrgList.GetOrgListParams(sys.getSysId())))
                .flatMap(orgs -> {
                    orgId = orgs.get(0).getOrgId();
                    return iconRepository.getIcons(new GetIconSortList.GetIconSortListParams(orgs.get(0).getOrgId(), "1"));
                })
                .flatMapIterable(iconSorts -> iconSorts)
                .flatMap(iconSort -> {
                    Observable<Icon> iconObs = iconRepository.getIconInfo(new GetIconInfo.GetIconInfoParams(orgId, iconSort.getIconId()));

                    return Observable.zip(Observable.just(iconSort), iconObs, (iconSort1, icon) -> {
                        iconSort1.setImage(icon.getImage());
                        return iconSort1;
                    });
                })
                .buffer(10);


    }


}
