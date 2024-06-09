package com.durbar.bangabandhuplay.data.model.photo_gallery

import com.google.gson.annotations.SerializedName

data class PhotoGalleryResponse(
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("errors") var errors: String? = null
)
