package live.durbar.bangabandhuapp.data.model.category.sub.single

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("slug")
    val slug: String? = null,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("order")
    val order: Int? = 0,
    @field:SerializedName("seo_title")
    val seoTitle: String? = null,
    @field:SerializedName("seo_description")
    val seoDescription: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("sub_sub_categories")
    val subSubCategories: List<SubSubCategory>? = listOf(),
    @field:SerializedName("categorySliders")
    val categorySliders: Any? = null
)
