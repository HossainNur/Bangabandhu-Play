package com.durbar.bangabandhuplay.data.model.pathshala

import com.google.gson.annotations.SerializedName

data class DataX(
    @field:SerializedName("ebooks")
    val ebooks: List<Ebook> = listOf(),
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("is_fixed")
    val isFixed: Int? = 0,
    @field:SerializedName("order")
    val order: Int? = 0,
    @field:SerializedName("slug")
    val slug: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("title")
    val title: String? = null
)
