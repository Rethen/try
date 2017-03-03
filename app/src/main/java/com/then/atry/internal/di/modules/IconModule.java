package com.then.atry.internal.di.modules;

import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.complex.GetCommonApprovalIconList;
import com.then.atry.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by then on 2017/2/28.
 */

@Module
public class IconModule {

    @Provides
    @PerActivity
    @Named("iconList")
    UseCase provideIconListUseCase(GetCommonApprovalIconList getCommonApprovalIconList) {
        return getCommonApprovalIconList;
    }
}
