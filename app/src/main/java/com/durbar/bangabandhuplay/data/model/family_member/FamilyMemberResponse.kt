package com.durbar.bangabandhuplay.data.model.family_member

import com.google.gson.annotations.SerializedName

data class FamilyMemberResponse(
    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("errors") var errors: String? = null
)
