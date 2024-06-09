package com.durbar.bangabandhuplay.data.model.family_member

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("short_title") var shortTitle: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)
