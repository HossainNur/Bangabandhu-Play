package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.CustomContent;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data;
import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.data.model.sliders.Sliders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private Application application;
    private ApiService apiService;
    private MutableLiveData<List<Original>> sliderList;
    private MutableLiveData<List<Data>> dataList;

    public HomeRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        sliderList = new MutableLiveData<>();
        dataList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Original>> fetchSlider() {

        Call<Sliders> call = apiService.getSliders();
        call.enqueue(new Callback<Sliders>() {
            @Override
            public void onResponse(@NonNull Call<Sliders> call, @NonNull Response<Sliders> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData().getOriginal() != null) {
                    sliderList.setValue(response.body().getData().getOriginal());
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sliders> call, @NonNull Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return sliderList;
    }

    public MutableLiveData<List<Data>> getFrontendSection() {
        Call<CustomContent> call = apiService.getFrontendCustomContent();
        call.enqueue(new Callback<CustomContent>() {
            @Override
            public void onResponse(Call<CustomContent> call, Response<CustomContent> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    dataList.setValue(response.body().getData());
                } else {
                    Toast.makeText(application, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<CustomContent> call, @NonNull Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return dataList;
    }


}
