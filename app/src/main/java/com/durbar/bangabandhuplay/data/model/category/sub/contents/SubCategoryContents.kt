package com.durbar.bangabandhuplay.data.model.category.sub.contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubCategoryContents(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<Data>? = listOf(),

    @field:SerializedName("errors")
    val errors: Any? = null
)
