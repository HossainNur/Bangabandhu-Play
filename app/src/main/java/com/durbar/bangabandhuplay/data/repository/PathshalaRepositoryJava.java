package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.model.pathshala.DataX;
import com.durbar.bangabandhuplay.data.model.pathshala.Ebook;
import com.durbar.bangabandhuplay.data.model.pathshala.PathshalaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PathshalaRepositoryJava {
    private Application application;
    private ApiService apiService;

    private MutableLiveData<List<DataX>> dataxList;

    public PathshalaRepositoryJava(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        dataxList = new MutableLiveData<>();
    }

    public MutableLiveData<List<DataX>> fetchEbook(){
        Call<PathshalaResponse> call = apiService.getPathsahlaPdf();
        call.enqueue(new Callback<PathshalaResponse>() {
            @Override
            public void onResponse(Call<PathshalaResponse> call, Response<PathshalaResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    dataxList.setValue(response.body().getData().getData());
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PathshalaResponse> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return dataxList;
    }
}
