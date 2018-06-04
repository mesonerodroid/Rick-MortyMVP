package com.sergio.mesonero.rickmorty.main_activity;


import android.app.Application;
import android.support.annotation.NonNull;
//
//import dagger.ObjectGraph;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RickMortyApplication extends Application {

    private static RickMortyApplication sInstance;
//    private ObjectGraph mApplicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        initRealmConfiguration();
        initApplicationGraph();
    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initApplicationGraph() {
//        mApplicationGraph = ObjectGraph.create(new ApplicationModule());
    }

    public static void injectModules(@NonNull final Object object, final Object... modules) {
//        sInstance.mApplicationGraph.plus(modules).inject(object);
    }
}