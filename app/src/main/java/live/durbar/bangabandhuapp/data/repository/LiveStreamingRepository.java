package live.durbar.bangabandhuapp.data.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import live.durbar.bangabandhuapp.data.Api;
import live.durbar.bangabandhuapp.data.ApiService;
import live.durbar.bangabandhuapp.data.model.live.Data;
import live.durbar.bangabandhuapp.data.model.live.LiveStreamingResponse;


import live.durbar.bangabandhuapp.data.Api;
import live.durbar.bangabandhuapp.data.model.live.Data;
import live.durbar.bangabandhuapp.data.model.live.LiveStreamingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveStreamingRepository {
    private Application application;
    private ApiService apiService;
    private MutableLiveData<Data> dataList;

    public LiveStreamingRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().apiService;
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
                //Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return dataList;
    }
}
