package com.durbar.bangabandhuplay.data.model.fronend_custom_slider


import com.google.gson.annotations.SerializedName

data class FrontEndCustomSliders(
    @field:SerializedName("data")
    val `data`: Data? = null,
    @field:SerializedName("errors")
    val errors: Any? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: Boolean? = null
)

data class Data(
    @field:SerializedName("exception")
    val exception: Any?= null,
    @field:SerializedName("headers")
    val headers: Headers?= null,
    @field:SerializedName("original")
    val original: List<Original>? = listOf()
)

data class Original(
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("press_action_slug")
    val pressActionSlug: Any? = null
)

class Headers