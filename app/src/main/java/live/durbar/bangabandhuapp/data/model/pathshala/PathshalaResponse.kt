package live.durbar.bangabandhuapp.data.model.pathshala

import com.google.gson.annotations.SerializedName

data class PathshalaResponse(
    @field:SerializedName("data")
    val `data`: Data? = null,
    @field:SerializedName("errors")
    val errors: Any? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: Boolean? = null
)
