package com.then.atry.internal.di.modules;

import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.atom.oauth.AccountLogin;
import com.then.atry.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by then on 2017/2/15.
 */
@Module
public class LoginModule {

    public LoginModule(){

    }




    @Provides
    @PerActivity
    @Named("login")
    UseCase provideLoginUseCase(AccountLogin accountLogin) {
        return accountLogin;
    }


}