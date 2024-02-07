package com.durbar.bangabandhuplay.data.model.category.sub.all;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
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
    @SerializedName("subSubCategories")
    @Expose
    private List<Object> subSubCategories;
    @Nullable
    @SerializedName("rootCategory")
    @Expose
    private RootCategory rootCategory;


    public Data(Integer id, String title, String slug, String image, Integer order, String seoTitle, String seoDescription, String status, List<Object> subSubCategories, RootCategory rootCategory) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.order = order;
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        this.status = status;
        this.subSubCategories = subSubCategories;
        this.rootCategory = rootCategory;
    }
}
