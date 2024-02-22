package live.durbar.bangabandhuapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_contents.Data
import live.durbar.bangabandhuapp.data.model.sliders.Original
import live.durbar.bangabandhuapp.data.repository.HomeRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HomeRepository

    init {
        repository = HomeRepository(application)
    }

    val sliders: MutableLiveData<List<Original>?>
        get() = repository.fetchSlider()
    val frontendSection: MutableLiveData<List<Data>?>
        get() = repository.getFrontendSection()

    val customSliders: MutableLiveData<List<live.durbar.bangabandhuapp.data.model.fronend_custom_slider.Original>?>
        get() = repository.fetchCustomSlider()
}