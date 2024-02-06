package com.durbar.bangabandhuplay.ui.family_member;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.durbar.bangabandhuplay.data.model.family_member.Data;
import com.durbar.bangabandhuplay.data.repository.FamilyMemberRepository;

import java.util.List;

public class FamilyMemberViewModel extends AndroidViewModel {

    private FamilyMemberRepository repository;
    public FamilyMemberViewModel(@NonNull Application application) {
        super(application);
        repository = new FamilyMemberRepository(application);
    }

    public MutableLiveData<List<Data>> fetchFamilyMemberPhotos(){
        return repository.fetchFamilyMemberPhotos();
    }
}
