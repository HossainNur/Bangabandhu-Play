package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.model.search_content.SearchResultRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchContentsRepository {
    private ApiService apiService;
    private Application application;
    private MutableLiveData<SearchResultRes> searchData;

    public SearchContentsRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        searchData = new MutableLiveData<>();
    }
    public MutableLiveData<SearchResultRes> getSearchContents(String keyword){
        Call<SearchResultRes> call = apiService.getSearchContents(keyword);
        call.enqueue(new Callback<SearchResultRes>() {
            @Override
            public void onResponse(Call<SearchResultRes> call, Response<SearchResultRes> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null){
                    searchData.setValue(response.body());
                }else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResultRes> call, Throwable t) {
                Toast.makeText(application, "Failed - "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return searchData;
    }
}
