package com.durbar.bangabandhuplay.data.model.category.single_sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("image")
    @Expose
    public Object image;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("seo_title")
    @Expose
    public String seoTitle;
    @SerializedName("seo_description")
    @Expose
    public Object seoDescription;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("root_category_id")
    @Expose
    public Integer rootCategoryId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("ott_contents")
    @Expose
    public List<OttContent> ottContents;

    public Data(Integer id, String title, String slug, Object image, Integer order, String seoTitle, Object seoDescription, String status, Integer rootCategoryId, String createdAt, String updatedAt, List<OttContent> ottContents) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
        this.rootCategoryId = rootCategoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Object getImage() {
        return image;
    }

    public Integer getOrder() {
        return order;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public Object getSeoDescription() {
        return seoDescription;
    }

    public String getStatus() {
        return status;
    }

    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<OttContent> getOttContents() {
        return ottContents;
    }
}
