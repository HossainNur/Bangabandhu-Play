package live.durbar.bangabandhuapp.data.model.get_related_contents

import com.google.gson.annotations.SerializedName

data class FixedRelatedContent(
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("content_type_slug")
    val contentTypeSlug: Int? = 0,

    @field:SerializedName("content_type_title")
    val contentTypeTitle: String? = null,

    @field:SerializedName("more_info_slug")
    val moreInfoSlug: String? = null,

    @field:SerializedName("is_available_on_single_page")
    val isAvailableOnSinglePage: Int? = 0,

    @field:SerializedName("is_featured_section")
    val isFeaturedSection: Int? = 0,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("frontend_custom_content_limited_data")
    val frontendCustomContentLimitedData: List<FrontendCustomContentLimitedData>? = listOf()
)
