package live.durbar.bangabandhuapp.data.model.category.sub.all

import com.google.gson.annotations.SerializedName

data class AllSubCategory(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<Data>? = listOf(),

    @field:SerializedName("errors")
    val errors: Any? = null
)
