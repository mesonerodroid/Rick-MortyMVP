package com.sergio.mesonero.rickmorty.main_activity;

import com.sergio.mesonero.rickmorty.model.Json.Person;

import java.util.ArrayList;

public interface MainContract {



    interface presenter{

        void onDestroy();

        void buttonClicked();

        void requestDataFromServer();

        void requestDataFromDatabase();

        void saveData(ArrayList<Person> personArrayList);

        void closeRealm();


        void showFirstScreen();

        void showDetailScreen(Person person);
    }


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Person> noticeArrayList);

        void onResponseFailure(Throwable throwable);

        void showNoDataMessage(boolean b);

        void showFirstScreen();

        void showDetailScreen(Person person);
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface PersonServerIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Person> personArrayList);
            void onFailure(Throwable t);
        }

        void getPersonFromServer(OnFinishedListener onFinishedListener);    //esto
    }

    interface PersonDatabaseIntractor {

//        interface saveData {
//            void onFinished(ArrayList<Person> personArrayList);
//            void onFailure(Throwable t);
//        }

        interface OnloadedDbListener {
            void loadedFinished(ArrayList<Person> personArrayList);

        }
        void saveData(ArrayList<Person> personArrayList);
        void closeRealm();

        void getPersonFromDatabase(OnloadedDbListener onloadedDbListener);
    }
}
