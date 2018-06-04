package com.sergio.mesonero.rickmorty.main_presenter;

import android.util.Log;

import com.sergio.mesonero.rickmorty.main_activity.MainContract;
import com.sergio.mesonero.rickmorty.model.Json.Person;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.PersonServerIntractor.OnFinishedListener, MainContract.PersonDatabaseIntractor.OnloadedDbListener {


    private MainContract.MainView mainView;
    private MainContract.PersonServerIntractor personServerIntractor;
    private MainContract.PersonDatabaseIntractor personDatabaseIntractor;


    //todo combinar getperson y save person en realmservice. Que realmService sea independiente de la Activity

    //todo dagger
    public MainPresenterImpl(MainContract.MainView mainView, MainContract.PersonServerIntractor personServerIntractor,
                             MainContract.PersonDatabaseIntractor personDatabaseIntractor) {
        this.mainView = mainView;
        this.personServerIntractor = personServerIntractor;
        this.personDatabaseIntractor = personDatabaseIntractor;
    }

//    @Inject
//    public MainPresenterImpl(MainContract.MainView mainView, MainContract.PersonServerIntractor personServerIntractor,
//                             MainContract.PersonDatabaseIntractor personDatabaseIntractor) {
//        this.mainView = mainView;
//        this.personServerIntractor = personServerIntractor;
//        this.personDatabaseIntractor = personDatabaseIntractor;
//    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void buttonClicked() {

        if(mainView != null){
            mainView.showProgress();
        }
        personServerIntractor.getPersonFromServer(this);

    }

    @Override
    public void requestDataFromServer() {
        personServerIntractor.getPersonFromServer(this);
    }

    @Override
    public void requestDataFromDatabase() {
        personDatabaseIntractor.getPersonFromDatabase(this);
    }

    @Override
    public void closeRealm() {
        personDatabaseIntractor.closeRealm();
    }

    @Override
    public void showFirstScreen() {
        mainView.showFirstScreen();
    }

    @Override
    public void showDetailScreen(Person person) {
        mainView.showDetailScreen(person);
    }


    @Override
    public void onFinished(ArrayList<Person> personArrayList) {
        //guardamos y cargamos
        Log.e("T", "onfinished rest");
        personDatabaseIntractor.saveData(personArrayList);
        personDatabaseIntractor.getPersonFromDatabase(this);
        if(mainView != null){
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

    @Override
    public void saveData(ArrayList<Person> personArrayList) {
        personDatabaseIntractor.saveData(personArrayList);
    }

    @Override
    public void loadedFinished(ArrayList<Person> personArrayList) {
        Log.e("loaded data", "loaded data "+personArrayList.size());

        mainView.showNoDataMessage(personArrayList.size()==0);


        if(mainView != null){
            mainView.setDataToRecyclerView(personArrayList);

            mainView.hideProgress();
        }
    }
}
