package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.model.category.root.single.Data;
import com.durbar.bangabandhuplay.data.model.category.root.single.SingleRootCategory;
import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.data.model.sliders.Sliders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    private Application application;
    private ApiService apiService;
    private MutableLiveData<List<Original>> sliderList;

    private MutableLiveData<List<Data>> dataList;

    public MoviesRepository(Application application) {
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
                    Toast.makeText(application, "Something went wrong!!", Toast.LENGTH_SHORT).show();
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
    public MutableLiveData<List<Data>> fetchMoviesCategory(String slug){

        Call<SingleRootCategory> call = apiService.getMoviesCategory(slug);

        call.enqueue(new Callback<SingleRootCategory>() {
            @Override
            public void onResponse(Call<SingleRootCategory> call, Response<SingleRootCategory> response) {
                if (response.isSuccessful() && response.body() != null){
                    dataList.setValue(response.body().getData());
                }else {
                    Toast.makeText(application, response.code()+" - "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SingleRootCategory> call, Throwable t) {
                Toast.makeText(application, "Failed - "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return dataList;
    }
}
