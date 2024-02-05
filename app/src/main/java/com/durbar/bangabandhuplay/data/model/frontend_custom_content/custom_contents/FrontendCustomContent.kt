package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FrontendCustomContent {
    @SerializedName("id")
    @Expose
    private final Integer id;
    @SerializedName("content_id")
    @Expose
    private final Integer contentId;
    @SerializedName("publish_date")
    @Expose
    private final String publishDate;
    @SerializedName("is_active")
    @Expose
    private final Integer isActive;
    @SerializedName("is_upcoming")
    @Expose
    private final Integer isUpcoming;
    @SerializedName("sorting_position")
    @Expose
    private final Integer sortingPosition;
    @SerializedName("frontend_custom_content_type_id")
    @Expose
    private final Integer frontendCustomContentTypeId;
    @SerializedName("created_at")
    @Expose
    private final Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private final Object updatedAt;
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
