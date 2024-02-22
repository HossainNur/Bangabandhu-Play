package live.durbar.bangabandhuapp.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.search_content.SearchResultRes
import live.durbar.bangabandhuapp.data.repository.SearchContentsRepository

class SearchContentsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SearchContentsRepository

    init {
        repository = SearchContentsRepository(application)
    }

    fun getSearchContents(keyword: String?): MutableLiveData<SearchResultRes?> {
        return repository.getSearchContents(keyword)
    }
}
