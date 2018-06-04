package com.sergio.mesonero.rickmorty.main_activity.intractors;

import android.util.Log;

import com.sergio.mesonero.rickmorty.main_activity.MainContract;
import com.sergio.mesonero.rickmorty.model.Json.ResponseJson;
import com.sergio.mesonero.rickmorty.service.GetPersonService;
import com.sergio.mesonero.rickmorty.retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonServerIntractorServerImpl implements MainContract.PersonServerIntractor {

    @Override
    public void getPersonFromServer(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetPersonService service = RetrofitInstance.getRetrofitInstance().create(GetPersonService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<ResponseJson> call = service.getPersonData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                Log.wtf("ok", call.request().url() + " "+ response.body().getResults().size());
                onFinishedListener.onFinished((ArrayList)response.body().getResults());

            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }



}
