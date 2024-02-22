package live.durbar.bangabandhuapp.ui.more

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.category.single_sub.SingleSubCategoryRes
import live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug
import live.durbar.bangabandhuapp.data.repository.MoreHomeRepository

class MoreHomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MoreHomeRepository

    init {
        repository = MoreHomeRepository(application)
    }

    fun getContentHome(slug: String?): MutableLiveData<CustomContentBySlug?> {
        return repository.fetchContentForHome(slug)
    }

    fun getSingleSubCategoryContents(slug: String?): MutableLiveData<SingleSubCategoryRes?> {
        return repository.getSingleSubCategoryContents(slug)
    }
}
