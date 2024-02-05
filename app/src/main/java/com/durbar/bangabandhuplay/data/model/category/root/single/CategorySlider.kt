package com.durbar.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategorySlider {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("bottom_title")
    @Expose
    public String bottomTitle;
    @SerializedName("root_category_id")
    @Expose
    public Integer rootCategoryId;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("landscape_image")
    @Expose
    public String landscapeImage;
    @SerializedName("content_url")
    @Expose
    public String contentUrl;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("is_home")
    @Expose
    public Integer isHome;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public CategorySlider(Integer id, String title, String description, String bottomTitle, Integer rootCategoryId, String slug, String image, String landscapeImage, String contentUrl, String status, Integer isHome, Integer order, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.bottomTitle = bottomTitle;
        this.rootCategoryId = rootCategoryId;
        this.slug = slug;
        this.image = image;
        this.landscapeImage = landscapeImage;
        this.contentUrl = contentUrl;
        this.status = status;
        this.isHome = isHome;
        this.order = order;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getLandscapeImage() {
        return landscapeImage;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
