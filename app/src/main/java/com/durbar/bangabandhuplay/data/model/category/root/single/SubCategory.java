package com.durbar.bangabandhuplay.data.model.category.root.single;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {
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
    @SerializedName("ott_contents")
    @Expose
    private List<OttContent> ottContents;

    public SubCategory(Integer id, String title, String slug, Integer order, String seoTitle, String seoDescription, String status, List<OttContent> ottContents) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
        this.ottContents = ottContents;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getOrder() {
        return order;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public String getStatus() {
        return status;
    }

    public List<OttContent> getOttContents() {
        return ottContents;
    }
}
