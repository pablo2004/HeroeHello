package com.oxxo.heroehello.Config.WebService;

import com.oxxo.heroehello.Module.Start.Response.CharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelWS {

    @GET("characters")
    Call<CharactersResponse> characters(@Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") long ts);

}