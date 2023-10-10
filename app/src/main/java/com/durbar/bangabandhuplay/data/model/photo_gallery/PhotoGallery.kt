package com.durbar.bangabandhuplay.data.model.photo_gallery


import com.google.gson.annotations.SerializedName

data class PhotoGallery(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("errors")
    val errors: Any?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)