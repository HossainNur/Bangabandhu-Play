package live.durbar.bangabandhuapp.data.model.category.root.all

import com.google.gson.annotations.SerializedName

data class AllRootCategory(
    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<Data>? = listOf(),

    @field:SerializedName("errors")
    val errors: Any? = null
)
