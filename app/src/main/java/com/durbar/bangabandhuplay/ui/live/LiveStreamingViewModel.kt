package com.durbar.bangabandhuplay.ui.live;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.model.live.Data;
import com.durbar.bangabandhuplay.data.repository.LiveStreamingRepository;

public class LiveStreamingViewModel extends AndroidViewModel {

    private LiveStreamingRepository repository;
    public LiveStreamingViewModel(@NonNull Application application) {
        super(application);
        repository = new LiveStreamingRepository(application);
    }

    public MutableLiveData<Data> getLiveStreaming(){
        return repository.getLiveStreaming();
    }
}
