package com.sergio.mesonero.rickmorty.main_activity.intractors;

import android.util.Log;

import com.sergio.mesonero.rickmorty.main_activity.MainContract;
import com.sergio.mesonero.rickmorty.model.Json.Person;
import com.sergio.mesonero.rickmorty.model.Realm.PersonObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PersonDatabaseIntractorDatabaseImpl implements MainContract.PersonDatabaseIntractor {


    private Realm mRealm;
    @Override
    public void saveData(ArrayList<Person> personArrayList) {
//        mRealm = Realm.getInstance(getContext());

        Log.e("Save","save data");
        //podríamos comprobar si es necesario un update, pero como vamos a insertar los datos cada vez,
            //borramos los que había
        mRealm = Realm.getDefaultInstance();
        RealmResults<PersonObject> personObjects = mRealm.where(PersonObject.class).findAll();

        mRealm.beginTransaction();
        personObjects.deleteAllFromRealm();

        mRealm.commitTransaction();

        for (Person per : personArrayList) {
            mRealm.beginTransaction();
            PersonObject personObject = mRealm.createObject(PersonObject.class);
            personObject.setName(per.getName());
            personObject.setGender(per.getGender());
            personObject.setId(per.getId());
            personObject.setImage(per.getImage());
            personObject.setSpecies(per.getSpecies());
            personObject.setStatus(per.getStatus());
            personObject.setUrl(per.getUrl());
            personObject.setType(per.getType());

            mRealm.commitTransaction();
        }
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

    @Override
    public void getPersonFromDatabase(OnloadedDbListener onloadedDbListener) {
        mRealm = Realm.getDefaultInstance();
        RealmResults<PersonObject> personObjects = mRealm.where(PersonObject.class).findAll();

        Log.e("d","devuelvo prueb "+personObjects.size());
        ArrayList <Person> personArrayList = new ArrayList<>();
        for (PersonObject per : personObjects) {
            Person person = new Person();
            person.setName(per.getName());
            person.setGender(per.getGender());
            person.setId(per.getId());
            person.setImage(per.getImage());
            person.setSpecies(per.getSpecies());
            person.setStatus(per.getStatus());
            person.setUrl(per.getUrl());
            person.setType(per.getType());
            personArrayList.add(person);
        }
        onloadedDbListener.loadedFinished(personArrayList);
    }
}
