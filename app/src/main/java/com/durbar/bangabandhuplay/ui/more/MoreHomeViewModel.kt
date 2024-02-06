package com.durbar.bangabandhuplay.ui.more

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.category.single_sub.SingleSubCategoryRes
import com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug.CustomContentBySlug
import com.durbar.bangabandhuplay.data.repository.MoreHomeRepository

class MoreHomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MoreHomeRepository

    init {
        repository = MoreHomeRepository(application)
    }

    fun getContentHome(slug: String?): MutableLiveData<CustomContentBySlug> {
        return repository.fetchContentForHome(slug)
    }

    fun getSingleSubCategoryContents(slug: String?): MutableLiveData<SingleSubCategoryRes> {
        return repository.getSingleSubCategoryContents(slug)
    }
}
