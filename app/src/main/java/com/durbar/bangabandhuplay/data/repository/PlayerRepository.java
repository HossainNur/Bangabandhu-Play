package com.durbar.bangabandhuplay.data.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.model.get_related_contents.GetRelatedContent;
import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent;
import com.durbar.bangabandhuplay.data.model.ott_content.SingleOttContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerRepository {
    private static final String TAG = "PlayerRepository";
    private ApiService apiService;
    private MutableLiveData<SingleOttContent> content;
    private MutableLiveData<List<SingleContentRelatedContent>> relatedContent;
    private Application application;

    public PlayerRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        content = new MutableLiveData<>();
        relatedContent = new MutableLiveData<>();
    }

    public MutableLiveData<SingleOttContent> fetchContent(String UUID) {

        Call<SingleOttContent> call = apiService.getSingleOttContent(UUID);

        call.enqueue(new Callback<SingleOttContent>() {
            @Override
            public void onResponse(Call<SingleOttContent> call, Response<SingleOttContent> response) {

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getData() != null)
                        content.setValue(response.body());
                    Log.v(TAG, "code: " + response.code() + " message: " + response.message());
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SingleOttContent> call, Throwable t) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });

        return content;
    }

    public MutableLiveData<List<SingleContentRelatedContent>> getRelatedOttContents(String UUID) {

        Call<GetRelatedContent> call = apiService.getRelatedOttContents(UUID);
        call.enqueue(new Callback<GetRelatedContent>() {
            @Override
            public void onResponse(Call<GetRelatedContent> call, Response<GetRelatedContent> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData().getSingleContentRelatedContents() != null) {
                    relatedContent.setValue(response.body().getData().getSingleContentRelatedContents());
                    Log.d("response",""+response.body().getData().getSingleContentRelatedContents());
                }
                else{
                    Toast.makeText(application, response.code()+" - "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetRelatedContent> call, Throwable t) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });

        return relatedContent;
    }

}
