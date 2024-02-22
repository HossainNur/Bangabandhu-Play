package live.durbar.bangabandhuapp.ui.player

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.get_related_contents.SingleContentRelatedContent
import live.durbar.bangabandhuapp.data.model.ott_content.SingleOttContent
import live.durbar.bangabandhuapp.data.repository.PlayerRepository

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PlayerRepository

    init {
        repository = PlayerRepository(application)
    }

    fun fetchContent(UUID: String?): MutableLiveData<SingleOttContent?> {
        return repository.fetchContent(UUID)
    }

    fun getRelatedOttContents(UUID: String?): MutableLiveData<List<SingleContentRelatedContent>?> {
        return repository.getRelatedOttContents(UUID)
    }
}
