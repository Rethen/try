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
import io.reactivex.functions.BiFunction;

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

        Observable<List<IconSort>> iconSortObs = sysRepository.sysList(new GetSysList.SysListParams())
                .flatMap(syses -> Observable.just(syses.get(0)))
                .flatMap(sys -> orgRepository.orgs(new GetOrgList.GetOrgListParams(sys.getSysId())))
                .flatMap(orgs ->{
                    orgId = orgs.get(0).getOrgId();
                    return iconRepository.getIcons(new GetIconSortList.GetIconSortListParams(orgs.get(0).getOrgId(), "1"));
                });

        Observable<List<Icon>> iconObs = iconSortObs.flatMapIterable(iconSorts -> iconSorts)
                .flatMap(iconSort -> iconRepository.getIconInfo(new GetIconInfo.GetIconInfoParams(orgId, iconSort.getIconId())))
                .buffer(10);

        Observable<List<IconSort>> lastObs = Observable.zip(iconSortObs, iconObs, new BiFunction<List<IconSort>, List<Icon>, List<IconSort>>() {
            @Override
            public List<IconSort> apply(List<IconSort> iconSorts, List<Icon> icons) throws Exception {
                for (int i = 0; i < iconSorts.size(); i++) {
                    IconSort iconSort = iconSorts.get(i);
                    Icon icon = icons.get(i);
                    iconSort.setImage(icon.getImage());
                }
                return iconSorts;
            }
        });
        return lastObs;
    }


}
