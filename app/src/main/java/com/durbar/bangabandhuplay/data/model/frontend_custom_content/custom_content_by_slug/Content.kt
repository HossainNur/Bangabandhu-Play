package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Content(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("content_id")
    val contentId: Int? = 0,

    @field:SerializedName("publish_date")
    val publishDate: String? = null,

    @field:SerializedName("is_active")
    val isActive: Int? = 0,

    @field:SerializedName("is_upcoming")
    val isUpcoming: Int? = 0,

    @field:SerializedName("sorting_position")
    val sortingPosition: Int? = 0,

    @field:SerializedName("frontend_custom_content_type_id")
    val frontendCustomContentTypeId: Int? = 0,

    @field:SerializedName("created_at")
    val createdAt: Any? = null,

    @field:SerializedName("updated_at")
    val updatedAt: Any? = null,

    @field:SerializedName("ott_content")
    val ottContent: OttContent? = null,

    @field:SerializedName("frontent_custom_content_section")
    val frontentCustomContentSection: FrontendCustomContentSection? = null
)
