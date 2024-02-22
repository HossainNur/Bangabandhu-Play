package live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_content_by_slug

import com.google.gson.annotations.SerializedName

data class OttContent(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("uuid")
    val uuid: String? = null,

    @field:SerializedName("poster")
    val poster: Any? = null,  // Note: You may want to replace Object? with the actual type if known

    @field:SerializedName("access")
    val access: String? = null,

    @field:SerializedName("thumbnail_portrait")
    val thumbnailPortrait: String? = null,

    @field:SerializedName("thumbnail_landscape")
    val thumbnailLandscape: String? = null
)
