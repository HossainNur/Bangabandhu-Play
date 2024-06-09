package com.durbar.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategorySlider {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("bottom_title")
    @Expose
    private String bottomTitle;
    @SerializedName("root_category_id")
    @Expose
    private Integer rootCategoryId;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("content_url")
    @Expose
    private String contentUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_home")
    @Expose
    private Integer isHome;
    @SerializedName("order")
    @Expose
    private Integer order;

    public CategorySlider(Integer id, String title, String description, String bottomTitle, Integer rootCategoryId, String slug, String image, String contentUrl, String status, Integer isHome, Integer order) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.bottomTitle = bottomTitle;
        this.rootCategoryId = rootCategoryId;
        this.slug = slug;
        this.image = image;
        this.contentUrl = contentUrl;
        this.status = status;
        this.isHome = isHome;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBottomTitle() {
        return bottomTitle;
    }

    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    public String getSlug() {
        return slug;
    }

    public String getImage() {
        return image;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public String getStatus() {
        return status;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public Integer getOrder() {
        return order;
    }
}
