package live.durbar.bangabandhuapp.data.model.sliders

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("headers")
    val headers: Headers? = null,
    @field:SerializedName("original")
    val original: List<Original>? = listOf(),
    @field:SerializedName("exception")
    val exception: Any? = null
)
