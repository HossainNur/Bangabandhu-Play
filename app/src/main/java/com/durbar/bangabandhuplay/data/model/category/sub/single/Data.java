package com.durbar.bangabandhuplay.data.model.category.sub.single;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("title")
    @Expose
    public String title;
    @Nullable
    @SerializedName("slug")
    @Expose
    public String slug;
    @Nullable
    @SerializedName("image")
    @Expose
    public String image;
    @Nullable
    @SerializedName("order")
    @Expose
    public Integer order;
    @Nullable
    @SerializedName("seo_title")
    @Expose
    public String seoTitle;
    @Nullable
    @SerializedName("seo_description")
    @Expose
    public String seoDescription;
    @Nullable
    @SerializedName("status")
    @Expose
    public String status;
    @Nullable
    @SerializedName("sub_sub_categories")
    @Expose
    public List<SubSubCategory> subSubCategories;
    @Nullable
    @SerializedName("categorySliders")
    @Expose
    public Object categorySliders;

    public Data(Integer id, String title, String slug, String image, Integer order, String seoTitle, String seoDescription, String status, List<SubSubCategory> subSubCategories, Object categorySliders) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
        this.subSubCategories = subSubCategories;
        this.categorySliders = categorySliders;
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

    public List<SubSubCategory> getSubSubCategories() {
        return subSubCategories;
    }

    public Object getCategorySliders() {
        return categorySliders;
    }
}
