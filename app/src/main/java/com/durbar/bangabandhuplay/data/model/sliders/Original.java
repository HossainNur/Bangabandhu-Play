package com.durbar.bangabandhuplay.data.model.sliders;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Original {
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
    @SerializedName("root_category")
    @Expose
    public RootCategory rootCategory;
    @SerializedName("ott_content")
    @Expose
    public OttContent ottContent;

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

    public RootCategory getRootCategory() {
        return rootCategory;
    }

    public OttContent getOttContent() {
        return ottContent;
    }

    @Override
    public String toString() {
        return "Original{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", bottomTitle='" + bottomTitle + '\'' +
                ", rootCategoryId=" + rootCategoryId +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", landscapeImage='" + landscapeImage + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                ", status='" + status + '\'' +
                ", isHome=" + isHome +
                ", order=" + order +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", rootCategory=" + rootCategory +
                ", ottContent=" + ottContent +
                '}';
    }
}
