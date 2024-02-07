package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FrontendCustomContent {
    @Nullable
    @SerializedName("id")
    @Expose
    private final Integer id;
    @Nullable
    @SerializedName("content_id")
    @Expose
    private final Integer contentId;
    @Nullable
    @SerializedName("publish_date")
    @Expose
    private final String publishDate;
    @Nullable
    @SerializedName("is_active")
    @Expose
    private final Integer isActive;
    @Nullable
    @SerializedName("is_upcoming")
    @Expose
    private final Integer isUpcoming;
    @Nullable
    @SerializedName("sorting_position")
    @Expose
    private final Integer sortingPosition;
    @Nullable
    @SerializedName("frontend_custom_content_type_id")
    @Expose
    private final Integer frontendCustomContentTypeId;
    @Nullable
    @SerializedName("created_at")
    @Expose
    private final Object createdAt;
    @Nullable
    @SerializedName("updated_at")
    @Expose
    private final Object updatedAt;
    @Nullable
    @SerializedName("ott_content")
    @Expose
    private final OttContent ottContent;


    public FrontendCustomContent(Integer id, Integer contentId, String publishDate, Integer isActive, Integer isUpcoming, Integer sortingPosition, Integer frontendCustomContentTypeId, Object createdAt, Object updatedAt, OttContent ottContent) {
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
}
