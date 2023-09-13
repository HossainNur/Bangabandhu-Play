package com.example.bangabandhuplay.data.model.category.sub.all;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootCategory {
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    public RootCategory
            (
                    Integer id,
                    String title,
                    String slug,
                    String image,
                    Integer order,
                    String seoTitle,
                    String seoDescription,
                    String status,
                    String createdAt,
                    String updatedAt
            ) {
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
