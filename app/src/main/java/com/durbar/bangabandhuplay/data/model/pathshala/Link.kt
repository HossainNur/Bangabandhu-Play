package com.durbar.bangabandhuplay.data.model.pathshala

import com.google.gson.annotations.SerializedName

data class Link(
    @field:SerializedName("active")
    val active: Boolean? = null,
    @field:SerializedName("label")
    val label: String? = null,
    @field:SerializedName("url")
    val url: String? = null
)
