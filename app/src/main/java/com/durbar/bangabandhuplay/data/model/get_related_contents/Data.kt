package com.durbar.bangabandhuplay.data.model.get_related_contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("single_content_related_contents")
    val singleContentRelatedContents: List<SingleContentRelatedContent>? = listOf(),

    @field:SerializedName("fixed_related_contents")
    val fixedRelatedContents: List<FixedRelatedContent>? = listOf()
)
