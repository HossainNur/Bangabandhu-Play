package com.durbar.bangabandhuplay.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents.Data
import com.durbar.bangabandhuplay.data.model.sliders.Original
import com.durbar.bangabandhuplay.data.repository.HomeRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HomeRepository

    init {
        repository = HomeRepository(application)
    }

    val sliders: MutableLiveData<List<Original>>
        get() = repository.fetchSlider()
    val frontendSection: MutableLiveData<List<Data>>
        get() = repository.frontendSection
}