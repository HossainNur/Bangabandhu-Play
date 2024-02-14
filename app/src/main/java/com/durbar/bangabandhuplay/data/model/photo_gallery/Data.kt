package com.durbar.bangabandhuplay.data.model.photo_gallery

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("id") 
    val id: Int? = 0,
    @field:SerializedName("title") 
    val title: String? = null,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("short_title")
    val shortTitle: String? = null,
    @field:SerializedName("description") 
    val description: String? = null,
    @field:SerializedName("type")
    val type: String? = null,
    @field:SerializedName("created_at") 
    val createdAt: String? = null,
    @field:SerializedName("updated_at") 
    val updatedAt: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}
