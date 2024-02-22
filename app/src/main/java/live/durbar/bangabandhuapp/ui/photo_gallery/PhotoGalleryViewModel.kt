package live.durbar.bangabandhuapp.ui.photo_gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.photo_gallery.Data
import live.durbar.bangabandhuapp.data.repository.PhotoGalleryRepository

class PhotoGalleryViewModel(application: Application) : AndroidViewModel(application){

    private val repository: PhotoGalleryRepository = PhotoGalleryRepository(application)
    fun fetchGalleryPhotos(): MutableLiveData<List<Data>>{
        return repository.fetchGalleryPhotos()
    }
}