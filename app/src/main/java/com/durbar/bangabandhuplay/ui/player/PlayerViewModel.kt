package com.durbar.bangabandhuplay.ui.player

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.get_related_contents.SingleContentRelatedContent
import com.durbar.bangabandhuplay.data.model.ott_content.SingleOttContent
import com.durbar.bangabandhuplay.data.repository.PlayerRepository

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
