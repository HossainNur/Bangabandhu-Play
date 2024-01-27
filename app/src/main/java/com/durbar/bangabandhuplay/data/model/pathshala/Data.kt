package com.durbar.bangabandhuplay.data.model.pathshala

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("current_page")
    val currentPage: Int? = 0,
    @field:SerializedName("data")
    val `data`: List<DataX>? = listOf(),
    @field:SerializedName("first_page_url")
    val firstPageUrl: String?= null,
    @field:SerializedName("from")
    val from: Int? = 0,
    @field:SerializedName("last_page")
    val lastPage: Int? = 0,
    @field:SerializedName("last_page_url")
    val lastPageUrl: String? = null,
    @field:SerializedName("links")
    val links: List<Link>? = listOf(),
    @field:SerializedName("next_page_url")
    val nextPageUrl: Any? = null,
    @field:SerializedName("path")
    val path: String?= null,
    @field:SerializedName("per_page")
    val perPage: Int? = 0,
    @field:SerializedName("prev_page_url")
    val prevPageUrl: Any? = null,
    @field:SerializedName("to")
    val to: Int? = 0,
    @field:SerializedName("total")
    val total: Int? = 0
)
