package live.durbar.bangabandhuapp.data.model.category.root.single

import com.google.gson.annotations.SerializedName

data class SelectedCategoryItem(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("ott_content")
    val ottContent: OttContent__1? = null,

    @field:SerializedName("is_featured")
    val isFeatured: Int? = 0
)