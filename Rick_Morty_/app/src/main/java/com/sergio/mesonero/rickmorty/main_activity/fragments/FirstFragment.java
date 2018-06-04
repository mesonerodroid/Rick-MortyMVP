package com.sergio.mesonero.rickmorty.main_activity.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sergio.mesonero.rickmorty.R;
import com.sergio.mesonero.rickmorty.adapter.PersonAdapter;
import com.sergio.mesonero.rickmorty.main_activity.MainActivity;
import com.sergio.mesonero.rickmorty.main_activity.RecyclerItemClickListener;
import com.sergio.mesonero.rickmorty.model.Json.Person;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private View rootView;
    private Context fatherActivity;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button button;
    private TextView noDataTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createCustomView(inflater, container, savedInstanceState, null);
    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        ((MainActivity) fatherActivity).requestDataFromDatabase();
    }


    public View createCustomView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, View childView) {


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.first_fragment, container, false);
            fatherActivity = getActivity();

            initializeViews();
        }

        return rootView;
    }


    private void initializeViews() {

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        button = rootView.findViewById(R.id.button);
        noDataTxt = rootView.findViewById(R.id.nodata_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) fatherActivity).buttonClicked();
            }
        });
        recyclerView = rootView.findViewById(R.id.recycler_persons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(fatherActivity);
        recyclerView.setLayoutManager(layoutManager);

        progressBar = rootView.findViewById(R.id.first_frag_progres);
    }

    /**
     * Initializing progressbar programmatically
     * */


    public void setDataToRecyclerView(ArrayList<Person> personArrayList) {


        PersonAdapter adapter = new PersonAdapter(personArrayList , recyclerItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Person person) {

            ((MainActivity) fatherActivity).personClicked(person);



            //TODO click a otro fragment

        }
    };
    public void showNoDataMessage(boolean b) {


        if(b){
            noDataTxt.setVisibility(View.VISIBLE);
        }
        else{
            noDataTxt.setVisibility(View.GONE);
        }
    }

    public void showProgressBar(boolean b){
        if (b){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }
}
