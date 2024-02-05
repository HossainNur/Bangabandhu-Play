package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("contents")
    val contents: List<Content>? = listOf()
)
