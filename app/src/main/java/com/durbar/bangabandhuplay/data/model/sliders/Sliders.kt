package com.durbar.bangabandhuplay.data.model.sliders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sliders(
    @field:SerializedName("status")
    val status: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: Data? = null,
    @field:SerializedName("errors")
    val errors: Any? = null
)