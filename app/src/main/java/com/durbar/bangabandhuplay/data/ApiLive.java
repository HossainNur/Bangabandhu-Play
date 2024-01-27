package com.durbar.bangabandhuplay.data;

import com.durbar.bangabandhuplay.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiLive {
    private static volatile ApiLive instance = null;

    private final ApiService apiService;

    public static ApiLive getInstance() {
        if (instance == null) synchronized (ApiLive.class) {
            if (instance == null) {
                instance = new ApiLive();
            }
        }

        return instance;
    }

    private ApiLive() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL_LIVE).addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return this.apiService;
    }
}
