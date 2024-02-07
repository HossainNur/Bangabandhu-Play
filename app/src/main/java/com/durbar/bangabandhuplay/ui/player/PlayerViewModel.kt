package com.durbar.bangabandhuplay.ui.player;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent;
import com.durbar.bangabandhuplay.data.model.ott_content.SingleOttContent;
import com.durbar.bangabandhuplay.data.repository.PlayerRepository;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository repository;
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new PlayerRepository(application);
    }

    public MutableLiveData<SingleOttContent> fetchContent(String UUID){
        return repository.fetchContent(UUID);
    }
    public MutableLiveData<List<SingleContentRelatedContent>> getRelatedOttContents(String UUID) {
        return repository.getRelatedOttContents(UUID);
    }
}
