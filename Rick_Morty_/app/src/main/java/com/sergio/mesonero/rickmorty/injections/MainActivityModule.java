package com.sergio.mesonero.rickmorty.injections;

import com.sergio.mesonero.rickmorty.main_activity.MainActivity;
import com.sergio.mesonero.rickmorty.main_activity.MainContract;
import com.sergio.mesonero.rickmorty.main_presenter.MainPresenterImpl;
import com.sergio.mesonero.rickmorty.main_activity.intractors.PersonDatabaseIntractorDatabaseImpl;
import com.sergio.mesonero.rickmorty.main_activity.intractors.PersonServerIntractorServerImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class MainActivityModule {
    @Binds
    public abstract MainContract.MainView view(MainActivity mainActivity);

    @Provides
    static MainContract.PersonServerIntractor provideServerIntractor()  {

        return new PersonServerIntractorServerImpl();
    }

    @Provides
    static MainContract.PersonDatabaseIntractor provideDatabaseIntractor()  {

        return new PersonDatabaseIntractorDatabaseImpl();
    }
    @Provides
    static MainContract.presenter provideAuthPresenter(MainContract.MainView view, MainContract.PersonServerIntractor personServerIntractor,
                                                       MainContract.PersonDatabaseIntractor personDatabaseIntractor)  {

        return new MainPresenterImpl(view, personServerIntractor, personDatabaseIntractor);
    }


}