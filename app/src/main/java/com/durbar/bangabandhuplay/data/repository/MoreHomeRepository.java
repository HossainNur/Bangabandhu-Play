package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.model.category.single_sub.SingleSubCategoryRes;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreHomeRepository {
    private Application application;
    private MutableLiveData<CustomContentBySlug> contentHome;
    private MutableLiveData<SingleSubCategoryRes> singleSubCategory;
    private ApiService apiService;

    public MoreHomeRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        contentHome = new MutableLiveData<>();
        singleSubCategory = new MutableLiveData<>();
    }

    public MutableLiveData<CustomContentBySlug> fetchContentForHome(String slug) {
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
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return contentHome;
    }

    public MutableLiveData<SingleSubCategoryRes> getSingleSubCategoryContents(String slug){
        Call<SingleSubCategoryRes> call = apiService.getSingleSubCategoryContents(slug);
        call.enqueue(new Callback<SingleSubCategoryRes>() {
            @Override
            public void onResponse(Call<SingleSubCategoryRes> call, Response<SingleSubCategoryRes> response) {
                if (response.isSuccessful() && response.body() != null) {
                    singleSubCategory.setValue(response.body());
                } else {
                    Toast.makeText(application, "Something went wrong!!" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SingleSubCategoryRes> call, Throwable t) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return singleSubCategory;
    }
}
