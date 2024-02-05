package com.durbar.bangabandhuplay.data.model.ott_content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentSource(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("ott_content_id")
    val ottContentId: Int? = 0,
    @field:SerializedName("content_source")
    val contentSource: String? = null,
    @field:SerializedName("fps")
    val fps: Any? = null,
    @field:SerializedName("source_format")
    val sourceFormat: Any? = null,
    @field:SerializedName("source_type")
    val sourceType: String? = null
)
