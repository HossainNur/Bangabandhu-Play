package com.durbar.bangabandhuplay.ui.family_member

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.family_member.Data
import com.durbar.bangabandhuplay.data.repository.FamilyMemberRepository

class FamilyMemberViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FamilyMemberRepository

    init {
        repository = FamilyMemberRepository(application)
    }

    fun fetchFamilyMemberPhotos(): MutableLiveData<List<Data>> {
        return repository.fetchFamilyMemberPhotos()
    }
}
