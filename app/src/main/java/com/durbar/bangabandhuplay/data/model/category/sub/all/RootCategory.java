package com.durbar.bangabandhuplay.data.model.category.sub.all;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootCategory {
    @Nullable
    @SerializedName("id")
    @Expose
    private Integer id;
    @Nullable
    @SerializedName("title")
    @Expose
    private String title;
    @Nullable
    @SerializedName("slug")
    @Expose
    private String slug;
    @Nullable
    @SerializedName("image")
    @Expose
    private String image;
    @Nullable
    @SerializedName("order")
    @Expose
    private Integer order;
    @Nullable
    @SerializedName("seo_title")
    @Expose
    private String seoTitle;
    @Nullable
    @SerializedName("seo_description")
    @Expose
    private String seoDescription;
    @Nullable
    @SerializedName("status")
    @Expose
    private String status;
    @Nullable
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @Nullable
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    public RootCategory(Integer id, String title, String slug, String image, Integer order, String seoTitle, String seoDescription, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
