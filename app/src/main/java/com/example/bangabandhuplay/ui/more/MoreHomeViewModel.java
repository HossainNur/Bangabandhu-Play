package com.example.bangabandhuplay.ui.more;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;
import com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider.FrontendCustomContentSlider;
import com.example.bangabandhuplay.data.repository.MoreHomeRepository;

public class MoreHomeViewModel extends AndroidViewModel {
    private MoreHomeRepository repository;
    public MoreHomeViewModel(@NonNull Application application) {
        super(application);
        repository = new MoreHomeRepository(application);
    }

    public MutableLiveData<CustomContentBySlug> getContentHome(String slug) {
        return repository.fetchContentForHome(slug);
    }
    public MutableLiveData<FrontendCustomContentSlider> getSlider(String slug) {
        return repository.fetchSliders(slug);
    }
}
