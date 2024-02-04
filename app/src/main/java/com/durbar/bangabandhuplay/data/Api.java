package com.durbar.bangabandhuplay.data;

import com.durbar.bangabandhuplay.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Api {
    private static volatile Api instance = null;

    private final ApiService apiService;

    public static Api getInstance() {
        if (instance == null) synchronized (Api.class) {
            if (instance == null) {
                instance = new Api();
            }
        }

        return instance;
    }

    private Api() {
        HttpLoggingInterceptor httpLoggingInterceptor = new  HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient) //add our client
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return this.apiService;
    }
}
