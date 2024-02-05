package com.durbar.bangabandhuplay.data.model.get_related_contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OttContent(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("poster")
    val poster: Any? = null,

    @field:SerializedName("access")
    val access: String? = null
)
