package live.durbar.bangabandhuapp.data.model.category.root.single

import com.google.gson.annotations.SerializedName

data class CategorySlider(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("bottom_title")
    val bottomTitle: String? = null,

    @field:SerializedName("root_category_id")
    val rootCategoryId: Int? = 0,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("landscape_image")
    val landscapeImage: String? = null,

    @field:SerializedName("content_url")
    val contentUrl: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("is_home")
    val isHome: Int? = 0,

    @field:SerializedName("order")
    val order: Int? = 0,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null
)
