package com.durbar.bangabandhuplay.data.model.ott_content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("content_data")
    val contentData: ContentData? = null,
    @field:SerializedName("subscription")
    val subscription: Boolean? = null,
    @field:SerializedName("device_stream_limit_exceeded")
    val deviceStreamLimitExceeded: Boolean? = null
)
