package com.example.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedCategoryItem {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ott_content")
    @Expose
    private OttContent__1 ottContent;
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
