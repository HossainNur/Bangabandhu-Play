package com.durbar.bangabandhuplay.data.model.category.root.single

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleRootCategory(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<Data>? = listOf(),

    @field:SerializedName("errors")
    val errors: Any? = null
)
