package com.durbar.bangabandhuplay.ui.search;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.durbar.bangabandhuplay.data.model.search_content.SearchResultRes;
import com.durbar.bangabandhuplay.data.repository.SearchContentsRepository;
public class SearchContentsViewModel extends AndroidViewModel {
    private SearchContentsRepository repository;


    public SearchContentsViewModel(@NonNull Application application) {
        super(application);
        repository = new SearchContentsRepository(application);
    }

    public MutableLiveData<SearchResultRes> getSearchContents(String keyword){
        return repository.getSearchContents(keyword);
    }
}
