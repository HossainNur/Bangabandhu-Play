package com.durbar.bangabandhuplay.data.model.get_related_contents

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentSource(
    @field:SerializedName("key")
    val key: Any? = null,

    @field:SerializedName("value")
    val value: Any? = null
)
