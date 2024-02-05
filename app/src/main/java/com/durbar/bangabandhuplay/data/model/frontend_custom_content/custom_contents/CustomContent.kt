package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CustomContent(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<Data>? = listOf(),

    @field:SerializedName("errors")
    val errors: Any? = null
)
