package com.sergio.mesonero.rickmorty.main_activity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;

import android.util.Log;

import android.widget.Toast;
import com.sergio.mesonero.rickmorty.R;
import com.sergio.mesonero.rickmorty.baseactivity.BaseActivity;

import com.sergio.mesonero.rickmorty.main_activity.fragments.DetailFragment;
import com.sergio.mesonero.rickmorty.main_activity.fragments.FirstFragment;
import com.sergio.mesonero.rickmorty.main_activity.intractors.PersonDatabaseIntractorDatabaseImpl;
import com.sergio.mesonero.rickmorty.main_activity.intractors.PersonServerIntractorServerImpl;
import com.sergio.mesonero.rickmorty.main_presenter.MainPresenterImpl;
import com.sergio.mesonero.rickmorty.model.Json.Person;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;


public class MainActivity extends BaseActivity implements MainContract.MainView {


//    private MainContract.presenter presenter;
    FragmentManager manager;
    FirstFragment firstFragment;
    DetailFragment detailFragment;




    @Inject
    MainContract.presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new FirstFragment();

        Realm.init(this);
        manager = getSupportFragmentManager();


        presenter = new MainPresenterImpl(this, new PersonServerIntractorServerImpl(), new PersonDatabaseIntractorDatabaseImpl());

        presenter.showFirstScreen();
//        presenter.requestDataFromDatabase();


    }

    public void requestDataFromDatabase(){
        presenter.requestDataFromDatabase();
    }

    @Override
    public void showProgress() {
        if (firstFragment.isAdded()&&!firstFragment.isDetached() && !firstFragment.isRemoving()) {
            firstFragment.showProgressBar(true);
        }
    }

    @Override
    public void hideProgress() {
        if (firstFragment.isAdded()&&!firstFragment.isDetached() && !firstFragment.isRemoving()) {
            firstFragment.showProgressBar(false);
        }
    }

    @Override
    public void setDataToRecyclerView(ArrayList<Person> personArrayList) {

        Log.e("setdatatorec","setdatatorec");
        if (firstFragment.isAdded()&&!firstFragment.isDetached() && !firstFragment.isRemoving()) {
            firstFragment.setDataToRecyclerView(personArrayList);
        }
        else{
            Log.e("noadded","noadded");
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Error: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoDataMessage(boolean b) {

        Log.e("df","bol "+b);
        if (firstFragment.isAdded()&&!firstFragment.isDetached() && !firstFragment.isRemoving()) {

            firstFragment.showNoDataMessage(b);
        }
    }

    @Override
    public void showFirstScreen() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_portrait, firstFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showDetailScreen(Person person) {

        detailFragment = new DetailFragment();
        detailFragment.setPerson(person);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_portrait, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

//    @Override
//    protected Object getModule() {
////        return new ApplicationModule();
//        return null;
//    }

    @Override
    protected void closeRealm() {
        presenter.closeRealm();
    }

    @Override
    public void onBackPressed() {

        if (firstFragment.isVisible()){
            finish();
        }
        else{
            super.onBackPressed();
        }
    }
    public void buttonClicked(){
        presenter.buttonClicked();
    }

    public void personClicked(Person person) {

        presenter.showDetailScreen(person);
    }
}