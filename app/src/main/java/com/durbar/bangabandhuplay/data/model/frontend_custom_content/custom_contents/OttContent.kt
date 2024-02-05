package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OttContent(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("poster")
    val poster: Any? = null,

    @field:SerializedName("access")
    val access: String? = null,

    @field:SerializedName("runtime")
    val runtime: Any? = null,

    @field:SerializedName("thumbnail_portrait")
    val thumbnailPortrait: String? = null,

    @field:SerializedName("thumbnail_landscape")
    val thumbnailLandscape: String? = null
)
