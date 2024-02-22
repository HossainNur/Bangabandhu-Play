package live.durbar.bangabandhuapp.data.model.live

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("app_id")
    val appId: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("channel_name")
    val channelName: String? = null,
    @field:SerializedName("token")
    val token: String? = null,
    @field:SerializedName("status")
    val status: Int? = 0,
    @field:SerializedName("created_at")
    val createdAt: String? = null,
    @field:SerializedName("updated_at")
    val updatedAt: String? = null
)
