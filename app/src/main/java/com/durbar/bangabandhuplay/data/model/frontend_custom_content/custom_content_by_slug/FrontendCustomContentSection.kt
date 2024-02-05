package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FrontendCustomContentSection(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("content_type_slug")
    val contentTypeSlug: Int? = 0,

    @field:SerializedName("content_type_title")
    val contentTypeTitle: String? = null,

    @field:SerializedName("more_info_slug")
    val moreInfoSlug: String? = null
)
