package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.model.live.Data;
import com.durbar.bangabandhuplay.data.model.live.LiveStreamingResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveStreamingRepository {
    private Application application;
    private ApiService apiService;
    private MutableLiveData<Data> dataList;

    public LiveStreamingRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        dataList = new MutableLiveData<>();
    }

    public MutableLiveData<Data> getLiveStreaming(){
        Call<LiveStreamingResponse> call = apiService.getLiveStreaming();
        call.enqueue(new Callback<LiveStreamingResponse>() {
            @Override
            public void onResponse(Call<LiveStreamingResponse> call, Response<LiveStreamingResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    dataList.setValue(response.body().getData());
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LiveStreamingResponse> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return dataList;
    }
}
