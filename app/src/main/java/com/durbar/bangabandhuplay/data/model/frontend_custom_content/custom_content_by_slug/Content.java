package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {
    @Nullable
    @SerializedName("id")
    @Expose
    public Integer id;
    @Nullable
    @SerializedName("content_id")
    @Expose
    public Integer contentId;
    @Nullable
    @SerializedName("publish_date")
    @Expose
    public String publishDate;
    @Nullable
    @SerializedName("is_active")
    @Expose
    public Integer isActive;
    @Nullable
    @SerializedName("is_upcoming")
    @Expose
    public Integer isUpcoming;
    @Nullable
    @SerializedName("sorting_position")
    @Expose
    public Integer sortingPosition;
    @Nullable
    @SerializedName("frontend_custom_content_type_id")
    @Expose
    public Integer frontendCustomContentTypeId;
    @Nullable
    @SerializedName("created_at")
    @Expose
    public Object createdAt;
    @Nullable
    @SerializedName("updated_at")
    @Expose
    public Object updatedAt;
    @Nullable
    @SerializedName("ott_content")
    @Expose
    public OttContent ottContent;
    @Nullable
    @SerializedName("frontent_custom_content_section")
    @Expose
    public FrontendCustomContentSection frontentCustomContentSection;

    public Content(Integer id, Integer contentId, String publishDate, Integer isActive, Integer isUpcoming, Integer sortingPosition, Integer frontendCustomContentTypeId, Object createdAt, Object updatedAt, OttContent ottContent, FrontendCustomContentSection frontentCustomContentSection) {
        this.id = id;
        this.contentId = contentId;
        this.publishDate = publishDate;
        this.isActive = isActive;
        this.isUpcoming = isUpcoming;
        this.sortingPosition = sortingPosition;
        this.frontendCustomContentTypeId = frontendCustomContentTypeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ottContent = ottContent;
        this.frontentCustomContentSection = frontentCustomContentSection;
    }

    public Integer getId() {
        return id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Integer getIsUpcoming() {
        return isUpcoming;
    }

    public Integer getSortingPosition() {
        return sortingPosition;
    }

    public Integer getFrontendCustomContentTypeId() {
        return frontendCustomContentTypeId;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public OttContent getOttContent() {
        return ottContent;
    }

    public FrontendCustomContentSection getFrontentCustomContentSection() {
        return frontentCustomContentSection;
    }
}
