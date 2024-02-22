package live.durbar.bangabandhuapp.data.model.frontend_custom_content.custom_content_by_slug

import com.google.gson.annotations.SerializedName

data class CustomContentBySlug(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("errors")
    val errors: Any? = null
)
