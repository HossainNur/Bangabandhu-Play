package live.durbar.bangabandhuapp.data.model.ott_content

import com.google.gson.annotations.SerializedName

data class Pivot(
    @field:SerializedName("ott_content_id")
    val ottContentId: Int? = 0,
    @field:SerializedName("cast_and_crew_id")
    val castAndCrewId: Int? = 0,
    @field:SerializedName("role")
    val role: String? = null
)
