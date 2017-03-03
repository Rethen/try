package com.then.atry.internal.di.modules;

import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysList;
import com.then.atry.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by then on 2017/2/15.
 */
@Module
public class SysModule {

    @Provides
    @PerActivity
    @Named("sysList")
    UseCase provideLoginUseCase(GetSysList getSysList) {
        return getSysList;
    }
}
