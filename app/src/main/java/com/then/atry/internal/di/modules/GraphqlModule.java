package com.then.atry.internal.di.modules;

import com.then.atry.domain.interactor.UseCase;
import com.then.atry.internal.di.PerActivity;
import com.then.atry.domain.interactor.graphql.Graphql;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 42524 on 2017/3/30.
 */
@Module
public class GraphqlModule {

    @Provides
    @PerActivity
    @Named("graphql")
    UseCase provideIconListUseCase(Graphql graphql) {
        return graphql;
    }
}
