package com.durbar.bangabandhuplay.data.model.category.root.all;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class Data {
    @Nullable
    @SerializedName("id")
    @Expose
    private Integer id;
    @Nullable@SerializedName("title")
    @Expose
    private String title;
    @Nullable@SerializedName("slug")
    @Expose
    private String slug;
    @Nullable@SerializedName("image")
    @Expose
    private String image;
    @Nullable@SerializedName("order")
    @Expose
    private Integer order;
    @Nullable@SerializedName("seo_title")
    @Expose
    private String seoTitle;
    @Nullable@SerializedName("seo_description")
    @Expose
    private String seoDescription;
    @Nullable@SerializedName("status")
    @Expose
    private String status;

    public Data(Integer id, String title, String slug, String image, Integer order, String seoTitle, String seoDescription, String status) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
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

    public String getImage() {
        return image;
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
}
