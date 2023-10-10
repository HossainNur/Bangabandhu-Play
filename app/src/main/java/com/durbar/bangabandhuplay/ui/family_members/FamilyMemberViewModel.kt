package com.durbar.bangabandhuplay.ui.family_members

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.data.repository.DrawerMenuRepository

class FamilyMemberViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DrawerMenuRepository = DrawerMenuRepository()

    fun getPhotos(): MutableLiveData<List<Data>>{
        return repository.getPhotos()
    }

}