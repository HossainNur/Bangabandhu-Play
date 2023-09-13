package com.example.bangabandhuplay.data.model.category.sub.all;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("seo_title")
    @Expose
    private String seoTitle;
    @SerializedName("seo_description")
    @Expose
    private String seoDescription;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subSubCategories")
    @Expose
    private List<Object> subSubCategories;
    @SerializedName("rootCategory")
    @Expose
    private RootCategory rootCategory;


    public Data
            (
                    Integer id,
                    String title,
                    String slug,
                    String image,
                    Integer order,
                    String seoTitle,
                    String seoDescription,
                    String status,
                    List<Object> subSubCategories,
                    RootCategory rootCategory
            ) {
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
