package live.durbar.bangabandhuapp.data.model.family_member

import com.google.gson.annotations.SerializedName

data class FamilyMemberResponse(
    @field:SerializedName("status")
    val status: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: ArrayList<Data> = arrayListOf(),
    @field:SerializedName("errors")
    val errors: String? = null
)
