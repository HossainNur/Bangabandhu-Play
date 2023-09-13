package com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Original {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content_id")
    @Expose
    private Integer contentId;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("is_upcoming")
    @Expose
    private Integer isUpcoming;
    @SerializedName("sorting_position")
    @Expose
    private Integer sortingPosition;
    @SerializedName("frontend_custom_content_type_id")
    @Expose
    private Integer frontendCustomContentTypeId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("ott_content")
    @Expose
    private OttContent ottContent;
    @SerializedName("frontent_custom_content_section")
    @Expose
    private FrontendCustomContentSection frontendCustomContentSection;

    public Original(Integer id, Integer contentId, String publishDate, Integer isActive, Integer isUpcoming, Integer sortingPosition, Integer frontendCustomContentTypeId, Object createdAt, Object updatedAt, OttContent ottContent, FrontendCustomContentSection frontendCustomContentSection) {
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
        this.frontendCustomContentSection = frontendCustomContentSection;
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

    public FrontendCustomContentSection getFrontendCustomContentSection() {
        return frontendCustomContentSection;
    }
}
