package live.durbar.bangabandhuapp.data.model.photo_gallery

import com.google.gson.annotations.SerializedName

data class PhotoGalleryResponse(
    @field:SerializedName("status") 
    val status: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: ArrayList<Data> = arrayListOf(),
    @field:SerializedName("errors") 
    val errors: String? = null
)
