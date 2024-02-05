package com.durbar.bangabandhuplay.data.model.category.single_sub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleSubCategoryRes(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("errors")
    val errors: Any? = null
)