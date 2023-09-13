package com.example.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.bangabandhuplay.data.Api;
import com.example.bangabandhuplay.data.ApiService;
import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;
import com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider.FrontendCustomContentSlider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreHomeRepository {
    private Application application;
    private MutableLiveData<FrontendCustomContentSlider> slider;
    private MutableLiveData<CustomContentBySlug> contentHome;
    private ApiService apiService;

    public MoreHomeRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        slider = new MutableLiveData<>();
        contentHome = new MutableLiveData<>();
    }

    public MutableLiveData<CustomContentBySlug> fetchContentForHome(String slug){
        Call<CustomContentBySlug> call = apiService.getCustomContentBySlug(slug);
        call.enqueue(new Callback<CustomContentBySlug>() {
            @Override
            public void onResponse(Call<CustomContentBySlug> call, @NonNull Response<CustomContentBySlug> response) {


                if (response.isSuccessful() && response.body() != null) {
                    //Log.v(TAG, response.body().getMessage());
                    contentHome.setValue(response.body());
                } else {
                    Toast.makeText(application, "Something went wrong!!" + response.code(), Toast.LENGTH_SHORT).show();
                    //Log.v(TAG, "response not successful");
                }
            }

            @Override
            public void onFailure(Call<CustomContentBySlug> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return contentHome;
    }
    public MutableLiveData<FrontendCustomContentSlider> fetchSliders(String slug){
        Call<FrontendCustomContentSlider> call = apiService.getFrontendCustomContentSectionSliders(slug);

        call.enqueue(new Callback<FrontendCustomContentSlider>() {
            @Override
            public void onResponse(Call<FrontendCustomContentSlider> call, Response<FrontendCustomContentSlider> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //Log.v(TAG, response.body().getMessage());
                    slider.setValue(response.body());
                } else {
                    Toast.makeText(application, "Something went wrong!!" + response.code(), Toast.LENGTH_SHORT).show();
                    //Log.v(TAG, "response not successful");
                }
            }

            @Override
            public void onFailure(Call<FrontendCustomContentSlider> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return slider;
    }
}
