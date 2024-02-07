package com.durbar.bangabandhuplay.data.model.category.root.single;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedCategoryItem {


    @Nullable
    @SerializedName("id")
    @Expose
    private Integer id;
    @Nullable
    @SerializedName("ott_content")
    @Expose
    private OttContent__1 ottContent;
    @Nullable
    @SerializedName("is_featured")
    @Expose
    private Integer isFeatured;

    public SelectedCategoryItem(Integer id, OttContent__1 ottContent, Integer isFeatured) {
        this.id = id;
        this.ottContent = ottContent;
        this.isFeatured = isFeatured;
    }

    public Integer getId() {
        return id;
    }

    public OttContent__1 getOttContent() {
        return ottContent;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }
}
