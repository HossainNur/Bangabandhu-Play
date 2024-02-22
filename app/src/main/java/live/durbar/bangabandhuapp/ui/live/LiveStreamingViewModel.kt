package live.durbar.bangabandhuapp.ui.live

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.live.Data
import live.durbar.bangabandhuapp.data.repository.LiveStreamingRepository

class LiveStreamingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LiveStreamingRepository

    init {
        repository =
            LiveStreamingRepository(
                application
            )
    }

    val liveStreaming: MutableLiveData<Data>
        get() = repository.liveStreaming
}
