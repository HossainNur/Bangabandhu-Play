package com.durbar.bangabandhuplay.ui.home;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.model.sliders.Original;
import com.durbar.bangabandhuplay.data.repository.HomeRepository;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data;

import java.util.List;

 public class HomeViewModel extends AndroidViewModel {

    private HomeRepository repository;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new HomeRepository(application);
    }

    public MutableLiveData<List<Original>> getSliders(){
        return repository.fetchSlider();
    }

    public MutableLiveData<List<Data>> getFrontendSection(){
        return repository.getFrontendSection();
    }
}