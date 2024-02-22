package live.durbar.bangabandhuapp.data.model.category.root.single

import com.google.gson.annotations.SerializedName

data class ParentItem(
    @field:SerializedName("ParentItemTitle")
    val parentItemTitle: String? = null,

    @field:SerializedName("ChildItemList")
    val childItemList: List<ChildItem>? = null
)