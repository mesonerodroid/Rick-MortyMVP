package com.sergio.mesonero.rickmorty.injections;



import dagger.android.AndroidInjector;

import dagger.android.DaggerApplication;


public class MyApplication extends DaggerApplication {


        @Override
        public void onCreate() {
            super.onCreate();
        }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return com.sergio.mesonero.rickmorty.injections.DaggerAppComponent.builder().application(this).build();
    }

}