package com.durbar.bangabandhuplay.ui.live

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.live.Data
import com.durbar.bangabandhuplay.data.repository.LiveStreamingRepository

class LiveStreamingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LiveStreamingRepository

    init {
        repository = LiveStreamingRepository(application)
    }

    val liveStreaming: MutableLiveData<Data>
        get() = repository.liveStreaming
}
