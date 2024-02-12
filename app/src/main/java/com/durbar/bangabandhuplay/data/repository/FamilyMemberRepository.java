package com.durbar.bangabandhuplay.data.repository;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.Api;
import com.durbar.bangabandhuplay.data.ApiService;
import com.durbar.bangabandhuplay.data.model.family_member.Data;
import com.durbar.bangabandhuplay.data.model.family_member.FamilyMemberResponse;
import com.durbar.bangabandhuplay.utils.NetworkUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FamilyMemberRepository {
    private Application application;
    private ApiService apiService;
    private MutableLiveData<List<Data>> dataList;
    private NetworkUtil networkUtil;

    public FamilyMemberRepository(Application application) {
        this.application = application;
        apiService = Api.getInstance().getApiService();
        dataList = new MutableLiveData<>();
        networkUtil = NetworkUtil.getInstance(application);
    }

    public MutableLiveData<List<Data>> fetchFamilyMemberPhotos(){
        Call<FamilyMemberResponse> call = apiService.getFamilyMemberPhotos();
        call.enqueue(new Callback<FamilyMemberResponse>() {
            @Override
            public void onResponse(Call<FamilyMemberResponse> call, Response<FamilyMemberResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    dataList.setValue(response.body().getData());
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FamilyMemberResponse> call, Throwable t) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return dataList;
    }


}
