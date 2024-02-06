package com.durbar.bangabandhuplay.ui.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.category.root.single.Data
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.data.repository.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository: MoviesRepository

    init {
        moviesRepository = MoviesRepository(application)
    }

    val sliders: MutableLiveData<List<Original>>
        get() = moviesRepository.fetchSlider()

    fun fetchMoviesCategory(slug: String?): MutableLiveData<List<Data>> {
        return moviesRepository.fetchMoviesCategory(slug)
    }
}
