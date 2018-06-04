package com.sergio.mesonero.rickmorty.service;

import com.sergio.mesonero.rickmorty.model.Json.ResponseJson;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetPersonService
{
    @GET("api/character/")
    Call<ResponseJson> getPersonData();
}

