package live.durbar.bangabandhuapp.data.model.ott_content

import com.google.gson.annotations.SerializedName

data class CastAndCrew(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("about")
    val about: String? = null,
    @field:SerializedName("dob")
    val dob: String? = null,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("nationality")
    val nationality: String? = null,
    @field:SerializedName("upcomming")
    val upcomming: String? = null,
    @field:SerializedName("previous")
    val previous: String? = null,
    @field:SerializedName("created_at")
    val createdAt: String? = null,
    @field:SerializedName("updated_at")
    val updatedAt: String? = null,
    @field:SerializedName("pivot")
    val pivot: Pivot? = null
)
