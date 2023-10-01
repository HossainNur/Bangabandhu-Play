package com.durbar.bangabandhuplay.data.model.category.sub.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubSubCategory {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("seo_title")
    @Expose
    public String seoTitle;
    @SerializedName("seo_description")
    @Expose
    public String seoDescription;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("ott_contents")
    @Expose
    public List<OttContent> ottContents;

    public SubSubCategory(Integer id, String title, String slug, Integer order, String seoTitle, String seoDescription, String status, List<OttContent> ottContents) {
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
