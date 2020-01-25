package com.oxxo.heroehello.Config;

import android.os.Bundle;
import android.util.Log;

import com.mno.init.Core.Module.ActivityBase;
import com.oxxo.heroehello.Config.WebService.MarvelWS;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends ActivityBase {

    private MarvelWS webService = null;

    public MarvelWS getWebService() {
        return webService;
    }

    public void setWebService(MarvelWS webService) {
        this.webService = webService;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        Log.e("main", getClass().getCanonicalName());
        webServiceInit();
    }

    private void webServiceInit(){
        OkHttpClient httpClient;

        // FOR ONLY LOG
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(logging).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS);
        httpClient = httpBuilder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.WS_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setWebService(retrofit.create(MarvelWS.class));
    }

}
