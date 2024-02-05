package com.durbar.bangabandhuplay.data.model.family_member

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("short_title")
    val shortTitle: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("type")
    val type: String? = null,
    @field:SerializedName("created_at")
    val createdAt: String? = null,
    @field:SerializedName("updated_at")
    val updatedAt: String? = null
)
