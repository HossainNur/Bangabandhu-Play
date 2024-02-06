package com.durbar.bangabandhuplay.ui.more;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.model.category.single_sub.SingleSubCategoryRes;
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug;
import com.durbar.bangabandhuplay.data.repository.MoreHomeRepository;

public class MoreHomeViewModel extends AndroidViewModel {
    private MoreHomeRepository repository;
    public MoreHomeViewModel(@NonNull Application application) {
        super(application);
        repository = new MoreHomeRepository(application);
    }
    public MutableLiveData<CustomContentBySlug> getContentHome(String slug) {
        return repository.fetchContentForHome(slug);
    }

    public MutableLiveData<SingleSubCategoryRes> getSingleSubCategoryContents(String slug){
        return repository.getSingleSubCategoryContents(slug);
    }
}
