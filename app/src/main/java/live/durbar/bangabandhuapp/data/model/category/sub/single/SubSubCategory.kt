package live.durbar.bangabandhuapp.data.model.category.sub.single

import com.google.gson.annotations.SerializedName

data class SubSubCategory(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("slug")
    val slug: String? = null,
    @field:SerializedName("order")
    val order: Int? = 0,
    @field:SerializedName("seo_title")
    val seoTitle: String? = null,
    @field:SerializedName("seo_description")
    val seoDescription: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("ott_contents")
    val ottContents: List<OttContent>? = listOf()
)

