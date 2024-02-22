package live.durbar.bangabandhuapp.ui.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.category.root.single.Data
import live.durbar.bangabandhuapp.data.model.sliders.Original
import live.durbar.bangabandhuapp.data.repository.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository: MoviesRepository

    init {
        moviesRepository = MoviesRepository(application)
    }

    val sliders: MutableLiveData<List<Original>?>
        get() = moviesRepository.fetchSlider()

    fun fetchMoviesCategory(slug: String?): MutableLiveData<List<Data>?> {
        return moviesRepository.fetchMoviesCategory(slug)
    }
}
