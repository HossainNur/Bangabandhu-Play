package com.durbar.bangabandhuplay.ui.photo_gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.data.repository.PhotoGalleryRepository

class PhotoGalleryViewModel(application: Application) : AndroidViewModel(application){

    private val repository: PhotoGalleryRepository = PhotoGalleryRepository(application)
    fun fetchGalleryPhotos(): MutableLiveData<List<Data>>{
        return repository.fetchGalleryPhotos()
    }
}