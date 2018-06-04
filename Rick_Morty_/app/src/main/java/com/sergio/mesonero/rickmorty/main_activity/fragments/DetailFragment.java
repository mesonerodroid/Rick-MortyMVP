package com.sergio.mesonero.rickmorty.main_activity.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sergio.mesonero.rickmorty.R;
import com.sergio.mesonero.rickmorty.model.Json.Person;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class DetailFragment extends Fragment {

    private View rootView;
    private Context fatherActivity;

    private Person person;

    private TextView name, status, specie, gen;
    private ImageView image;
    private ProgressBar progressBar;

    @Inject
    Picasso picasso;

    public void setPerson(Person person){
        this.person=person;
    }

    //Nombre, estado, especie, género e imagen.

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

        initDataperson();
    }


    public View createCustomView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, View childView) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.second_fragment, container, false);
            fatherActivity = getActivity();
            initializeViews();
        }

        return rootView;
    }


    private void initializeViews() {

        name= rootView.findViewById(R.id.second_frag_name);
        status= rootView.findViewById(R.id.second_frag_status);
        specie= rootView.findViewById(R.id.second_frag_specie);
        gen= rootView.findViewById(R.id.second_frag_gen);
        image= rootView.findViewById(R.id.second_frag_image);
        progressBar = (ProgressBar)rootView.findViewById(R.id.second_frag_progres);

    }
    private void initDataperson() {

        name.setText(person.getName());
        status.setText(getString(R.string.status).concat (person.getStatus()));
        specie.setText(getString(R.string.specie).concat (person.getSpecies()));
        gen.setText(getString(R.string.gen).concat (person.getGender()));
        showProgress(true);


        Picasso.with(fatherActivity).load(person.getImage()).into(image, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                showProgress(false);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void showProgress(boolean b) {

        if (b){
            progressBar.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
        }
        else{
            image.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }


}
