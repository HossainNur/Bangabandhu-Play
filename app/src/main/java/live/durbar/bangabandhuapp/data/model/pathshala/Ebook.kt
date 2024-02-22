package live.durbar.bangabandhuapp.data.model.pathshala

import com.google.gson.annotations.SerializedName

data class Ebook(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("pdf")
    val pdf: String? = null,
    @SerializedName("root_category_id")
    val rootCategoryId: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
